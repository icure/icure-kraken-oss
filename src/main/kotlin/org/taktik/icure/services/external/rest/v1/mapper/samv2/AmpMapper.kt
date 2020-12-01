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

package org.taktik.icure.services.external.rest.v1.mapper.samv2

import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.taktik.icure.entities.samv2.Amp
import org.taktik.icure.services.external.rest.v1.dto.samv2.AmpDto
import org.taktik.icure.services.external.rest.v1.mapper.EntityReferenceMapper
import org.taktik.icure.services.external.rest.v1.mapper.samv2.embed.AmpComponentMapper
import org.taktik.icure.services.external.rest.v1.mapper.samv2.embed.AmpStatusMapper
import org.taktik.icure.services.external.rest.v1.mapper.samv2.embed.AmppMapper
import org.taktik.icure.services.external.rest.v1.mapper.samv2.embed.CompanyMapper
import org.taktik.icure.services.external.rest.v1.mapper.samv2.embed.MedicineTypeMapper
import org.taktik.icure.services.external.rest.v1.mapper.samv2.embed.SamTextMapper

@Mapper(componentModel = "spring", uses = [CompanyMapper::class, AmpStatusMapper::class, SamTextMapper::class, VmpStubMapper::class, MedicineTypeMapper::class, AmpComponentMapper::class, EntityReferenceMapper::class, AmppMapper::class], injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface AmpMapper {
    @Mappings(
            Mapping(target = "attachments", ignore = true),
            Mapping(target = "revHistory", ignore = true),
            Mapping(target = "conflicts", ignore = true),
            Mapping(target = "revisionsInfo", ignore = true)
            )
	fun map(ampDto: AmpDto):Amp
	fun map(amp: Amp):AmpDto
}
