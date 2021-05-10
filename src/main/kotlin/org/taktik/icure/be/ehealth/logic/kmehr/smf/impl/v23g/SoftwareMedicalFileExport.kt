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

package org.taktik.icure.be.ehealth.logic.kmehr.smf.impl.v23g

import com.github.mustachejava.DefaultMustacheFactory
import com.github.mustachejava.Mustache
import com.github.mustachejava.MustacheFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import org.springframework.core.io.buffer.DataBuffer
import org.taktik.couchdb.exception.DocumentNotFoundException
import org.taktik.icure.asynclogic.AsyncSessionLogic
import org.taktik.icure.asynclogic.CodeLogic
import org.taktik.icure.asynclogic.ContactLogic
import org.taktik.icure.asynclogic.DocumentLogic
import org.taktik.icure.asynclogic.FormLogic
import org.taktik.icure.asynclogic.FormTemplateLogic
import org.taktik.icure.asynclogic.HealthElementLogic
import org.taktik.icure.asynclogic.HealthcarePartyLogic
import org.taktik.icure.asynclogic.InsuranceLogic
import org.taktik.icure.asynclogic.PatientLogic
import org.taktik.icure.asynclogic.UserLogic
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.Utils
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.Utils.makeMomentType
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.Utils.makeXMLGregorianCalendarFromFuzzyLong
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.Utils.makeXmlGregorianCalendar
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.be.fgov.ehealth.standards.kmehr.cd.v1.CDCONTENT
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.be.fgov.ehealth.standards.kmehr.cd.v1.CDCONTENTschemes
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTYschemes
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.be.fgov.ehealth.standards.kmehr.cd.v1.CDINCAPACITY
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.be.fgov.ehealth.standards.kmehr.cd.v1.CDINCAPACITYREASON
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.be.fgov.ehealth.standards.kmehr.cd.v1.CDINCAPACITYREASONvalues
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.be.fgov.ehealth.standards.kmehr.cd.v1.CDINCAPACITYvalues
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.be.fgov.ehealth.standards.kmehr.cd.v1.CDITEM
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.be.fgov.ehealth.standards.kmehr.cd.v1.CDITEMschemes
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.be.fgov.ehealth.standards.kmehr.cd.v1.CDLIFECYCLE
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.be.fgov.ehealth.standards.kmehr.cd.v1.CDLIFECYCLEvalues
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.be.fgov.ehealth.standards.kmehr.cd.v1.CDLNKvalues
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.be.fgov.ehealth.standards.kmehr.cd.v1.CDMEDIATYPEvalues
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.be.fgov.ehealth.standards.kmehr.cd.v1.CDTRANSACTION
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.be.fgov.ehealth.standards.kmehr.cd.v1.CDTRANSACTIONschemes
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.be.fgov.ehealth.standards.kmehr.cd.v1.LnkType
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.be.fgov.ehealth.standards.kmehr.dt.v1.TextType
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTYschemes
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.be.fgov.ehealth.standards.kmehr.id.v1.IDINSURANCE
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.be.fgov.ehealth.standards.kmehr.id.v1.IDINSURANCEschemes
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHRschemes
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.be.fgov.ehealth.standards.kmehr.schema.v1.AuthorType
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.be.fgov.ehealth.standards.kmehr.schema.v1.ContentType
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.be.fgov.ehealth.standards.kmehr.schema.v1.FolderType
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.be.fgov.ehealth.standards.kmehr.schema.v1.IncapacityType
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.be.fgov.ehealth.standards.kmehr.schema.v1.IncapacityreasonType
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.be.fgov.ehealth.standards.kmehr.schema.v1.InsuranceType
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.be.fgov.ehealth.standards.kmehr.schema.v1.ItemType
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.be.fgov.ehealth.standards.kmehr.schema.v1.LifecycleType
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.be.fgov.ehealth.standards.kmehr.schema.v1.RecipientType
import org.taktik.icure.be.ehealth.dto.kmehr.v20131001.be.fgov.ehealth.standards.kmehr.schema.v1.TransactionType
import org.taktik.icure.be.ehealth.logic.kmehr.Config
import org.taktik.icure.be.ehealth.logic.kmehr.emitMessage
import org.taktik.icure.be.ehealth.logic.kmehr.v20131001.KmehrExport
import org.taktik.couchdb.id.UUIDGenerator
import org.taktik.icure.entities.Contact
import org.taktik.icure.entities.Form
import org.taktik.icure.entities.HealthElement
import org.taktik.icure.entities.HealthcareParty
import org.taktik.icure.entities.Patient
import org.taktik.icure.entities.base.CodeStub
import org.taktik.icure.entities.base.ICureDocument
import org.taktik.icure.entities.embed.Insurability
import org.taktik.icure.entities.embed.ReferralPeriod
import org.taktik.icure.entities.embed.Service
import org.taktik.icure.entities.embed.SubContact
import org.taktik.icure.services.external.api.AsyncDecrypt
import org.taktik.icure.services.external.http.websocket.AsyncProgress
import org.taktik.icure.services.external.rest.v1.dto.ContactDto
import org.taktik.icure.services.external.rest.v1.mapper.ContactMapper
import org.taktik.icure.utils.FuzzyValues
import java.io.StringWriter
import java.time.Instant
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*
import javax.xml.datatype.DatatypeConstants

/**
 * @author Bernard Paulus on 29/05/17.
 */
