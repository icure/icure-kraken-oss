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
package org.taktik.icure.services.external.rest.v1.dto


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.github.pozo.KotlinBuilder
import org.taktik.icure.services.external.rest.v1.dto.base.CodeStubDto
import org.taktik.icure.services.external.rest.v1.dto.base.EncryptableDto
import org.taktik.icure.services.external.rest.v1.dto.base.ICureDocumentDto
import org.taktik.icure.services.external.rest.v1.dto.base.StoredDocumentDto
import org.taktik.icure.services.external.rest.v1.dto.embed.AddressDto
import org.taktik.icure.services.external.rest.v1.dto.embed.CalendarItemTagDto
import org.taktik.icure.services.external.rest.v1.dto.embed.DelegationDto
import org.taktik.icure.services.external.rest.v1.dto.embed.FlowItemDto
import org.taktik.icure.validation.NotNull

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@KotlinBuilder
data class CalendarItemDto(
        override val id: String,
        override val rev: String? = null,
        override val created: Long? = null,
        override val modified: Long? = null,
        override val author: String? = null,
        override val responsible: String? = null,
        override val medicalLocationId: String? = null,
        override val tags: Set<CodeStubDto> = setOf(),
        override val codes: Set<CodeStubDto> = setOf(),
        override val endOfLife: Long? = null,
        override val deletionDate: Long? = null,
        @NotNull val title: String? = null,
        val calendarItemTypeId: String? = null,
        val masterCalendarItemId: String? = null,
        @Deprecated("Use crypedForeignKeys instead") val patientId: String? = null,
        val important: Boolean? = null,
        val homeVisit: Boolean? = null,
        val phoneNumber: String? = null,
        val placeId: String? = null,
        val address: AddressDto? = null,
        val addressText: String? = null,
        val startTime: Long? = null, // YYYYMMDDHHMMSS if unknown, 00, ex:20010800000000. Note that to avoid all confusion: 2015/01/02 00:00:00 is encoded as 20150101235960.
        val endTime: Long? = null, // YYYYMMDDHHMMSS if unknown, 00, ex:20010800000000. Note that to avoid all confusion: 2015/01/02 00:00:00 is encoded as 20150101235960.
        val confirmationTime: Long? = null, // YYYYMMDDHHMMSS if unknown, 00, ex:20010800000000. Note that to avoid all confusion: 2015/01/02 00:00:00 is encoded as 20150101235960.
        val confirmationId: String? = null,
        val duration: Long? = null,
        val allDay: Boolean? = null,
        val details: String? = null,
        val wasMigrated: Boolean? = null,
        val agendaId: String? = null,
        val meetingTags: Set<CalendarItemTagDto> = setOf(),
        val flowItem: FlowItemDto? = null,
        override val secretForeignKeys: Set<String> = setOf(),
        override val cryptedForeignKeys: Map<String, Set<DelegationDto>> = mapOf(),
        override val delegations: Map<String, Set<DelegationDto>> = mapOf(),
        override val encryptionKeys: Map<String, Set<DelegationDto>> = mapOf(),
        override val encryptedSelf: String? = null
) : StoredDocumentDto, ICureDocumentDto<String>, EncryptableDto {
    override fun withIdRev(id: String?, rev: String) = if (id != null) this.copy(id = id, rev = rev) else this.copy(rev = rev)
    override fun withDeletionDate(deletionDate: Long?) = this.copy(deletionDate = deletionDate)
}
