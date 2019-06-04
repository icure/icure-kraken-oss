/*
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of iCureBackend.
 *
 * iCureBackend is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * iCureBackend is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with iCureBackend.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.taktik.icure.services.external.rest.v1.facade.be

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import ma.glasnost.orika.MapperFacade
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.taktik.icure.be.ehealth.logic.kmehr.smf.SoftwareMedicalFileLogic
import org.taktik.icure.be.ehealth.logic.kmehr.medicationscheme.MedicationSchemeLogic
import org.taktik.icure.be.ehealth.logic.kmehr.sumehr.SumehrLogic
import org.taktik.icure.dto.mapping.ImportMapping
import org.taktik.icure.dto.result.CheckSMFPatientResult
import org.taktik.icure.entities.HealthElement
import org.taktik.icure.entities.HealthcareParty
import org.taktik.icure.entities.embed.Service
import org.taktik.icure.logic.DocumentLogic
import org.taktik.icure.logic.HealthcarePartyLogic
import org.taktik.icure.logic.PatientLogic
import org.taktik.icure.logic.SessionLogic
import org.taktik.icure.services.external.rest.v1.dto.HealthElementDto
import org.taktik.icure.services.external.rest.v1.dto.HealthcarePartyDto
import org.taktik.icure.services.external.rest.v1.dto.ImportResultDto
import org.taktik.icure.services.external.rest.v1.dto.be.kmehr.*
import org.taktik.icure.services.external.rest.v1.dto.embed.ContentDto
import org.taktik.icure.services.external.rest.v1.dto.embed.ServiceDto
import org.taktik.icure.services.external.rest.v1.facade.OpenApiFacade
import org.taktik.icure.utils.ResponseUtils
import java.util.Arrays
import java.util.stream.Collectors
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.Response
import javax.ws.rs.core.StreamingOutput

@Component
@Path("/be_kmehr")
@Api(tags = ["be_kmehr"])
@Consumes("application/json")
@Produces("application/json")
class KmehrFacade(val mapper: MapperFacade, val sessionLogic: SessionLogic, @Qualifier("sumehrLogicV1") val sumehrLogicV1: SumehrLogic, @Qualifier("sumehrLogicV2") val sumehrLogicV2: SumehrLogic, val softwareMedicalFileLogic: SoftwareMedicalFileLogic, val medicationSchemeLogic: MedicationSchemeLogic, val healthcarePartyLogic: HealthcarePartyLogic, val patientLogic: PatientLogic, val documentLogic: DocumentLogic) : OpenApiFacade {
    @ApiOperation(value = "Generate sumehr", httpMethod = "POST", notes = "")
    @POST
    @Path("/sumehr/{patientId}/export")
    @Produces("application/octet-stream")
    fun generateSumehr(@PathParam("patientId") patientId: String, @QueryParam("language") language: String, info: SumehrExportInfoDto): Response {
        return ResponseUtils.ok(StreamingOutput { output -> sumehrLogicV1.createSumehr(output!!, patientLogic.getPatient(patientId), info.secretForeignKeys, healthcarePartyLogic.getHealthcareParty(sessionLogic.currentSessionContext.user.healthcarePartyId), mapper!!.map<HealthcarePartyDto, HealthcareParty>(info.recipient, HealthcareParty::class.java), language, info.comment, null) })
    }

	@ApiOperation(value = "Validate sumehr", httpMethod = "POST", notes = "")
    @POST
    @Path("/sumehr/{patientId}/validate")
    @Produces("application/octet-stream")
    fun validateSumehr(@PathParam("patientId") patientId: String, @QueryParam("language") language: String, info: SumehrExportInfoDto): Response {
        return ResponseUtils.ok(StreamingOutput { output -> sumehrLogicV1.validateSumehr(output!!, patientLogic.getPatient(patientId), info.secretForeignKeys, healthcarePartyLogic.getHealthcareParty(sessionLogic.currentSessionContext.user.healthcarePartyId), mapper!!.map<HealthcarePartyDto, HealthcareParty>(info.recipient, HealthcareParty::class.java), language, info.comment, null) } as StreamingOutput)
    }

	@ApiOperation(value = "Get sumehr elements", response = SumehrContentDto::class, httpMethod = "POST", notes = "")
	@POST
	@Path("/sumehr/{patientId}/content")
	fun getSumehrContent(@PathParam("patientId") patientId: String, info: SumehrExportInfoDto): Response {
		val result = SumehrContentDto()

		result.services = sumehrLogicV1.getAllServices(sessionLogic.currentSessionContext.user.healthcarePartyId, info.secretForeignKeys, null).stream().map { s -> mapper!!.map<Service, ServiceDto>(s, ServiceDto::class.java) }.collect(Collectors.toList<ServiceDto>())
		result.healthElements = sumehrLogicV1.getHealthElements(sessionLogic.currentSessionContext.user.healthcarePartyId, info.secretForeignKeys).stream().map { h -> mapper!!.map<HealthElement, HealthElementDto>(h, HealthElementDto::class.java) }.collect(Collectors.toList<HealthElementDto>())

		return ResponseUtils.ok(result)
	}

	@ApiOperation(value = "Check sumehr signature", response = ContentDto::class, httpMethod = "GET", notes = "")
	@GET
	@Path("/sumehr/{patientId}/md5")
    fun getSumehrMd5(@PathParam("patientId") patientId: String, @QueryParam("hcPartyId") hcPartyId: String, @QueryParam("secretFKeys") secretFKeys: String): Response {
        return ResponseUtils.ok(ContentDto.fromStringValue(sumehrLogicV1.getSumehrMd5(hcPartyId, patientLogic.getPatient(patientId), Arrays.asList(*secretFKeys.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()))))
    }

    @ApiOperation(value = "Get sumehr validity", response = String::class, httpMethod = "GET", notes = "")
    @GET
    @Path("/sumehr/{patientId}/valid")
    fun isSumehrValid(@PathParam("patientId") patientId: String, @QueryParam("hcPartyId") hcPartyId: String, @QueryParam("secretFKeys") secretFKeys: String): Response {
        return ResponseUtils.ok(SumehrValidityDto(SumehrStatus.valueOf(sumehrLogicV1.isSumehrValid(hcPartyId, patientLogic.getPatient(patientId), Arrays.asList(*secretFKeys.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())).name)))
    }

    @ApiOperation(value = "Generate sumehr", httpMethod = "POST", notes = "")
    @POST
    @Path("/sumehrv2/{patientId}/export")
    @Produces("application/octet-stream")
    fun generateSumehrV2(@PathParam("patientId") patientId: String, @QueryParam("language") language: String, info: SumehrExportInfoDto): Response {
        return ResponseUtils.ok(StreamingOutput { output -> sumehrLogicV2.createSumehr(output!!, patientLogic.getPatient(patientId), info.secretForeignKeys, healthcarePartyLogic.getHealthcareParty(sessionLogic.currentSessionContext.user.healthcarePartyId), mapper!!.map<HealthcarePartyDto, HealthcareParty>(info.recipient, HealthcareParty::class.java), language, info.comment, null) })
    }

    @ApiOperation(value = "Validate sumehr", httpMethod = "POST", notes = "")
    @POST
    @Path("/sumehrv2/{patientId}/validate")
    @Produces("application/octet-stream")
    fun validateSumehrV2(@PathParam("patientId") patientId: String, @QueryParam("language") language: String, info: SumehrExportInfoDto): Response {
        return ResponseUtils.ok(StreamingOutput { output -> sumehrLogicV2.validateSumehr(output!!, patientLogic.getPatient(patientId), info.secretForeignKeys, healthcarePartyLogic.getHealthcareParty(sessionLogic.currentSessionContext.user.healthcarePartyId), mapper!!.map<HealthcarePartyDto, HealthcareParty>(info.recipient, HealthcareParty::class.java), language, info.comment, null) } as StreamingOutput)
    }

    @ApiOperation(value = "Get sumehr elements", response = SumehrContentDto::class, httpMethod = "POST", notes = "")
    @POST
    @Path("/sumehrv2/{patientId}/content")
    fun getSumehrV2Content(@PathParam("patientId") patientId: String, info: SumehrExportInfoDto): Response {
        val result = SumehrContentDto()

        result.services = sumehrLogicV2.getAllServices(sessionLogic.currentSessionContext.user.healthcarePartyId, info.secretForeignKeys, null).stream().map { s -> mapper!!.map<Service, ServiceDto>(s, ServiceDto::class.java) }.collect(Collectors.toList<ServiceDto>())
        result.healthElements = sumehrLogicV2.getHealthElements(sessionLogic.currentSessionContext.user.healthcarePartyId, info.secretForeignKeys).stream().map { h -> mapper!!.map<HealthElement, HealthElementDto>(h, HealthElementDto::class.java) }.collect(Collectors.toList<HealthElementDto>())

        return ResponseUtils.ok(result)
    }

    @ApiOperation(value = "Check sumehr signature", response = ContentDto::class, httpMethod = "GET", notes = "")
    @GET
    @Path("/sumehrv2/{patientId}/md5")
    fun getSumehrV2Md5(@PathParam("patientId") patientId: String, @QueryParam("hcPartyId") hcPartyId: String, @QueryParam("secretFKeys") secretFKeys: String): Response {
        return ResponseUtils.ok(ContentDto.fromStringValue(sumehrLogicV2.getSumehrMd5(hcPartyId, patientLogic.getPatient(patientId), Arrays.asList(*secretFKeys.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()))))
    }

    @ApiOperation(value = "Get sumehr validity", response = String::class, httpMethod = "GET", notes = "")
    @GET
    @Path("/sumehrv2/{patientId}/valid")
    fun isSumehrV2Valid(@PathParam("patientId") patientId: String, @QueryParam("hcPartyId") hcPartyId: String, @QueryParam("secretFKeys") secretFKeys: String): Response {
        return ResponseUtils.ok(SumehrValidityDto(SumehrStatus.valueOf(sumehrLogicV2.isSumehrValid(hcPartyId, patientLogic.getPatient(patientId), Arrays.asList(*secretFKeys.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())).name)))
    }

    @ApiOperation(value = "Get SMF (Software Medical File) export")
	@POST
	@Path("/smf/{patientId}/export")
	@Produces("application/octet-stream")
	fun generateSmfExport(@PathParam("patientId") patientId: String, @QueryParam("language") language: String?, smfExportParams: SoftwareMedicalFileExportDto) : Response {
		val userHealthCareParty = healthcarePartyLogic.getHealthcareParty(sessionLogic.currentSessionContext.user.healthcarePartyId)
		return ResponseUtils.ok(StreamingOutput { output -> softwareMedicalFileLogic.createSmfExport(output!!, patientLogic.getPatient(patientId), smfExportParams.secretForeignKeys, userHealthCareParty, language ?: "fr", null, null) })
	}

	@ApiOperation(value = "Get Medicationscheme export")
	@POST
	@Path("/medicationscheme/{patientId}/export")
	@Produces("application/octet-stream")
	fun generateMedicationSchemeExport(@PathParam("patientId") patientId: String, @QueryParam("language") language: String?, @QueryParam("version") version: Int, medicationSchemeExportParams: MedicationSchemeExportInfoDto) : Response {
		val userHealthCareParty = healthcarePartyLogic.getHealthcareParty(sessionLogic.currentSessionContext.user.healthcarePartyId)
		return ResponseUtils.ok(StreamingOutput { output -> medicationSchemeLogic.createMedicationSchemeExport(output!!, patientLogic.getPatient(patientId), medicationSchemeExportParams.secretForeignKeys, userHealthCareParty, language ?: "fr", version, null, null) })
	}

	@ApiOperation(value = "Import SMF into patient(s) using existing document", response = ImportResultDto::class, responseContainer = "Array")
	@POST
	@Path("/smf/{documentId}/import")
	fun importSmf(@PathParam("documentId") documentId: String, @QueryParam("documentKey") documentKey: String?, @QueryParam("patientId") patientId: String?, @QueryParam("language") language: String?, mappings: HashMap<String,List<ImportMapping>>?) : Response {
		val user = sessionLogic.currentSessionContext.user
		val userHealthCareParty = healthcarePartyLogic.getHealthcareParty(user.healthcarePartyId)
		val document = documentLogic.get(documentId)

		return ResponseUtils.ok(softwareMedicalFileLogic.importSmfFile(documentLogic.readAttachment(documentId, document.attachmentId), user, language ?: userHealthCareParty.languages?.firstOrNull() ?: "fr",
		                                                               patientId?.let { patientLogic.getPatient(patientId) },
		                                                               mappings ?: HashMap()).map {mapper.map(it, ImportResultDto::class.java)})
	}

	@ApiOperation(value = "Check whether patients in SMF already exists in DB", response = CheckSMFPatientResult::class, responseContainer = "Array")
	@POST
	@Path("/smf/{documentId}/checkIfSMFPatientsExists")
	fun checkIfSMFPatientsExists(@PathParam("documentId") documentId: String, @QueryParam("documentKey") documentKey: String?, @QueryParam("patientId") patientId: String?, @QueryParam("language") language: String?, mappings: HashMap<String,List<ImportMapping>>?) : Response {
		val user = sessionLogic.currentSessionContext.user
		val userHealthCareParty = healthcarePartyLogic.getHealthcareParty(user.healthcarePartyId)
		val document = documentLogic.get(documentId)

		return ResponseUtils.ok(softwareMedicalFileLogic.checkIfSMFPatientsExists(documentLogic.readAttachment(documentId, document.attachmentId), user, language ?: userHealthCareParty.languages?.firstOrNull() ?: "fr",
				patientId?.let { patientLogic.getPatient(patientId) },
				mappings ?: HashMap()).map {mapper.map(it, CheckSMFPatientResult::class.java)})
	}

	@ApiOperation(value = "Import SMF into patient(s) using existing document", response = ImportResultDto::class, responseContainer = "Array")
	@POST
	@Path("/sumehr/{documentId}/import")
	fun importSumehr(@PathParam("documentId") documentId: String, @QueryParam("documentKey") documentKey: String?, @QueryParam("patientId") patientId: String?, @QueryParam("language") language: String?, mappings: HashMap<String,List<ImportMapping>>?) : Response {
		val user = sessionLogic.currentSessionContext.user
		val userHealthCareParty = healthcarePartyLogic.getHealthcareParty(user.healthcarePartyId)
		val document = documentLogic.get(documentId)

		return ResponseUtils.ok(sumehrLogicV1.importSumehr(documentLogic.readAttachment(documentId, document.attachmentId), user, language ?: userHealthCareParty.languages?.firstOrNull() ?: "fr",
		                                                               patientId?.let { patientLogic.getPatient(patientId) },
		                                                               mappings ?: HashMap()).map {mapper.map(it, ImportResultDto::class.java)})
	}

	@ApiOperation(value = "Import MedicationScheme into patient(s) using existing document", response = ImportResultDto::class, responseContainer = "Array")
	@POST
	@Path("/medicationscheme/{documentId}/import")
	fun importMedicationScheme(@PathParam("documentId") documentId: String, @QueryParam("documentKey") documentKey: String?, @QueryParam("patientId") patientId: String?, @QueryParam("language") language: String?, mappings: HashMap<String,List<ImportMapping>>?) : Response {

		val user = sessionLogic.currentSessionContext.user
		val userHealthCareParty = healthcarePartyLogic.getHealthcareParty(user.healthcarePartyId)
		val document = documentLogic.get(documentId)

		return ResponseUtils.ok(medicationSchemeLogic.importMedicationSchemeFile(documentLogic.readAttachment(documentId, document.attachmentId), user, language ?: userHealthCareParty.languages?.firstOrNull() ?: "fr",
				patientId?.let { patientLogic.getPatient(patientId) },
				mappings ?: HashMap()).map {mapper.map(it, ImportResultDto::class.java)})
	}
}