@org.springframework.stereotype.Service
class SoftwareMedicalFileExport(
        val formLogic: FormLogic,
        val formTemplateLogic: FormTemplateLogic,
        val insuranceLogic: InsuranceLogic,
        patientLogic: PatientLogic,
        codeLogic: CodeLogic,
        healthElementLogic: HealthElementLogic,
        healthcarePartyLogic: HealthcarePartyLogic,
        contactLogic: ContactLogic,
        documentLogic: DocumentLogic,
        sessionLogic: AsyncSessionLogic,
        userLogic: UserLogic,
        filters: org.taktik.icure.asynclogic.impl.filter.Filters,
        val contactMapper: ContactMapper
) : KmehrExport(patientLogic, codeLogic, healthElementLogic, healthcarePartyLogic, contactLogic, documentLogic, sessionLogic, userLogic, filters) {
	private var hesByContactId: Map<String?, List<HealthElement>> = HashMap()
    private var servicesByContactId: Map<String?, List<Service>> = HashMap()
    private var newestServicesById: MutableMap<String?, Service> = HashMap()
	private var itemByServiceId: MutableMap<String, ItemType> = HashMap()
	private var oldestHeByHeId: Map<String?, HealthElement> = HashMap()

	fun exportSMF(
			patient: Patient,
			sfks: List<String>,
			sender: HealthcareParty,
			language: String,
			decryptor: AsyncDecrypt?,
			progressor: AsyncProgress?,
			config: Config = Config(
                    _kmehrId = System.currentTimeMillis().toString(),
					date = makeXGC(Instant.now().toEpochMilli())!!,
					time = makeXGC(Instant.now().toEpochMilli(), true)!!,
					soft = Config.Software(name = "iCure", version = ICUREVERSION),
					clinicalSummaryType = "TODO", // not used
					defaultLanguage = "en",
					format = Config.Format.SMF
            )) : Flow<DataBuffer> = flow {

        log.info("Starting SMF export for "+patient.id)
        // fill missing config with default values
        config._kmehrId = config._kmehrId ?: System.currentTimeMillis().toString()
        config.date = config.date ?: makeXGC(Instant.now().toEpochMilli())!!
        config.time = config.time ?: makeXGC(Instant.now().toEpochMilli(), true)!!
        config.soft = config.soft ?: Config.Software(name = "iCure", version = ICUREVERSION)
        config.defaultLanguage = config.defaultLanguage ?: "en"
        config.format = config.format ?: Config.Format.SMF

		val sfksUniq = sfks.toSet().toList() // duplicated sfk cause couchDb views to return duplicated results

		val message = initializeMessage(sender, config)
		message.header.recipients.add(RecipientType().apply {
			hcparties.add(HcpartyType().apply {
				cds.add(CDHCPARTY().apply { s = CDHCPARTYschemes.CD_HCPARTY; value = "application" })
				name = if(config.format == Config.Format.PMF) {
					"gp-patient-migration"
				} else {
					"gp-software-migration"
				}
			})
		})

        val folder = makePatientFolder(1, patient, sfksUniq, sender, config, language, decryptor, progressor)
        emitMessage(folder, message).collect { emit(it) }
	}



    suspend fun makePatientFolder(patientIndex: Int, patient: Patient, sfks: List<String>,
								  healthcareParty: HealthcareParty, config: Config, language: String, decryptor: AsyncDecrypt?, progressor: AsyncProgress?): FolderType {
        log.info("Make patient export for "+patient.id)
		val folder = FolderType().apply {
			ids.add(idKmehr(patientIndex))
			this.patient = makePatient(patient, config)
		}
		folder.transactions.add(TransactionType().apply {
			ids.add(idKmehr(0))
			ids.add(IDKMEHR().apply { s = IDKMEHRschemes.LOCAL; sl = "MF-ID"; sv = "1.0"; value = UUIDGenerator().newGUID().toString() })
			cds.add(CDTRANSACTION().apply { s = CDTRANSACTIONschemes.CD_TRANSACTION; value = "clinicalsummary" })
			date = config.date
			time = config.time
			author = AuthorType().apply {
				hcparties.add(
                        healthcarePartyLogic.getHealthcareParty(patient.author?.let { userLogic.getUser(it)?.healthcarePartyId } ?: healthcareParty.id)?.let { createParty(it) }
                )
			}
			isIscomplete = true
			isIsvalidated = true
			getLastGmdManager(patient).let { (hcp, period) ->
				if (hcp != null && period != null) {
					makeGmdManager(headingsAndItemsAndTexts.size + 1, config, hcp, period)?.let { headingsAndItemsAndTexts.add(it) }
				}
			}
			headingsAndItemsAndTexts.addAll(makeContactPeople(headingsAndItemsAndTexts.size + 1, patient, config))
			makeInsurancyStatus(headingsAndItemsAndTexts.size + 1, config, patient.insurabilities.find { it.endDate == null || FuzzyValues.getDateTime(it.endDate).isAfter(LocalDateTime.now()) })?.let { headingsAndItemsAndTexts.add(it) }
		})

		val contacts = getAllContacts(healthcareParty, sfks.toList()).sortedBy {
			it.openingDate
		}
		val startIndex = folder.transactions.size

		hesByContactId = getNonConfidentialItems(getHealthElements(healthcareParty, sfks, config)).groupBy {
			it.idOpeningContact
		}

        // in PMF, we only want the last version, older versions are removed from servicesByContactId
        servicesByContactId = contacts.map { con ->
            con.id to con.services.toList().map { svc ->
                svc.also { it.id?.let { svcId -> newestServicesById[svcId] = svc } }
            }
        }.toMap()

		val hesByHeIdSortedByDate = getNonConfidentialItems(getHealthElements(healthcareParty, sfks, config)).groupBy {
			it.healthElementId
		}.mapValues {
			it.value.sortedWith(compareBy({ it.created },{ it.modified })) // created is the key, but use modified for backward compat
			// oldest He is first in list
		}
		oldestHeByHeId = hesByHeIdSortedByDate.mapValues {
			it.value.first()
		}

        if(config.format == Config.Format.PMF) { // only last version in PMF
            hesByContactId = hesByContactId.map { entry ->
                entry.key to entry.value.filter({ he : HealthElement  -> hesByHeIdSortedByDate[he.healthElementId]?.last()?.id == he.id })
            }.toMap()
        }

		// add Hes without idOpeningContact to clinical summary
		hesByContactId[null].orEmpty().map { he -> addHealthCareElement(folder.transactions.first(), he, 0, config) }
		hesByContactId = hesByContactId.filterKeys { it != null }

		val heById = getNonConfidentialItems(getHealthElements(healthcareParty, sfks, config)).groupBy {
			// retrive the healthElementId property of an HE by his couchdb id
			it.id
		}

		var documents = emptyList<Triple<String,Service,Contact>>()
        var pharmaceuticalPrescriptions = emptyList<Pair<Service,Contact>>()
		val specialPrescriptions = mutableListOf<TransactionType>()
        val summaries = mutableListOf<TransactionType>()

		contacts.forEachIndexed { index, encContact ->
			progressor?.progress((1.0 * index) / (contacts.size + documents.size))
            log.info("Treating contact ${index}/${contacts.size}")

            log.info("Decrypt "+encContact.id)

            val contact = if (decryptor != null && (encContact.services.isNotEmpty())) {
                val ctcDto = contactMapper.map(encContact)
				decryptor.decrypt(listOf(ctcDto), ContactDto::class.java).firstOrNull()?.let { contactMapper.map(it) } ?: encContact
			} else {
				encContact
			}

			folder.transactions.add(
                    TransactionType().apply {
                        var services: List<Service> = excludesServiceForPMF(contact.services.toList(), config)
                        val trn = this

                        val (cdTransactionRef, defaultCdItemRef, exportAsDocument) = when (contact.encounterType?.code) {
                            "labresult" -> Triple("labresult", "lab", false)
                            else -> {
                                if (contact.tags?.any { it.type == "CD-TRANSACTION" && it.code == "labresult" } == true) {
                                    Triple("labresult", "lab", true)
                                } else {
                                    Triple("contactreport", "parameter", false)
                                }
                            }
                        }

                        ids.add(idKmehr(startIndex))
                        ids.add(IDKMEHR().apply { s = IDKMEHRschemes.LOCAL; sl = "MF-ID"; value = contact.id })
                        cds.add(CDTRANSACTION().apply { s = CDTRANSACTIONschemes.CD_TRANSACTION; value = cdTransactionRef })
                        (contact.modified ?: contact.created)?.let {
                            date = makeXGC(it)
                            time = makeXGC(it, unsetMillis = true)
                        } ?: also {
                            date = config.date
                            time = makeXGC(0L, unsetMillis = true)
                        }
                        (contact.responsible ?: healthcareParty.id)?.let {
                            author = AuthorType().apply { hcparties.add(createParty(healthcarePartyLogic.getHealthcareParty(it)!!, emptyList())) }
                        }
                        isIscomplete = true
                        isIsvalidated = true
                        contact.openingDate?.let { headingsAndItemsAndTexts.add(makeEncounterDateTime(headingsAndItemsAndTexts.size + 1, it)) }
                        contact.location?.let { headingsAndItemsAndTexts.add(makeEncounterLocation(headingsAndItemsAndTexts.size + 1, it, language)) }
                        contact.encounterType?.let { headingsAndItemsAndTexts.add(makeEncounterType(headingsAndItemsAndTexts.size + 1, it)) }

                        hesByContactId[contact.id].orEmpty().map { he -> addHealthCareElement(trn, he, 0, config) }

                        hesByContactId = hesByContactId.filterKeys { it != contact.id } // prevent re-using the same He for the next subcontact

                        // Special code for specific forms
                        contact.subContacts.forEach { subcon ->
                            //TODO: Please explain
                            if (subcon.healthElementId == null) { // discard form <-> he links
                                subcon.formId?.let {
                                    formLogic.getForm(it)?.let { form ->
                                        form.formTemplateId?.let {
                                            try {
                                                formTemplateLogic.getFormTemplateById(it)?.let {
                                                    when (it.guid) {
                                                        "FFFFFFFF-FFFF-FFFF-FFFF-INCAPACITY00" -> { // ITT
                                                            services = services.filterNot { subcon.services.map { it.serviceId }.contains(it.id) } // remove form services from main list
                                                            trn.headingsAndItemsAndTexts.add(makeIncapacityItem(contact, subcon, form))
                                                        }
                                                        "AEFED10A-9A72-4B40-981B-1D79ADB05516" -> { // Prescription Kine
                                                            services = services.filterNot { subcon.services.map { it.serviceId }.contains(it.id) }
                                                            specialPrescriptions.add(makeKinePrescriptionTransaction(contact, subcon, form))
                                                        }
                                                        "64DAB551-B007-4B5C-BD64-F886301F5326" -> { // Prescription Nurse
                                                            services = services.filterNot { subcon.services.map { it.serviceId }.contains(it.id) }
                                                            // get subforms
                                                            val subformsubcons = contact.subContacts.filter { subformsubcon ->
                                                                subformsubcon.formId?.let {
                                                                    formLogic.getForm(it)?.let { subform ->
                                                                        subform.parent == form.id

                                                                    }
                                                                } == true
                                                            }
                                                            subformsubcons.forEach { subformsubcon ->
                                                                services = services.filterNot { subformsubcon.services.map { it.serviceId }.contains(it.id) }
                                                            }
                                                            specialPrescriptions.add(makeNursePrescriptionTransaction(contact, subcon, subformsubcons, form))
                                                        }
                                                        else -> Unit

                                                    }
                                                }
                                            } catch (e:Exception) {
                                                log.error("Cannot load form template $it")
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        contact.services.filter { s -> s.tags.find { t -> t.code == "incapacity" } != null }.forEach { incapacityService ->
                            headingsAndItemsAndTexts.add(makeIncapacityItem(healthcareParty, incapacityService))
                            incapacityService.content[language]?.documentId?.let { docId ->
                                createLinkToDocument(docId, healthcareParty, incapacityService, folder, language, config)
                            }
                        }
                        contact.services.filter { s -> s.tags.find { t -> t.code == "physiotherapy" } != null }.forEach { kineService ->
                            specialPrescriptions.add(makeKinePrescriptionTransaction(kineService))
                        }
                        contact.services.filter { s -> s.tags.find { t -> t.code == "medicalcares" } != null }.forEach { nurseService ->
                            specialPrescriptions.add(makeNursePrescriptionTransaction(nurseService))
                        }

                        contact.services.filter { s -> isSummary(s) }.forEach { summaryService ->
                            summaries.add(makeSummaryTransaction(contact, summaryService))
                        }

                        // services
                        if (exportAsDocument && services.size == 1) {
                            services[0].content.values.forEach { doc ->
                                doc.stringValue?.let { headingsAndItemsAndTexts.add(LnkType().apply { type = CDLNKvalues.MULTIMEDIA; mediatype = CDMEDIATYPEvalues.TEXT_PLAIN; value = it.toByteArray(Charsets.UTF_8) }) }
                            }
                        } else {
                            services.forEach servicesLoop@{ svc ->
                                val incapacityTag = svc.tags.find { t -> t.code == "incapacity" }
                                if (incapacityTag != null) return@servicesLoop
                                var forSeparateTransaction = false // documents are in separate transaction in *MF
                                svc.content.values.find { it.documentId != null }?.let {
                                    documents = documents.plus(Triple(it.documentId!!, svc, contact))
                                    forSeparateTransaction = true
                                }

                                // prescriptions are in separate transaction in *MF
                                // icure prescriptions have another tag (and in separate transaction in *MF)
                                svc.tags.find {
                                    (it.type == "CD-ITEM" && it.code == "treatment")
                                            || (it.type == "ICURE" && it.code == "PRESC")
                                }?.let {
                                    pharmaceuticalPrescriptions = pharmaceuticalPrescriptions.plus(Pair(svc, contact))
                                    forSeparateTransaction = true
                                }
                                forSeparateTransaction = forSeparateTransaction || isSummary(svc)

                                if (!forSeparateTransaction) {
                                    val svcCdItem = svc.tags.filter { it.type == "CD-ITEM" }.firstOrNull()

                                    val cdItem = (svcCdItem?.code ?: defaultCdItemRef).let {
                                        if (it == "parameter") {
                                            svc.content.let {
                                                it.entries.firstOrNull()?.value?.measureValue?.let { "parameter" }
                                                        ?: "clinical" // FIXME: change to clinical instead of technical because medinote doesnt support technical
                                            } //Change parameters to technicals if not real parameters
                                        } else it
                                    }


                                    val contents: List<ContentType> = svc.content.entries.flatMap {
                                        makeContent(it.key, it.value)?.let { c ->
                                            listOf(c.apply {
                                                if (svcCdItem == null && texts.size > 0) {
                                                    if (svc.label != "<invalid>" && !texts.first().value.startsWith(svc.label)) {
                                                        texts.first().value = "${svc.label}: ${texts.first().value}"
                                                    }
                                                }
                                            })
                                        } ?: emptyList()
                                    } + codesToKmehr(svc.codes)
                                    if (contents.isNotEmpty()) {
                                        val item = createItemWithContent(svc, headingsAndItemsAndTexts.size + 1, cdItem, contents, "MF-ID")?.apply {
                                            this.ids.add(IDKMEHR().apply {
                                                this.s = IDKMEHRschemes.LOCAL
                                                this.sv = "1.0"
                                                this.sl = "org.taktik.icure.label"
                                                this.value = svc.label
                                            })
                                            if (cdItem == "parameter") {
                                                svc.tags.find { it.type == "CD-PARAMETER" }?.let {
                                                    this.cds.add(
                                                            CDITEM().apply {
                                                                s = CDITEMschemes.CD_PARAMETER

                                                                value = it.code
                                                            }
                                                    )
                                                }
                                                if(!svc.label.isNullOrEmpty()) {
                                                    this.cds.add(
                                                            CDITEM().apply {
                                                                s = CDITEMschemes.LOCAL
                                                                sl = "LOCAL-PARAMETER"
                                                                sv = "1.0"
                                                                dn = if (svc.comment == "" || svc.comment == null) {
                                                                    svc.label
                                                                } else {
                                                                    svc.comment
                                                                }
                                                                value = svc.label
                                                            }
                                                    )
                                                }
                                            }
                                            if (cdItem == "medication") {
                                                svc.content.values.find { it.medicationValue?.instructionForPatient != null }?.let {
                                                    this.posology = ItemType.Posology().apply {
                                                        text = TextType().apply { l = language; value = it.medicationValue!!.instructionForPatient }
                                                    }
                                                }
                                            }
                                            if (cdItem == "vaccine") {
                                                svc.content.values.firstOrNull { it.medicationValue != null }?.medicationValue?.batch?.let {
                                                    this.batch = it
                                                }
                                            }

                                            svc.comment?.let {
                                                (it != "") && it.let {
                                                    this.contents.add(ContentType().apply { texts.add(TextType().apply { l = language; value = it }) })
                                                }
                                            }

                                            addHistoryLinkAndCacheService(this, svc, config)
                                            headingsAndItemsAndTexts.add(this)
                                        }
                                    }
                                }
                            }
                        }

                        // add link from items to HEs
                        contact.subContacts.forEach { subcon ->
                            if (subcon.healthElementId != null) {
                                subcon.services.forEach {
                                    itemByServiceId[it.serviceId]?.lnks?.let {
                                        val lnk = LnkType().apply {
                                            type = CDLNKvalues.ISASERVICEFOR
                                            // link should point to He.healthElementId and not He.id
                                            subcon.healthElementId?.let {
                                                heById[it]?.firstOrNull()?.healthElementId?.let {
                                                    url = makeLnkUrl(it)
                                                }
                                            }
                                        }
                                        if (it.none { (it.type == lnk.type) && (it.url == lnk.url) }) {
                                            it.add(lnk)
                                        }
                                    }
                                }
                            }
                        }
                    }
            )
			Unit
		}

		pharmaceuticalPrescriptions.forEachIndexed{ index, it ->
			progressor?.progress((1.0 * (index + contacts.size)) / (contacts.size + documents.size))
			val (svc, con) = it
            folder.transactions.add( TransactionType().apply {
                ids.add(idKmehr(startIndex))
                ids.add(IDKMEHR().apply { s = IDKMEHRschemes.LOCAL; sl = "MF-ID"; sv = "1.0"; value = svc.id })
                cds.add(CDTRANSACTION().apply { s = CDTRANSACTIONschemes.CD_TRANSACTION; value = "pharmaceuticalprescription" })
                (svc.modified ?: svc.created) ?.let {
                    date = makeXGC(it)
                    time = makeXGC(it, unsetMillis = true)
                } ?: also {
                    date = config.date
                    time = makeXGC(0L, unsetMillis = true)
                }
                (svc.responsible ?: healthcareParty.id) ?.let {
                    author = AuthorType().apply { hcparties.add(createParty(healthcarePartyLogic!!.getHealthcareParty(it)!!, emptyList())) }
                }
                isIscomplete = true
                isIsvalidated = true
                recorddatetime = Utils.makeXGC(svc.modified, true)
                var svcCdItem = svc.tags.filter { it.type == "CD-ITEM" }.firstOrNull() // is treatement in topaz but should be medication in kmehr
                val cdItem = "medication" // force medication
                var contents: List<ContentType> = svc.content.entries.flatMap {
                    makeContent(it.key, it.value)?.let { c ->
                        listOf(c.apply {
                            if (svcCdItem == null && texts.size > 0) {
                                if(svc.label != null) {
                                texts.first().value = "${svc.label}: ${texts.first().value}"
                            }
                            }
                        })
                    } ?: emptyList()
                }
                contents += codesToKmehr(svc.codes)
                if (contents.isNotEmpty()) {
                    val item = createItemWithContent(svc, headingsAndItemsAndTexts.size + 1, cdItem, contents, "MF-ID")?.apply {
                        this.ids.add(IDKMEHR().apply {
                            this.s = IDKMEHRschemes.LOCAL
                            this.sv = "1.0"
                            this.sl = "org.taktik.icure.label"
                            this.value = svc.label
                        })
                        if(cdItem == "medication") {
                            svc.content.values.find{ it.medicationValue?.instructionForPatient != null}?.let {
                                this.posology = ItemType.Posology().apply {
                                    text = TextType().apply { l = language; value = it.medicationValue!!.instructionForPatient }
                                }
                            }
                        }
                        svc.comment?.let {
                            (it != "") && it.let{
                                this.contents.add( ContentType().apply { texts.add(TextType().apply { l = language; value = it }) })
                            }
                        }
                        addHistoryLinkAndCacheService(this, svc, config)
                        headingsAndItemsAndTexts.add(this)
                    }
                }
                // FIXME: prescriptions should be linked to medication with a ISATTESTATIONOF link but there is no such link in topaz
				headingsAndItemsAndTexts.add(LnkType().apply { type = CDLNKvalues.ISACHILDOF; url = makeLnkUrl(con.id) })
			})
		}

        documents.forEachIndexed{ index, it ->
            try {
                progressor?.progress((1.0 * (index + contacts.size)) / (contacts.size + documents.size))
                val (docid, svc, con) = it
                folder.transactions.add(TransactionType().apply {
                    ids.add(idKmehr(startIndex))
                    ids.add(IDKMEHR().apply { s = IDKMEHRschemes.LOCAL; sl = "MF-ID"; sv = "1.0"; value = svc.id })
                    cds.add(CDTRANSACTION().apply { s = CDTRANSACTIONschemes.CD_TRANSACTION; value = "note"; dn = svc.content["fr"]?.stringValue })
                    (svc.modified ?: svc.created)?.let {
                        date = makeXGC(it)
                        time = makeXGC(it, unsetMillis = true)
                    } ?: also {
                        date = config.date
                        time = makeXGC(0L, unsetMillis = true)
                    }
                    (svc.responsible ?: healthcareParty.id)?.let {
                        author = AuthorType().apply { hcparties.add(createParty(healthcarePartyLogic!!.getHealthcareParty(it)!!, emptyList())) }
                    }
                    isIscomplete = true
                    isIsvalidated = true
                    recorddatetime = Utils.makeXGC(svc.modified, true)
                    svc.comment?.let {
                        headingsAndItemsAndTexts.add(TextType().apply {
                            l = language
                            value = svc.comment
                        })
                    }
                    documentLogic?.get(docid)?.let { d -> d.attachment?.let { headingsAndItemsAndTexts.add(LnkType().apply { type = CDLNKvalues.MULTIMEDIA; mediatype = documentMediaType(d); value = it }) } }
                    headingsAndItemsAndTexts.add(LnkType().apply { type = CDLNKvalues.ISACHILDOF; url = makeLnkUrl(con.id) })
                })
            } catch(e:Exception) {
                log.error("Cannot export document ${it.first}")
            }
        }

		specialPrescriptions.forEach {
			folder.transactions.add(it)
		}

        summaries.forEach {
            folder.transactions.add(it)
        }

		renumberKmehrIds(folder)
		return folder
	}

    private fun addHistoryLinkAndCacheService(item: ItemType, svc: Service, config: Config) {
        svc.id.let { svcId ->
            if (itemByServiceId[svcId] != null && config.format != Config.Format.PMF) {
                // this is a new version of and older service, add a link
                // no history in PMF
                item.lnks.add(
                        LnkType().apply {
                            type = CDLNKvalues.ISANEWVERSIONOF; url = makeLnkUrl(svcId)
                        }
                )
            }
            itemByServiceId[svcId] = item
        }
    }

    private suspend fun makeNursePrescriptionTransaction(contact: Service): TransactionType {
        return makeSpecialPrescriptionTransaction(contact, "nursing")
    }

    private suspend fun makeKinePrescriptionTransaction(contact: Service): TransactionType {
        return makeSpecialPrescriptionTransaction(contact, "physiotherapy")
    }

    private suspend fun makeSpecialPrescriptionTransaction(service: Service, transactionType: String): TransactionType {
        val lang = "fr" // FIXME: hardcoded "fr" but not sure if other languages can be used

        return TransactionType().apply {
            ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; value = "1" })
            ids.add(IDKMEHR().apply { s = IDKMEHRschemes.LOCAL; sl = "MF-ID"; value = service.id })
            cds.add(CDTRANSACTION().apply { s = CDTRANSACTIONschemes.CD_TRANSACTION; value = "prescription" })
            cds.add(CDTRANSACTION().apply { s = CDTRANSACTIONschemes.CD_TRANSACTION_TYPE; value = transactionType })
            (service.modified ?: service.created)?.let {
                date = makeXGC(it)
                time = makeXGC(it, unsetMillis = true)
            }
            service.responsible?.let {
                author = AuthorType().apply { hcparties.add(createParty(healthcarePartyLogic!!.getHealthcareParty(it)!!, emptyList())) }
            }
            service.created?.let {
                recorddatetime = Utils.makeXGC(it)
            }
            isIscomplete = true
            isIsvalidated = true

            service.content[lang]?.documentId?.let {
                try{
                    documentLogic?.get(it)?.let { d -> d.attachment?.let { headingsAndItemsAndTexts.add(LnkType().apply { type = CDLNKvalues.MULTIMEDIA; mediatype = documentMediaType(d); value = it }) } }
                } catch(e:Exception) {
                    log.error("Cannot export document ${it}")
                }
            }
        }
    }

    private fun makeIncapacityItem(healthcareParty: HealthcareParty, service: Service, index: Number = 0): ItemType {
        val lang = "fr" // FIXME: hardcoded "fr" but not sure if other languages can be used

        fun getCompoundValueContent(label: String) = service.content?.get(lang)?.compoundValue?.firstOrNull { it.label == label }?.content?.values?.firstOrNull()
        fun getCompoundValueTag(label: String, tagType: String) = service.content?.get(lang)?.compoundValue?.firstOrNull { it.label == label }?.tags?.find { it.type == tagType }
        return ItemType().apply {
            ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; value = index.toString() })
            ids.add(IDKMEHR().apply { s = IDKMEHRschemes.LOCAL; sl = "MF-ID"; sv = ICUREVERSION; value = service.id })
            cds.add(CDITEM().apply { s(CDITEMschemes.CD_ITEM); value = "incapacity" })
            service.tags?.firstOrNull { it.type == "MS-INCAPACITYTYPE" }?.code?.let { incapacityType ->
                cds.add(CDITEM().apply { s = CDITEMschemes.LOCAL; value = incapacityType; sl = "PMF-PARAMETER"; l = lang; dn="incapacity type"  })
            }

            contents.add(ContentType().apply {
                incapacity = IncapacityType().apply {
                    (service.tags?.find { it.type == "CD-INCAPACITY" }
                            ?: service.tags?.find { it.type == "CD-INCAPACITY-EXT" })?.let { incapacityTag ->
                        cds.add(
                                CDINCAPACITY().apply {
                                    try {
                                        value = CDINCAPACITYvalues.fromValue(incapacityTag.code)
                                    } catch (e: IllegalArgumentException) {
                                        // TODO ignored for now should be other
                                    }
                                }
                        )
                        getCompoundValueContent("Percentage")?.numberValue?.let {
                            percentage = it.toBigDecimal()
                        }
                    }
                    (getCompoundValueTag("Reason", "CD-INCAPACITYREASON")?.code
                            ?: getCompoundValueTag("Reason", "CD-INCAPACITYREASON-EXT")?.code)
                            ?.let { reasonValue ->
                                incapacityreason = IncapacityreasonType().apply {
                                    cd = CDINCAPACITYREASON().apply {
                                        value = try {
                                            CDINCAPACITYREASONvalues.fromValue(reasonValue)
                                        } catch (e: IllegalArgumentException) {
                                            CDINCAPACITYREASONvalues.fromValue("other")
                                        }
                                    }
                                }
                            }
                    getCompoundValueTag("Outing", "MS-INCAPACITYOUTING")?.code?.let { outingCode ->
                        isOutofhomeallowed = when (outingCode) {
                            "allowed", "notrecommended" -> true
                            else -> false
                        }
                    }

                    getCompoundValueContent("Diagnosis")?.stringValue?.let { diagnosisText ->
                        texts.add(TextType().apply {
                            l = lang
                            value = diagnosisText
                        })
                    }

                }
            })

            lifecycle = LifecycleType().apply {
                cd = CDLIFECYCLE().apply {
                    value = service.tags?.find { t -> t.type == "CD-LIFECYCLE" }?.let { CDLIFECYCLEvalues.fromValue(it.code) }
                            ?: CDLIFECYCLEvalues.ACTIVE
                }
            }
            isIsrelevant = true
            getCompoundValueContent("StartDate")?.instantValue?.let {
                beginmoment = makeMomentType(it)
            }
            getCompoundValueContent("EndDate")?.instantValue?.let {
                endmoment = makeMomentType(it)
            }
            service.modified?.let {
                recorddatetime = Utils.makeXGC(it, true)
            }
        }
    }

    private fun isSummary(s: Service) = s.label == "Summary" && s.tags.find { t -> t.code == "summary" } != null
    private suspend fun makeSummaryTransaction(contact: Contact, service: Service): TransactionType {
        val defaultLanguage = "fr";
        fun getCompoundValueContent(service: Service, label: String) = service.content?.get(defaultLanguage)?.compoundValue?.firstOrNull { it.label == label }?.content?.get(defaultLanguage);
        fun getCompoundValueTag(service: Service, label: String, tagType: String) = service.content?.get(defaultLanguage)?.compoundValue?.firstOrNull { it.label == label }?.tags?.find { it.type == tagType };

        return TransactionType().apply {
            val title = getCompoundValueContent(service, "SummaryTitle")
            val content = getCompoundValueContent(service, "SummaryContent")
            val cdMediaType = getCompoundValueTag(service, "SummaryContent", "CD-MEDIATYPE")
            val mediaType = try {
                CDMEDIATYPEvalues.fromValue(cdMediaType?.code)
            } catch (ignored: IllegalArgumentException) {
                null
            }

            ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; value = "1" })
            ids.add(IDKMEHR().apply { s = IDKMEHRschemes.LOCAL; sl = "MF-ID"; value = service.id })
            cds.add(CDTRANSACTION().apply { s = CDTRANSACTIONschemes.CD_TRANSACTION; value = "note"; dn = title?.stringValue })
            (service.modified ?: service.created)?.let {
                date = makeXGC(it)
                time = makeXGC(it, unsetMillis = true)
            }
            service.responsible?.let {
                author = AuthorType().apply { hcparties.add(createParty(healthcarePartyLogic!!.getHealthcareParty(it)!!, emptyList())) }
            }
            service.created?.let {
                recorddatetime = Utils.makeXGC(it)
            }
            isIscomplete = true
            isIsvalidated = true

            headingsAndItemsAndTexts.add(LnkType().apply { type = CDLNKvalues.MULTIMEDIA; mediatype = mediaType; value = content?.stringValue?.toByteArray() })
            headingsAndItemsAndTexts.add(LnkType().apply { type = CDLNKvalues.ISACHILDOF; url = makeLnkUrl(contact.id!!) })
        }
    }

    private suspend fun createLinkToDocument(documentId: String, healthcareParty: HealthcareParty, service: Service, folder: FolderType, language: String, config: Config){
        try {
            folder.transactions.add(TransactionType().apply {
                ids.add(IDKMEHR().apply { s = IDKMEHRschemes.LOCAL; sl = "MF-ID"; sv = "1.0"; value = service.id })
                cds.add(CDTRANSACTION().apply { s = CDTRANSACTIONschemes.CD_TRANSACTION; value = "note" })
                (service.modified ?: service.created)?.let {
                    date = makeXGC(it)
                    time = makeXGC(it, unsetMillis = true)
                }
                        ?: also {
                            date = config.date
                            time = makeXGC(0L, unsetMillis = true)
                        }
                (service.responsible ?: healthcareParty.id)?.let {
                    author = AuthorType().apply { hcparties.add(createParty(healthcarePartyLogic!!.getHealthcareParty(it)!!, emptyList())) }
                }
                isIscomplete = true
                isIsvalidated = true
                recorddatetime = Utils.makeXGC(service.modified, true)
                service.comment?.let {
                    headingsAndItemsAndTexts.add(TextType().apply {
                        l = language
                        value = service.comment
                    })
                }
                documentLogic?.get(documentId)?.let { d -> d.attachment?.let { headingsAndItemsAndTexts.add(LnkType().apply { type = CDLNKvalues.MULTIMEDIA; mediatype = documentMediaType(d); value = it }) } }
                LnkType().apply { type = CDLNKvalues.ISACHILDOF; url = makeLnkUrl(service.id!!) }.also { headingsAndItemsAndTexts.add(it) }
            })
        } catch(e:Exception) {
          log.error("Cannot export document ${documentId}")
        }
    }

    private fun makeIncapacityItem(contact: Contact, subcon: SubContact, form: Form, index: Number = 0): ItemType {
		val lang = "fr" // FIXME: hardcoded "fr" but not sure if other languages can be used
		val servlist = listOf(
				"incapacité de",
				"du",
				"au",
				"inclus/exclus",
				"pour cause de",
				"Accident suvenu le",
				"Sortie",
				"autres",
				"reprise d'activité partielle",
				"pourcentage",
				"totale",
				"Commentaire"
		)
		val servmap = contact.services.filter { serv -> subcon.services.map { it.serviceId }.contains(serv.id) }.associateBy({ it.label }, { it })

		fun getServiceFor(label: String, default: String): String {
			return servmap[label]?.content?.let { (it[lang] ?: it.values.firstOrNull())?.stringValue } ?: default
		}

		val content = ContentType().apply {
			incapacity = IncapacityType().apply {
                cds.add(
                        CDINCAPACITY().apply {
                            // the right way is to use the CodeStub, but the previous ITT form was free text and now it's the id of the code in
                            // the free text
                            var svcvalue : String = getServiceFor("incapacité de", "work")
                            try {
                                if(svcvalue.contains("|")) {
                                    svcvalue = svcvalue.split("|")[1]
                                }
                                value = CDINCAPACITYvalues.fromValue(svcvalue)
                            } catch(e : Exception) {
                                // TODO: add a warning in the log
                                svcvalue = "work"
                                value = CDINCAPACITYvalues.fromValue(svcvalue)
                            }
                        }
                )
				incapacityreason = IncapacityreasonType().apply {
					cd = CDINCAPACITYREASON().apply {
                        // the right way is to use the CodeStub, but the previous ITT form was free text and now it's the id of the code in
                        // the free text
                        var svcvalue : String = getServiceFor("pour cause de", "sickness")
                        try {
                            if(svcvalue.contains("|")) {
                                svcvalue = svcvalue.split("|")[1]
                            }
                            value = CDINCAPACITYREASONvalues.fromValue(svcvalue)
                        } catch(e : Exception) {
                            // TODO: add a warning in the log
                            svcvalue = "sickness"
                            value = CDINCAPACITYREASONvalues.fromValue(svcvalue)
                        }
					}
				}
				isOutofhomeallowed = servmap["Sortie"]?.content?.let {
					(it[lang] ?: it.values.firstOrNull())?.stringValue
				} == "autorisée"
				servmap["pourcentage"]?.content?.let {
					(it[lang] ?: it.values.firstOrNull())?.measureValue?.value
				}?.let {
					percentage = it.toBigDecimal()
				}
			}
		}

		return ItemType().apply {
			ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; value = index.toString() })
			ids.add(IDKMEHR().apply { s = IDKMEHRschemes.LOCAL; sl = "MF-ID"; sv = ICUREVERSION; value = form.id })
			cds.add(CDITEM().apply { s(CDITEMschemes.CD_ITEM); value = "incapacity" })

			this.contents.add(content)
			lifecycle = LifecycleType().apply {
				cd = CDLIFECYCLE().apply {
					value = form.tags.find { t -> t.type == "CD-LIFECYCLE" }?.let { CDLIFECYCLEvalues.fromValue(it.code) }
							?: CDLIFECYCLEvalues.ACTIVE
				}
			}
			isIsrelevant = true
			beginmoment = Utils.makeMomentTypeFromFuzzyLong(servmap["du"]?.content?.let {
				(it[lang] ?: it.values.firstOrNull())?.fuzzyDateValue
			} ?: 0)
			endmoment = Utils.makeMomentTypeFromFuzzyLong(servmap["au"]?.content?.let {
				(it[lang] ?: it.values.firstOrNull())?.fuzzyDateValue
			} ?: 0)
			recorddatetime = Utils.makeXGC(form.modified, true)
		}
	}


	private fun makeEncounterDateTime(index: Int, yyyymmddhhmmss: Long): ItemType {
		return ItemType().apply {
			ids.add(idKmehr(index))
			cds.add(cdItem("encounterdatetime"))
			contents.add(ContentType().apply {
				date = makeXMLGregorianCalendarFromFuzzyLong(yyyymmddhhmmss)
				time = makeXMLGregorianCalendarFromFuzzyLong(yyyymmddhhmmss)?.apply {
					if (hour == DatatypeConstants.FIELD_UNDEFINED) {
						hour = 0
					}
					if (minute == DatatypeConstants.FIELD_UNDEFINED) {
						minute = 0
					}
					if (second == DatatypeConstants.FIELD_UNDEFINED) {
						second = 0
					}
				}
			})
		}
	}


	private fun makeEncounterLocation(index: Int, location: String, language: String): ItemType {
		return ItemType().apply {
			ids.add(idKmehr(index))
			cds.add(cdItem("encounterlocation"))
			contents.add(ContentType().apply {
				texts.add(TextType().apply { l = language; value = location })
			})
		}
	}

	private fun makeEncounterType(index: Int, encounterType: CodeStub): ItemType {
		return ItemType().apply {
			ids.add(idKmehr(index))
			cds.add(cdItem("encountertype"))
			contents.add(ContentType().apply {
				cds.add(CDCONTENT().apply { s = CDCONTENTschemes.CD_ENCOUNTER; value = encounterType.code })
			})
		}
	}

	private suspend fun makeContactPeople(startIndex: Int, pat: Patient, config: Config): List<ItemType> {
		val partnersById: Map<String, Patient> = patientLogic.getPatients(pat.partnerships.map { it?.partnerId }.filterNotNull())
				.filterNotNull().toList().associateBy { partner -> partner.id }

		return pat.partnerships.filter { it.partnerId != null }.mapIndexed { i, partnership ->
			partnersById[partnership.partnerId]?.let { partner ->
				ItemType().apply {
					ids.add(idKmehr(startIndex + i))
					ids.add(localIdKmehrElement(startIndex + i, config))
					cds.add(cdItem("contactperson"))
					cds.add(CDITEM().apply { s(CDITEMschemes.CD_CONTACT_PERSON); value = partnership.otherToMeRelationshipDescription })
					contents.add(ContentType().apply { person = makePerson(partner, config) })
				}
			}
		}.filterNotNull()
	}

	private suspend fun makeGmdManager(itemIndex: Int, config: Config, hcp: HealthcareParty, period: ReferralPeriod): ItemType? {
		return ItemType().apply {
			ids.add(idKmehr(itemIndex))
			ids.add(localIdKmehrElement(itemIndex, config))
			cds.add(cdItem("gmdmanager"))
			contents.add(ContentType().apply { hcparty = createParty(hcp) })
			beginmoment = period.startDate?.let { makeMomentType(it, precision = ChronoUnit.DAYS) }
			recorddatetime = period.startDate?.let { makeXmlGregorianCalendar(it) } // should be the modification date, but it's not present
		}.let { if (it.contents.first().hcparty.ids.filter { it.s == IDHCPARTYschemes.ID_HCPARTY }.size == 1) it else null }
	}

	private suspend fun makeInsurancyStatus(itemIndex: Int, config: Config, insurability: Insurability?): ItemType? {
		val insStatus = ItemType().apply {
        ids.add(idKmehr(itemIndex))
        ids.add(localIdKmehrElement(itemIndex, config))
        cds.add(cdItem("insurancystatus"))
        if (insurability?.insuranceId?.isBlank() == false) {
            try {
                insuranceLogic.getInsurance(insurability.insuranceId)?.let {
                        if (it.code != null && it.code.length >= 3) {
                            contents.add(ContentType().apply {
                                insurance = InsuranceType().apply {
                                    id = IDINSURANCE().apply { s = IDINSURANCEschemes.ID_INSURANCE; value = it.code.substring(0, 3); }
                                    membership = insurability.identificationNumber ?: ""
                                    if (it.code != null && it.code.length >= 3) {
                                        insurability.parameters["tc1"]?.let {
                                            cg1 = it
                                            insurability.parameters["tc2"]?.let { cg2 = it }
                                        }
                                    }
                                }
                            })
                        }
                    }
            } catch (ignored: DocumentNotFoundException) {
            }
        }
      }
		return if (insStatus.contents.size > 0) insStatus else null
	}

	private fun cdItem(v: String): CDITEM {
		return CDITEM().apply { s(CDITEMschemes.CD_ITEM); value = v }
	}

	private suspend fun getLastGmdManager(pat: Patient): Pair<HealthcareParty?, ReferralPeriod?> {
		val isActive: (ReferralPeriod) -> Boolean = { r -> r.startDate?.isBefore(Instant.now()) == true && null == r.endDate }
		val gmdRelationship = pat.patientHealthCareParties?.find { it.referralPeriods?.any(isActive) ?: false }
                ?: return Pair(null, null)
        val gmd = gmdRelationship.healthcarePartyId?.let { healthcarePartyLogic.getHealthcareParty(it) }
		return Pair(gmd, gmdRelationship.referralPeriods?.find(isActive))
	}

	private fun codesToKmehr(codes: Set<CodeStub>): ContentType {
		return ContentType().apply {
			cds.addAll(codes.map { code ->
				when  {
                    code.type == "ICPC" -> CDCONTENT().apply { s = CDCONTENTschemes.ICPC; sv = code.version; value = code.code }
					code.type =="ICD" -> CDCONTENT().apply { s = CDCONTENTschemes.ICD; sv = code.version; value = code.code }
					code.type =="CD-ATC" -> CDCONTENT().apply { s = CDCONTENTschemes.CD_ATC; sv = code.version; value = code.code }
					code.type =="CD-PATIENTWILL" -> CDCONTENT().apply { s = CDCONTENTschemes.CD_PATIENTWILL; sv = code.version; value = code.code }
					code.type =="BE-THESAURUS" -> CDCONTENT().apply { s = CDCONTENTschemes.CD_CLINICAL; sv = code.version; value = code.code } // FIXME: no spec for version can be found regarding thesaurus
					code.type =="BE-THESAURUS-PROCEDURES" -> CDCONTENT().apply {
                        // FIXME: this is specific to pricare and icure, what format should we use ?
						s = CDCONTENTschemes.LOCAL
						sl = "BE-THESAURUS-PROCEDURES"
						sv = code.version
						value = "${code.code}"
					}
					code.type == "CD-VACCINEINDICATION" -> CDCONTENT().apply { s = CDCONTENTschemes.CD_VACCINEINDICATION; sv = code.version; value = code.code }
                    code.type?.startsWith("MS-EXTRADATA") ?: false -> CDCONTENT().apply { s = CDCONTENTschemes.LOCAL; sv = code.version; sl = code.type; dn = code.type; value = code.code }
					else -> CDCONTENT().apply {
						s = CDCONTENTschemes.LOCAL
						sl = "ICURE.MEDICALCODEID"
						dn = "ICURE.MEDICALCODEID"
						sv = code.version
						value = code.code
					}
				}
			})
		}
	}

	fun isHeANewVersionOf(he: HealthElement) : Boolean {
		// since HEs versions have same healthElementId, if the He is the oldest with this ID, it's not a new version
		oldestHeByHeId[he.healthElementId]?.id?.let {
			return it != he.id
		}
		return false
	}

    fun isHeTheLastVersion(he: HealthElement) : Boolean {
        // since HEs versions have same healthElementId, if the He is the oldest with this ID, it's not a new version
        oldestHeByHeId[he.healthElementId]?.id?.let {
            return it != he.id
        }
        return false
    }

    fun addHealthCareElement(trn: TransactionType, eds: HealthElement, itemIndex: Int, config: Config): Int {
		var mutItemIndex = itemIndex
		try {
			val content = listOf(
					ContentType().apply {
						texts.add(TextType().apply { l = "fr"; value = eds.descr })
					},
					ContentType().apply {
                        cds.addAll(codesToKmehr(eds.codes).cds)
                    }
            )
			val itemtype = eds.tags.find { it.type == "CD-ITEM" }?.let { it.code } ?: "healthcareelement"
			createItemWithContent(eds, itemIndex, itemtype, content, "MF-ID")?.let {
				if(isHeANewVersionOf(eds) && config.format != Config.Format.PMF && eds.healthElementId != null) { // no versioning in PMF
					it.lnks.add(
							LnkType().apply {
								type = CDLNKvalues.ISANEWVERSIONOF; url = makeLnkUrl(eds.healthElementId)
							}
					)
				}
                if(!(config.format == Config.Format.PMF && !it.isIsrelevant && it.lifecycle.cd.value == CDLIFECYCLEvalues.INACTIVE)) {
                    // inactive irrelevant items should not be exported in PMF
                    trn.headingsAndItemsAndTexts.add(it)
                }
				mutItemIndex++

			}
		} catch (e: Exception) {
			log.error("Unexpected error", e)
		}
		return mutItemIndex
	}

	suspend fun getHealthElements(hcp: HealthcareParty, sfks: List<String>, config: Config): List<HealthElement> {
        var res : List<HealthElement> = emptyList()
        if(hcp.parentId != null) {
            res = res + (healthElementLogic?.findByHCPartySecretPatientKeys(hcp.parentId, sfks)?.toList() ?: emptyList())
        }
        res = res + (healthElementLogic?.findByHCPartySecretPatientKeys(hcp.id, sfks)?.toList() ?: emptyList())
        res = res.distinctBy { it.id }
        return excludeHealthElementsForPMF(
				res?.filterNot {
					it.descr?.matches("INBOX|Etat général.*|Algemeen toestand.*".toRegex()) ?: false
				}.toList()
		, config)
	}

	fun excludeHealthElementsForPMF(helist: List<HealthElement>, config: Config) : List<HealthElement>{
		// PMF = all active items + all relevant inactive items
		return if(config.format == Config.Format.PMF) {
			helist.filter{
                (it.endOfLife == null || it.endOfLife == 0L)  		                        // not deleted
                && (
                        (
                                it.tags.any { it.type == "CD-LIFECYCLE" && it.code == "active" } 	// is tagged active
                                || (((it.status ?: 0) and 0x01) == 0)                               // or is status active
                        )
                        || (((it.status ?: 0) and 0x10) == 0)						                        // is status relevant
                )
			}
		} else {
			helist
		}
	}

	fun excludesServiceForPMF(servlist: List<Service>, config: Config) : List<Service>{
		// PMF = all active items + all relevant inactive items
		return if(config.format == Config.Format.PMF) {
			servlist.filter{ svc ->
                (svc.endOfLife == null || svc.endOfLife == 0L) // not deleted
                && (
                    (
                        svc.tags.any { // is tagged active
                            it.type == "CD-LIFECYCLE" && (
                                    listOf("active", "pending", "planned").contains(it.code) // "administrated" because vaccines should be included
                            )
                        }
                        || ( // or administrated vaccine
                            svc.tags.any({ it.type == "CD-ITEM" && listOf("vaccine").contains(it.code) }) // acts have always status=0, exclude them from this test
                            && svc.tags.any { // is tagged active
                                it.type == "CD-LIFECYCLE" && (
                                    listOf("completed").contains(it.code) // "administrated" because vaccines should be included
                                )
                            }
                        )
                        || ( // or is status active
                                svc.tags.none({ it.type == "CD-ITEM" && listOf("acts", "vaccine").contains(it.code) }) // acts have always status=0, exclude them from this test
                                && ((svc.status ?: 0) and 0x01) == 0
                        )
                    )
                    || ( // is status relevant
                            svc.tags.none({ it.type == "CD-ITEM" && listOf("acts", "vaccine").contains(it.code) }) // acts have always status=0, exclude them from this test
                            && ((svc.status ?: 0) and 0x10) == 0
                        )
                )
                && ( // no closingDate or closed in futur
                        svc.closingDate == null
                        || svc.closingDate == 0L
                        || svc.closingDate!!.toLong() > FuzzyValues.getFuzzyDate(LocalDateTime.now(), ChronoUnit.SECONDS)
                )
                && (
                        // medication store end moment in content.medicationValue.endMoment instead of svc.closingDate
                        svc.content.values.find { content ->
                            content.medicationValue != null
                        }?.let { content ->
                            content.medicationValue!!.endMoment == null
                            || content.medicationValue!!.endMoment == 0L
                            || content.medicationValue!!.endMoment!! > FuzzyValues.getFuzzyDate(LocalDateTime.now(), ChronoUnit.SECONDS)
                        } ?: true
                )
                && (
                        // is newest version: there is no history in PMF
                        newestServicesById[svc.id] != null
                )
			}
		} else {
			servlist
		}
	}

    private suspend fun getAllContacts(hcp : HealthcareParty, sfks: List<String>) : List<Contact> {
        var res : List<Contact> = emptyList()
        if(hcp.parentId != null) {
            res = contactLogic.findByHCPartyPatient(hcp.parentId, sfks.toList()).toList()
        }
        res = res + contactLogic.findByHCPartyPatient(hcp.id, sfks.toList()).toList()
        res = res.filterNot { it.services.isEmpty() &&  it.subContacts.isEmpty()}
        return res.distinctBy { it.id }
    }

	private fun <T : ICureDocument<String>> getNonConfidentialItems(items: List<T>): List<T> {
		return items.filter { s ->
			null == s.tags.find { it.type == "org.taktik.icure.entities.embed.Confidentiality" && it.code == "secret" } &&
					null == s.codes.find { it.type == "org.taktik.icure.entities.embed.Visibility" && it.code == "maskedfromsummary" }
		}
	}

	fun makeLnkUrl(id: String) : String {
		return "//item[id[@SL=\"MF-ID\" and .=\"${id}\"]]"
	}

	suspend fun makeSpecialPrescriptionTransaction(contact: Contact, subcon: SubContact, form: Form, cdTransactionType: String, data: ByteArray): TransactionType {
        // for kine and nurse prescriptions
		return TransactionType().apply {

			ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; value = "1" })
			ids.add(IDKMEHR().apply { s = IDKMEHRschemes.LOCAL; sl = "MF-ID"; value = form.id })
			cds.add(CDTRANSACTION().apply { s = CDTRANSACTIONschemes.CD_TRANSACTION; value = "prescription" })
			cds.add(CDTRANSACTION().apply { s = CDTRANSACTIONschemes.CD_TRANSACTION_TYPE ; value = cdTransactionType })
			contact.modified?.let {
				date = makeXGC(it)
				time = makeXGC(it, unsetMillis = true)
			}
			contact.responsible?.let {
				author = AuthorType().apply { hcparties.add(healthcarePartyLogic.getHealthcareParty(it)?.let { hcp -> createParty(hcp, emptyList()) }) }
			}
			recorddatetime = Utils.makeXGC(contact.created) // TODO: maybe should take form date instead of contact date
			isIscomplete = true
			isIsvalidated = true

			headingsAndItemsAndTexts.add(
					LnkType().apply { type = CDLNKvalues.MULTIMEDIA; value = data}
			)
			headingsAndItemsAndTexts.add(
					LnkType().apply { type = CDLNKvalues.ISACHILDOF; url = makeLnkUrl(contact.id)}
			)
		}
	}

	suspend fun makeKinePrescriptionTransaction(contact: Contact, subcon: SubContact, form: Form): TransactionType {
		val data = renderKinePrescriptionTemplate(contact, subcon, form)
		return makeSpecialPrescriptionTransaction(contact, subcon, form, "physiotherapy", data)
	}

	suspend fun makeNursePrescriptionTransaction(contact: Contact, subcon: SubContact, subformsubcons: List<SubContact>, form: Form): TransactionType {
		val data = renderNursePrescriptionTemplate(contact, subcon, subformsubcons, form)
		return makeSpecialPrescriptionTransaction(contact, subcon, form, "nursing", data)
	}

	fun renderNursePrescriptionTemplate(contact: Contact, subcon: SubContact, subformsubcons: List<SubContact>, form: Form): ByteArray {
        // TODO: not working yet, template and mapping need to be done

		val mf : MustacheFactory = DefaultMustacheFactory()
		val m : Mustache = mf.compile("NursePrescription.mustache")
		val writer = StringWriter()

		val lang = "fr" // FIXME: hardcoded "fr" but not sure if other languages can be used
		val servkeys = mapOf(
				"Communication par courrier"         to "contactMailPreference",
				"Communication par téléphone"        to "contactPhonePreference",
				"Communication autre"                to "contactOtherDetails" // NOTE: contactOtherPreference bit is not really relevant since text is filled
		)
		val servlist = servkeys.keys
		val servmap = contact.services.filter { serv -> subcon.services.map { it.serviceId }.contains(serv.id) }.associateBy({ it.label }, { it })

		fun getServiceValue(serv : Service?): String {
			return serv?.content?.let {
				it[lang] ?: it.values.firstOrNull()
			}?.let {
				it.stringValue
						?: it.booleanValue?.let { if(it) "X" else "" }
						?: it.numberValue?.let { it.toString() }
						?: it.measureValue?.let { it.value.toString() }
			} ?: ""
		}

		val keyserv = servkeys.keys.associateBy({ servkeys[it] }, { getServiceValue(servmap[it]) })

		val dat = mapOf(
				"nurse" to keyserv,
				"pat" to mapOf(
						"lname" to "testLname",
						"fname" to "testFname"
				)
		)

		m.execute(writer, dat)

		val html : String = writer.toString()
		println(html)
		return html.toByteArray()
	}

	fun renderKinePrescriptionTemplate(contact: Contact, subcon: SubContact, form: Form): ByteArray {
		// TODO: not working yet, template and mapping need to be done

		val mf : MustacheFactory = DefaultMustacheFactory()
		val m : Mustache = mf.compile("KinePrescription.mustache")
		val writer: StringWriter = StringWriter()

		val lang = "fr" // FIXME: hardcoded "fr" but not sure if other languages can be used
		val servkeys = mapOf(
				"Prescription de kinésithérapie"     to "opinionRequest",
				"Le patient ne peut se déplacer"    to "PatientCannotLeaveHome",
				"Demande d'avis consultatif kiné"    to "opinionRequest",
				"Mobilisation"                       to "fMobilisation",
				"Massage"                            to "fMasg",
				"Thermotherapie"                     to "fThermotherapy",
				"Kiné respiratoire tapotements" to "fTaping",
				"Localisation"                       to "localisation",
				"Drainage lymphatique"               to "fDrain",
				"Gymnastique"                        to "fGym",
				"Nombre de séances"                  to "numSession",
				"Fréquence"                           to "freq",
				"Code d'intervention"                to "surgicalInterventionCode",
				"Diagnostic"                         to "diagnostic",
				"Imagerie kiné"                      to "imageryAvailable",
				"Autre avis kiné"                    to "importantMedicalInfo", // TODO: not sure this match
				"Biologie kiné"                      to "biologyAvailable",
				"Avis spécialisé kiné"               to "specialisedOpinionAvailable", // TODO: not sure this match
				"Evolution pendant tt"               to "feedbackRequiredDuring",
				"Evolution fin tt"                   to "feedbackRequiredAtTheEnd",
				"Communication par courrier"         to "contactMailPreference",
				"Communication par téléphone"        to "contactPhonePreference",
				"Communication autre"                to "contactOtherDetails" // NOTE: contactOtherPreference bit is not really relevant since text is filled
		)
		val servlist = servkeys.keys
		val servmap = contact.services.filter { serv -> subcon.services.map { it.serviceId }.contains(serv.id) }.associateBy({ it.label }, { it })

		fun getServiceValue(serv : Service?): String {
			return serv?.content?.let {
				it[lang] ?: it.values.firstOrNull()
			}?.let {
				it.stringValue
					?: it.booleanValue?.let { if(it) "X" else "" }
					?: it.numberValue?.let { it.toString() }
					?: it.measureValue?.let { it.value.toString() }
			} ?: ""
		}

		val keyserv = servkeys.keys.associateBy({ servkeys[it] }, { getServiceValue(servmap[it]) })

		val dat = mapOf(
				"kine" to keyserv,
				"pat" to mapOf(
						"lname" to "testLname",
						"fname" to "testFname"
				)
		)

		m.execute(writer, dat)

		val html : String = writer.toString()
		println(html)
		return html.toByteArray()
	}

	fun renumberKmehrIds(folder: FolderType) {
		var transactionIndex = 1
		folder.transactions.forEach {
			var itemIndex = 1
			it.ids.find { it.s == IDKMEHRschemes.ID_KMEHR }?.let {
				it.value = transactionIndex.toString()
				transactionIndex += 1
			}
			it.headingsAndItemsAndTexts.forEach {
				try {
					val item = it as ItemType // can fail with exception when not an Item
					it.ids.find { it.s == IDKMEHRschemes.ID_KMEHR }?.let {
						it.value = itemIndex.toString()
						itemIndex += 1
					}
				} catch(ex: java.lang.ClassCastException) {
					// just skip
				}
			}
		}
	}
}
