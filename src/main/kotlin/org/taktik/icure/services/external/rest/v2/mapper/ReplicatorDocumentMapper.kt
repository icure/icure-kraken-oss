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

package org.taktik.icure.services.external.rest.v2.mapper

import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.taktik.couchdb.entity.ReplicateCommand
import org.taktik.couchdb.entity.ReplicationStats
import org.taktik.couchdb.entity.ReplicatorDocument
import org.taktik.icure.services.external.rest.v2.dto.AuthenticationDto
import org.taktik.icure.services.external.rest.v2.dto.BasicDto
import org.taktik.icure.services.external.rest.v2.dto.RemoteDto
import org.taktik.icure.services.external.rest.v2.dto.ReplicationStatsDto
import org.taktik.icure.services.external.rest.v2.dto.ReplicatorDocumentDto

@Mapper(componentModel = "spring", uses = [], injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface ReplicatorDocumentV2Mapper {
	@Mappings(
		Mapping(target = "createTarget", source = "create_target"),
		Mapping(target = "docIds", source = "doc_ids")
	)
	fun map(role: ReplicatorDocument): ReplicatorDocumentDto
	fun map(role: ReplicationStats): ReplicationStatsDto
	fun map(remote: ReplicateCommand.Remote): RemoteDto
	fun map(authenticaton: ReplicateCommand.Remote.Authentication): AuthenticationDto
	fun map(basic: ReplicateCommand.Remote.Authentication.Basic): BasicDto
}
