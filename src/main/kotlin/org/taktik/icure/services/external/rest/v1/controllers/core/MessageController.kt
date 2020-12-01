/*
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of iCureBackend.
 *
 * iCureBackend is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as published by
 * the Free Software Foundation.
 *
 * iCureBackend is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with iCureBackend.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.taktik.icure.services.external.rest.v1.controllers.core

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.common.base.Splitter
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flattenConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.reactor.mono
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import org.taktik.couchdb.DocIdentifier
import org.taktik.icure.asynclogic.AsyncSessionLogic
import org.taktik.icure.asynclogic.MessageLogic
import org.taktik.icure.db.PaginationOffset
import org.taktik.icure.entities.Message
import org.taktik.icure.services.external.rest.v1.dto.ListOfIdsDto
import org.taktik.icure.services.external.rest.v1.dto.MessageDto
import org.taktik.icure.services.external.rest.v1.dto.MessagesReadStatusUpdate
import org.taktik.icure.services.external.rest.v1.dto.embed.DelegationDto
import org.taktik.icure.services.external.rest.v1.mapper.MessageMapper
import org.taktik.icure.services.external.rest.v1.mapper.StubMapper
import org.taktik.icure.services.external.rest.v1.mapper.embed.DelegationMapper
import org.taktik.icure.utils.firstOrNull
import org.taktik.icure.utils.injectReactorContext
import org.taktik.icure.utils.paginatedList
import reactor.core.publisher.Flux
import kotlin.streams.toList


@FlowPreview
@ExperimentalCoroutinesApi
@RestController
@RequestMapping("/rest/v1/message")
@Tag(name = "message")
class MessageController(
        private val messageLogic: MessageLogic,
        private val sessionLogic: AsyncSessionLogic,
        private val messageMapper: MessageMapper,
        private val delegationMapper: DelegationMapper,
        private val stubMapper: StubMapper,
        private val objectMapper: ObjectMapper
) {
    val DEFAULT_LIMIT = 1000
    private val messageToMessageDto = { it: Message -> messageMapper.map(it) }
    private val logger = LoggerFactory.getLogger(this::class.java)

    @Operation(summary = "Creates a message")
    @PostMapping
    fun createMessage(@RequestBody messageDto: MessageDto) = mono {
        messageLogic.createMessage(messageMapper.map(messageDto))?.let { messageMapper.map(it) }
                ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Message creation failed")
                        .also { logger.error(it.message) }
    }

    @Operation(summary = "Deletes a message delegation")
    @DeleteMapping("/{messageId}/delegate/{delegateId}")
    fun deleteDelegation(
            @PathVariable messageId: String,
            @PathVariable delegateId: String) = mono {
        val message = messageLogic.get(messageId)
                ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Message with ID: $messageId not found").also { logger.error(it.message) }

        messageLogic.updateEntities(listOf(message.copy(delegations = message.delegations - delegateId))).firstOrNull()?.let { messageMapper.map(it) }
                ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Message delegation deletion failed").also { logger.error(it.message) }
    }

    @Operation(summary = "Deletes multiple messages")
    @DeleteMapping("/{messageIds}")
    fun deleteMessages(@PathVariable messageIds: String): Flux<DocIdentifier> {
        return messageIds.split(',').takeIf { it.isNotEmpty() }
                ?.let {
                    try {
                        messageLogic.deleteByIds(it).injectReactorContext()
                    } catch (e: java.lang.Exception) {
                        throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message).also { logger.error(it.message) }
                    }
                }
                ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Messages deletion failed").also { logger.error(it.message) }

    }

    @Operation(summary = "Deletes multiple messages")
    @PostMapping("/delete/byIds")
    fun deleteMessagesBatch(@RequestBody messagesIds: ListOfIdsDto): Flux<DocIdentifier>? {
        return messagesIds.ids?.takeIf { it.isNotEmpty() }
                ?.let {
                    try {
                        messageLogic.deleteByIds(it).injectReactorContext()
                    } catch (e: Exception) {
                        throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message).also { logger.error(it.message) }
                    }
                }
    }

    @Operation(summary = "Gets a message")
    @GetMapping("/{messageId}")
    fun getMessage(@PathVariable messageId: String) = mono {
        messageLogic.get(messageId)?.let { messageMapper.map(it) }
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found")
                        .also { logger.error(it.message) }
    }


    @Operation(summary = "List messages found By Healthcare Party and secret foreign keys.", description = "Keys must be delimited by coma")
    @GetMapping("/byHcPartySecretForeignKeys")
    fun findMessagesByHCPartyPatientForeignKeys(@RequestParam secretFKeys: String): Flux<MessageDto> {
        val secretPatientKeys = secretFKeys.split(',').map { it.trim() }
        return messageLogic.listMessagesByHCPartySecretPatientKeys(secretPatientKeys)
                .map { contact -> messageMapper.map(contact) }
                .injectReactorContext()
    }

    @Operation(summary = "Get all messages (paginated) for current HC Party")
    @GetMapping
    fun findMessages(@RequestParam(required = false) startKey: String?,
                     @RequestParam(required = false) startDocumentId: String?,
                     @RequestParam(required = false) limit: Int?) = mono {
        val realLimit = limit ?: DEFAULT_LIMIT
        val startKeyList = startKey?.takeIf { it.isNotEmpty() }?.let { Splitter.on(",").omitEmptyStrings().trimResults().splitToList(it) }
        val paginationOffset = PaginationOffset<List<Any>>(startKeyList, startDocumentId, null, realLimit + 1)

        messageLogic.findForCurrentHcParty(paginationOffset).paginatedList(messageToMessageDto, realLimit)
    }

    @Operation(summary = "Get children messages of provided message")
    @GetMapping("/{messageId}/children")
    fun getChildrenMessages(@PathVariable messageId: String) =
            messageLogic.getChildren(messageId).map { messageMapper.map(it) }.injectReactorContext()


    @Operation(summary = "Get children messages of provided message")
    @PostMapping("/children/batch")
    fun getChildrenMessagesOfList(@RequestBody parentIds: ListOfIdsDto) =
            messageLogic.getChildren(parentIds.ids)
                    .map { m -> m.stream().map { mm -> messageMapper.map(mm) }.toList().asFlow() }
                    .flattenConcat()
                    .injectReactorContext()

    @Operation(summary = "Get children messages of provided message")
    @PostMapping("byInvoiceId")
    fun listMessagesByInvoiceIds(@RequestBody ids: ListOfIdsDto) =
            messageLogic.listMessagesByInvoiceIds(ids.ids).map { messageMapper.map(it) }.injectReactorContext()

    @Operation(summary = "Get all messages (paginated) for current HC Party and provided transportGuid")
    @GetMapping("/byTransportGuid")
    fun findMessagesByTransportGuid(
            @RequestParam(required = false) transportGuid: String?,
            @RequestParam(required = false) received: Boolean?,
            @RequestParam(required = false) startKey: String?,
            @RequestParam(required = false) startDocumentId: String?,
            @RequestParam(required = false) limit: Int?,
            @RequestParam(required = false) hcpId: String?) = mono {
        val realLimit = limit ?: DEFAULT_LIMIT
        val startKeyList = startKey?.takeIf { it.isNotEmpty() }?.let { Splitter.on(",").omitEmptyStrings().trimResults().splitToList(it) }
        val paginationOffset = PaginationOffset<List<Any>>(startKeyList, startDocumentId, null, realLimit + 1)
        val hcpId = hcpId ?: sessionLogic.getCurrentHealthcarePartyId()
        val messages = received?.takeIf { it }?.let { messageLogic.findByTransportGuidReceived(hcpId, transportGuid, paginationOffset) }
                ?: messageLogic.findByTransportGuid(hcpId, transportGuid, paginationOffset)
        messages.paginatedList<Message, MessageDto>(messageToMessageDto, realLimit)
    }

    @Operation(summary = "Get all messages starting by a prefix between two date")
    @GetMapping("/byTransportGuidSentDate")
    fun findMessagesByTransportGuidSentDate(
            @RequestParam(required = false) transportGuid: String,
            @RequestParam(required = false, value = "from") fromDate: Long,
            @RequestParam(required = false, value = "to") toDate: Long,
            @RequestParam(required = false) startKey: String?,
            @RequestParam(required = false) startDocumentId: String?,
            @RequestParam(required = false) limit: Int?,
            @RequestParam(required = false) hcpId: String?) = mono {
        val realLimit = limit ?: DEFAULT_LIMIT
        val startKeyList = startKey?.takeIf { it.isNotEmpty() }?.let { Splitter.on(",").omitEmptyStrings().trimResults().splitToList(it) }
        val paginationOffset = PaginationOffset<List<Any>>(startKeyList, startDocumentId, null, realLimit + 1)
        messageLogic.findByTransportGuidSentDate(
                hcpId ?: sessionLogic.getCurrentHealthcarePartyId(),
                transportGuid,
                fromDate,
                toDate,
                paginationOffset
        ).paginatedList<Message, MessageDto>(messageToMessageDto, realLimit)
    }


    @Operation(summary = "Get all messages (paginated) for current HC Party and provided to address")
    @GetMapping("/byToAddress")
    fun findMessagesByToAddress(
            @RequestParam(required = false) toAddress: String,
            @RequestParam(required = false) startKey: String?,
            @RequestParam(required = false) startDocumentId: String?,
            @RequestParam(required = false) limit: Int?,
            @RequestParam(required = false) reverse: Boolean?,
            @RequestParam(required = false) hcpId: String?) = mono {
        val realLimit = limit ?: DEFAULT_LIMIT
        val startKeyElements = objectMapper.readValue<List<String>>(startKey, objectMapper.typeFactory.constructCollectionType(List::class.java, String::class.java))
        val paginationOffset = PaginationOffset<List<Any>>(startKeyElements, startDocumentId, null, realLimit + 1)
        val hcpId = hcpId ?: sessionLogic.getCurrentHealthcarePartyId()
        messageLogic.findByToAddress(hcpId, toAddress, paginationOffset, reverse).paginatedList<Message, MessageDto>(messageToMessageDto, realLimit)
    }

    @Operation(summary = "Get all messages (paginated) for current HC Party and provided from address")
    @GetMapping("/byFromAddress")
    fun findMessagesByFromAddress(
            @RequestParam(required = false) fromAddress: String,
            @RequestParam(required = false) startKey: String?,
            @RequestParam(required = false) startDocumentId: String?,
            @RequestParam(required = false) limit: Int?,
            @RequestParam(required = false) hcpId: String?) = mono {
        val realLimit = limit ?: DEFAULT_LIMIT
        val startKeyElements = objectMapper.readValue<List<String>>(startKey, objectMapper.typeFactory.constructCollectionType(List::class.java, String::class.java))
        val paginationOffset = PaginationOffset<List<Any>>(startKeyElements, startDocumentId, null, realLimit + 1)
        val hcpId = hcpId ?: sessionLogic.getCurrentHealthcarePartyId()
        messageLogic.findByFromAddress(hcpId, fromAddress, paginationOffset).paginatedList<Message, MessageDto>(messageToMessageDto, realLimit)
    }

    @Operation(summary = "Updates a message")
    @PutMapping
    fun modifyMessage(@RequestBody messageDto: MessageDto) = mono {
        if (messageDto.id == null) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "New delegation for message failed")
                    .also { logger.error(it.message) }
        }
        messageMapper.map(messageDto)
            messageLogic.modifyMessage(messageMapper.map(messageDto))?.let { messageMapper.map(it) }
                ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "New delegation for message failed")
                        .also { logger.error(it.message) }
    }

    @Operation(summary = "Set status bits for given list of messages")
    @PutMapping("/status/{status}")
    fun setMessagesStatusBits(
            @PathVariable status: Int,
            @RequestBody messageIds: ListOfIdsDto) = messageLogic.setStatus(messageIds.ids, status).map { messageMapper.map(it) }.injectReactorContext()

    @Operation(summary = "Set read status for given list of messages")
    @PutMapping("/readstatus")
    fun setMessagesReadStatus(@RequestBody data: MessagesReadStatusUpdate) = flow {
        emitAll(messageLogic.setReadStatus(data.ids ?: listOf(), data.userId ?: sessionLogic.getCurrentUserId(), data.status
                ?: false, data.time ?: System.currentTimeMillis()).map { messageMapper.map(it) })
    }.injectReactorContext()

    @Operation(summary = "Adds a delegation to a message")
    @PutMapping("/{messageId}/delegate")
    fun newMessageDelegations(
            @PathVariable messageId: String,
            @RequestBody ds: List<DelegationDto>) = mono {
        messageLogic.addDelegations(messageId, ds.map { delegationMapper.map(it) })?.takeIf { it.delegations.isNotEmpty() }?.let { stubMapper.mapToStub(it) }
                ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "New delegation for message failed")
    }
}
