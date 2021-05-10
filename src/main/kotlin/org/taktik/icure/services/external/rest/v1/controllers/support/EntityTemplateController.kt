/*
 *  iCure Data Stack. Copyright (c) 2020 Taktik SA
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as
 *     published by the Free Software Foundation, either version 3 of the
 *     License, or (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful, but
 *     WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *     Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public
 *     License along with this program.  If not, see
 *     <https://www.gnu.org/licenses/>.
 */

package org.taktik.icure.services.external.rest.v1.controllers.support

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.reactor.mono
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
import org.taktik.icure.asynclogic.EntityTemplateLogic
import org.taktik.icure.entities.EntityTemplate
import org.taktik.icure.services.external.rest.v1.dto.EntityTemplateDto
import org.taktik.icure.services.external.rest.v1.mapper.EntityTemplateMapper
import org.taktik.icure.utils.injectReactorContext
import reactor.core.publisher.Flux
import java.util.stream.Collectors

@ExperimentalCoroutinesApi
@RestController
@RequestMapping("/rest/v1/entitytemplate")
@Tag(name = "entitytemplate")
class EntityTemplateController(
        private val entityTemplateLogic: EntityTemplateLogic,
        private val entityTemplateMapper: EntityTemplateMapper
) {

    @Operation(summary = "Finding entityTemplates by userId, entityTemplate, type and version with pagination.", description = "Returns a list of entityTemplates matched with given input.")
    @GetMapping("/find/{userId}/{type}")
    fun findEntityTemplates(
            @PathVariable userId: String,
            @PathVariable type: String,
            @RequestParam(required = false) searchString: String?,
            @RequestParam(required = false) includeEntities: Boolean?) = entityTemplateLogic.findEntityTemplates(userId, type, searchString, includeEntities).injectReactorContext()

    @Operation(summary = "Finding entityTemplates by entityTemplate, type and version with pagination.", description = "Returns a list of entityTemplates matched with given input.")
    @GetMapping("/findAll/{type}")
    fun findAllEntityTemplates(
            @PathVariable type: String,
            @RequestParam(required = false) searchString: String?,
            @RequestParam(required = false) includeEntities: Boolean?) = entityTemplateLogic.findAllEntityTemplates(type, searchString, includeEntities).injectReactorContext()

    @Operation(summary = "Finding entityTemplates by userId, type and keyword.", description = "Returns a list of entityTemplates matched with given input.")
    @GetMapping("/find/{userId}/{type}/keyword/{keyword}")
    fun findEntityTemplatesByKeyword(
            @PathVariable userId: String,
            @PathVariable type: String,
            @PathVariable keyword: String,
            @RequestParam(required = false) includeEntities: Boolean?) = entityTemplateLogic.findEntityTemplatesByKeyword(userId, type, keyword, includeEntities).injectReactorContext()

    @Operation(summary = "Finding entityTemplates by entityTemplate, type and version with pagination.", description = "Returns a list of entityTemplates matched with given input.")
    @GetMapping("/findAll/{type}/keyword/{keyword}")
    fun findAllEntityTemplatesByKeyword(
            @PathVariable type: String,
            @PathVariable keyword: String,
            @RequestParam(required = false) includeEntities: Boolean?) = entityTemplateLogic.findAllEntityTemplatesByKeyword(type, keyword, includeEntities).injectReactorContext()


    @Operation(summary = "Create a EntityTemplate", description = "Type, EntityTemplate and Version are required.")
    @PostMapping
    fun createEntityTemplate(@RequestBody c: EntityTemplateDto) = mono {
        val et = entityTemplateMapper.map(c).copy(entity = c.entity)
        val entityTemplate = entityTemplateLogic.createEntityTemplate(et)
                ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "EntityTemplate creation failed.")

        entityTemplateMapper.map(entityTemplate)
    }

    @Operation(summary = "Get a list of entityTemplates by ids", description = "Keys must be delimited by coma")
    @GetMapping("/byIds/{entityTemplateIds}")
    fun getEntityTemplates(@PathVariable entityTemplateIds: String): Flux<EntityTemplateDto> {
        val entityTemplates = entityTemplateLogic.getEntityTemplates(entityTemplateIds.split(','))

        val entityTemplateDtos = entityTemplates.map { f -> entityTemplateMapper.map(f).apply { entity = f.entity } }

        return entityTemplateDtos.injectReactorContext()
    }


    @Operation(summary = "Get a entityTemplate", description = "Get a entityTemplate based on ID or (entityTemplate,type,version) as query strings. (entityTemplate,type,version) is unique.")
    @GetMapping("/{entityTemplateId}")
    fun getEntityTemplate(@Parameter(description = "EntityTemplate id", required = true) @PathVariable entityTemplateId: String) = mono {
        val c = entityTemplateLogic.getEntityTemplate(entityTemplateId)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "A problem regarding fetching the entityTemplate. Read the app logs.")

        val et = entityTemplateMapper.map(c)
        et.entity = c.entity
        et
    }

    @Operation(summary = "Modify a entityTemplate", description = "Modification of (type, entityTemplate, version) is not allowed.")
    @PutMapping
    fun modifyEntityTemplate(@RequestBody entityTemplateDto: EntityTemplateDto) = mono {
        val modifiedEntityTemplate = try {
            val et = entityTemplateMapper.map(entityTemplateDto).copy(entity = entityTemplateDto.entity)
            entityTemplateLogic.modifyEntityTemplate(et)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "A problem regarding modification of the entityTemplate. Read the app logs: " + e.message)
        }

        val succeed = modifiedEntityTemplate != null
        if (succeed) {
            modifiedEntityTemplate?.let { entityTemplateMapper.map(it) }
        } else {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Modification of the entityTemplate failed. Read the server log.")
        }
    }

    @DeleteMapping("/{entityTemplateIds}")
    @Operation(summary = "Delete entity templates")
    fun deleteEntityTemplate(@PathVariable("entityTemplateIds") entityTemplateIds: String) =
        entityTemplateLogic.deleteByIds(entityTemplateIds.split(",")).injectReactorContext()
}
