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

package org.taktik.icure.be.ehealth.logic.kmehr.diarynote.impl.v20170901

import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.logging.LogFactory
import org.taktik.icure.be.ehealth.dto.kmehr.v20170901.Utils
import org.taktik.icure.be.ehealth.dto.kmehr.v20170901.be.fgov.ehealth.standards.kmehr.cd.v1.*
import org.taktik.icure.be.ehealth.dto.kmehr.v20170901.be.fgov.ehealth.standards.kmehr.dt.v1.TextType
import org.taktik.icure.be.ehealth.dto.kmehr.v20170901.be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR
import org.taktik.icure.be.ehealth.dto.kmehr.v20170901.be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHRschemes
import org.taktik.icure.be.ehealth.dto.kmehr.v20170901.be.fgov.ehealth.standards.kmehr.schema.v1.*
import org.taktik.icure.be.ehealth.logic.kmehr.v20170901.KmehrExport
import org.taktik.icure.constants.ServiceStatus
import org.taktik.icure.entities.HealthElement
import org.taktik.icure.entities.HealthcareParty
import org.taktik.icure.entities.Patient
import org.taktik.icure.entities.base.ICureDocument
import org.taktik.icure.entities.embed.Content
import org.taktik.icure.entities.embed.Partnership
import org.taktik.icure.entities.embed.PatientHealthCareParty
import org.taktik.icure.entities.embed.Service
import org.taktik.icure.services.external.api.AsyncDecrypt
import org.taktik.icure.services.external.rest.v1.dto.HealthElementDto
import org.taktik.icure.services.external.rest.v1.dto.embed.ServiceDto
import org.taktik.icure.services.external.rest.v1.dto.filter.Filters
import org.taktik.icure.services.external.rest.v1.dto.filter.service.ServiceByHcPartyTagCodeDateFilter
import org.taktik.icure.utils.FuzzyValues
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.time.Instant
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*
import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller

@org.springframework.stereotype.Service("dairyNoteExport")
class DiaryNoteExport : KmehrExport() {
    override val log = LogFactory.getLog(DiaryNoteExport::class.java)

    fun getMd5(hcPartyId: String, patient: Patient, sfks: List<String>, excludedIds: List<String>): String {
        val signatures = ArrayList(listOf(patient.signature))
        val sorted = signatures.sorted()
        val md5Hex = DigestUtils.md5Hex(sorted.joinToString(","))
        return md5Hex
    }

    fun createDiaryNote(
        os: OutputStream,
        pat: Patient,
        sfks: List<String>,
        sender: HealthcareParty,
        recipient: HealthcareParty?,
        language: String,
        note: String?,
        tags: List<String>,
        contexts: List<String>,
        isPsy: Boolean,
        documentId: String?,
        decryptor: AsyncDecrypt?,
        config: Config = Config(_kmehrId = System.currentTimeMillis().toString(),
            date = makeXGC(Instant.now().toEpochMilli())!!,
            time = Utils.makeXGC(Instant.now().toEpochMilli(), true)!!,
            soft = Config.Software(name = "iCure", version = ICUREVERSION),
            clinicalSummaryType = "",
            defaultLanguage = "en"
        )
    ) {
        val message = initializeMessage(sender, config)
        message.header.recipients.add(RecipientType().apply {
            hcparties.add(recipient?.let { createParty(it, emptyList()) } ?: createParty(emptyList(), listOf(CDHCPARTY().apply { s = CDHCPARTYschemes.CD_APPLICATION; sv = "1.0" }), "gp-software-migration"))
        })

        val folder = FolderType()
        folder.ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value = 1.toString() })
        folder.patient = makePerson(pat, config)
        fillPatientFolder(folder, pat, sfks, sender, language, config, note, tags, contexts, isPsy, documentId, decryptor)
        message.folders.add(folder)

        val jaxbMarshaller = JAXBContext.newInstance(Kmehrmessage::class.java).createMarshaller()
        // output pretty printed
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
        jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8")
        jaxbMarshaller.marshal(message, OutputStreamWriter(os, "UTF-8"))
    }

    private fun dnFromContext(context: String) : String{
        return when (context) {
            "psichronilux" ->  "CHRONILUX"
            "psipact" ->  "PACT"
            "psiresinam" ->  "RéSiNam"
            "psi3c4h" ->  "3C4H"
            "psirelian" ->  "RéLIAN"
            else ->  ""
        }
    }

    internal fun fillPatientFolder(folder: FolderType, p: Patient, sfks: List<String>, sender: HealthcareParty, language: String, config: Config, note: String?, tags: List<String>, contexts: List<String>, isPsy: Boolean, documentId: String?, decryptor: AsyncDecrypt?): FolderType {
        val trn = TransactionType().apply {
            cds.add(CDTRANSACTION().apply { s = CDTRANSACTIONschemes.CD_TRANSACTION; sv = CDTRANSACTIONschemes.CD_TRANSACTION.version(); value = "diarynote" })
            author = AuthorType().apply { hcparties.add(createParty(sender, emptyList())) }
            ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value = "1" })
            ids.add(IDKMEHR().apply { s = IDKMEHRschemes.LOCAL; sl = "iCure-Item"; sv = ICUREVERSION; value = p.id.replace("-".toRegex(), "").substring(0, 8) + "." + System.currentTimeMillis() })
            tags.forEach { tag -> cds.add(CDTRANSACTION().apply { s = CDTRANSACTIONschemes.CD_DIARY; sv = CDTRANSACTIONschemes.CD_DIARY.version(); value = tag }) }
            contexts.forEach {context -> cds.add(CDTRANSACTION().apply { s = CDTRANSACTIONschemes.LOCAL; sv = "1.0"; sl = "CD-RSW-CONTEXT"; value = context; dn = dnFromContext(context) })}
            makeXGC(System.currentTimeMillis()).let { date = it; time = it }
            isIscomplete = true
            isIsvalidated = true
        }

        folder.transactions.add(trn)

        //add tags

        //add contexts (optional ?)

        if (note?.length ?: 0 > 0) {
            trn.headingsAndItemsAndTexts.add(TextType().apply { l = sender.languages.firstOrNull() ?: "fr"; value = note })
        }

        //add document

        //Remove empty headings
        val iterator = folder.transactions[0].headingsAndItemsAndTexts.iterator()
        while (iterator.hasNext()) {
            val h = iterator.next()
            if (h is HeadingType) {
                if (h.headingsAndItemsAndTexts.size == 0) {
                    iterator.remove()
                }
            }
        }
        return folder
    }

}
