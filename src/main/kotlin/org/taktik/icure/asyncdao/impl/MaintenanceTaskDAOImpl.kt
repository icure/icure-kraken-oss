/*
 * Copyright (c) 2020. Taktik SA, All rights reserved.
 */

package org.taktik.icure.asyncdao.impl

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository
import org.taktik.couchdb.annotation.View
import org.taktik.couchdb.entity.ComplexKey
import org.taktik.couchdb.id.IDGenerator
import org.taktik.couchdb.queryView
import org.taktik.icure.asyncdao.MaintenanceTaskDAO
import org.taktik.icure.entities.MaintenanceTask
import org.taktik.icure.entities.embed.Identifier
import org.taktik.icure.properties.CouchDbProperties

@Repository("maintenanceTaskDAO")
@View(name = "all", map = "function(doc) { if (doc.java_type === 'org.taktik.icure.entities.MaintenanceTask' && !doc.deleted) emit(null, doc._id)}")
class MaintenanceTaskDAOImpl(
	couchDbProperties: CouchDbProperties,
	@Qualifier("baseCouchDbDispatcher") couchDbDispatcher: CouchDbDispatcher,
	idGenerator: IDGenerator
) : GenericIcureDAOImpl<MaintenanceTask>(MaintenanceTask::class.java, couchDbProperties, couchDbDispatcher, idGenerator), MaintenanceTaskDAO {

	@OptIn(ExperimentalCoroutinesApi::class)
	@View(name = "by_hcparty_identifier", map = "classpath:js/maintenancetask/By_hcparty_identifier_map.js")
	override fun listMaintenanceTasksByHcPartyAndIdentifier(healthcarePartyId: String, identifiers: List<Identifier>) = flow {
		val client = couchDbDispatcher.getClient(dbInstanceUrl)

		val queryView = createQuery(client, "by_hcparty_identifier")
			.keys(
				identifiers.map {
					ComplexKey.of(healthcarePartyId, it.system, it.value)
				}
			)

		emitAll(
			client.queryView<ComplexKey, String>(queryView)
				.mapNotNull {
					if (it.key == null || it.key!!.components.size < 3) {
						return@mapNotNull null
					}
					return@mapNotNull it.id
				}
		)
	}

	@OptIn(ExperimentalCoroutinesApi::class)
	@View(name = "by_hcparty_date", map = "classpath:js/maintenancetask/By_hcparty_date_map.js")
	override fun listMaintenanceTasksAfterDate(healthcarePartyId: String, date: Long) = flow {
		val client = couchDbDispatcher.getClient(dbInstanceUrl)

		val queryView = createQuery(client, "by_hcparty_date")
			.startKey(ComplexKey.of(healthcarePartyId, ComplexKey.emptyObject()))
			.endKey(ComplexKey.of(healthcarePartyId, date))
			.descending(true)
			.includeDocs(false)

		emitAll(client.queryView<ComplexKey, Void>(queryView).map { it.id })
	}

	@OptIn(ExperimentalCoroutinesApi::class)
	@View(name = "by_hcparty_type", map = "classpath:js/maintenancetask/By_hcparty_type_map.js")
	override fun listMaintenanceTasksByHcPartyAndType(healthcarePartyId: String, type: String, startDate: Long?, endDate: Long?): Flow<String> = flow {
		val client = couchDbDispatcher.getClient(dbInstanceUrl)

		val queryView = createQuery(client, "by_hcparty_type")
			.startKey(
				startDate?.let { ComplexKey.of(healthcarePartyId, type, startDate) } ?: ComplexKey.of(
					healthcarePartyId,
					type,
					ComplexKey.emptyObject()
				)
			)
			.endKey(endDate?.let { ComplexKey.of(healthcarePartyId, type, endDate) } ?: ComplexKey.of(healthcarePartyId, type))
			.descending(true)
			.includeDocs(false)

		emitAll(client.queryView<ComplexKey, Void>(queryView).map { it.id })
	}
}
