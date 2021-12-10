package org.taktik.icure.asynclogic.impl.filter.patient

import javax.security.auth.login.LoginException
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.taktik.icure.asynclogic.AsyncSessionLogic
import org.taktik.icure.asynclogic.PatientLogic
import org.taktik.icure.asynclogic.impl.filter.Filter
import org.taktik.icure.asynclogic.impl.filter.Filters
import org.taktik.icure.entities.Patient
import org.taktik.icure.utils.getLoggedHealthCarePartyId

class PatientByHcPartyAndIdentifiersFilter (private val patientLogic: PatientLogic, private val sessionLogic: AsyncSessionLogic) : Filter<String, Patient, org.taktik.icure.domain.filter.patient.PatientByHcPartyAndIdentifiersFilter> {

    override fun resolve(filter: org.taktik.icure.domain.filter.patient.PatientByHcPartyAndIdentifiersFilter, context: Filters) = flow {
        try {
            emitAll(patientLogic.listPatientByHealthcarepartyAndIdentifiersIdsOnly( filter.healthcarePartyId ?: getLoggedHealthCarePartyId(sessionLogic), filter.identifiers).map { (patientId, _) -> patientId })
        } catch (e: LoginException) {
            throw IllegalArgumentException(e)
        }
    }
}
