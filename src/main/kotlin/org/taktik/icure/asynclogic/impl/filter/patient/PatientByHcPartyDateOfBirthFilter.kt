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
package org.taktik.icure.asynclogic.impl.filter.patient

import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import org.springframework.stereotype.Service
import org.taktik.icure.asynclogic.AsyncSessionLogic
import org.taktik.icure.asynclogic.PatientLogic
import org.taktik.icure.asynclogic.impl.filter.Filter
import org.taktik.icure.asynclogic.impl.filter.Filters
import org.taktik.icure.domain.filter.patient.PatientByHcPartyDateOfBirthFilter
import org.taktik.icure.entities.Patient
import org.taktik.icure.utils.getLoggedHealthCarePartyId
import javax.security.auth.login.LoginException

@Service
class PatientByHcPartyDateOfBirthFilter(private val patientLogic: PatientLogic,
                                        private val sessionLogic: AsyncSessionLogic) : Filter<String, Patient, PatientByHcPartyDateOfBirthFilter> {

    override fun resolve(filter: PatientByHcPartyDateOfBirthFilter, context: Filters) = flow<String> {
        try {
            emitAll(patientLogic.listByHcPartyDateOfBirthIdsOnly(filter.dateOfBirth, filter.healthcarePartyId ?: getLoggedHealthCarePartyId(sessionLogic)))
        } catch (e: LoginException) {
            throw IllegalArgumentException(e)
        }
    }
}