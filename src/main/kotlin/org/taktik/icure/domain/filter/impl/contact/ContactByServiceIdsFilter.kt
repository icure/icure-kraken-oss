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
package org.taktik.icure.domain.filter.impl.contact

import com.github.pozo.KotlinBuilder
import com.google.common.base.Objects
import org.taktik.icure.domain.filter.AbstractFilter
import org.taktik.icure.entities.Contact

@KotlinBuilder
data class ContactByServiceIdsFilter(
        override val desc: String? = null,
        override val ids: List<String>? = null
) : AbstractFilter<Contact>, org.taktik.icure.domain.filter.contact.ContactByServiceIdsFilter {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val filter = other as ContactByServiceIdsFilter
        return Objects.equal(ids, filter.ids)
    }

    override fun hashCode(): Int {
        return Objects.hashCode(ids)
    }

    override fun matches(item: Contact): Boolean {
        return item.services.stream().filter { (id) -> ids!!.contains(id) }.findAny().isPresent
    }
}