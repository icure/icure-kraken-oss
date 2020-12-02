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

package org.taktik.icure.asynclogic.impl.filter.code

import kotlinx.coroutines.flow.Flow
import org.taktik.icure.asynclogic.CodeLogic
import org.taktik.icure.asynclogic.impl.filter.Filter
import org.taktik.icure.asynclogic.impl.filter.Filters
import org.taktik.icure.domain.filter.code.CodeByRegionTypeLabelLanguageFilter
import org.taktik.icure.entities.base.Code

class CodeByRegionTypeLabelLanguageFilter(private val codeLogic: CodeLogic) : Filter<String, Code, CodeByRegionTypeLabelLanguageFilter> {
    override fun resolve(filter: CodeByRegionTypeLabelLanguageFilter, context: Filters): Flow<String> {
        return codeLogic.listCodeIdsByLabel(filter.region, filter.language, filter.type, filter.label)
    }
}