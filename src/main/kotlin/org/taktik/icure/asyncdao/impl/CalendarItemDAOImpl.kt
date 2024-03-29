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

package org.taktik.icure.asyncdao.impl

import java.time.temporal.ChronoUnit
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flattenConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository
import org.taktik.couchdb.ViewRowWithDoc
import org.taktik.couchdb.annotation.View
import org.taktik.couchdb.entity.ComplexKey
import org.taktik.couchdb.id.IDGenerator
import org.taktik.couchdb.queryViewIncludeDocs
import org.taktik.couchdb.queryViewIncludeDocsNoValue
import org.taktik.icure.asyncdao.CalendarItemDAO
import org.taktik.icure.db.PaginationOffset
import org.taktik.icure.entities.CalendarItem
import org.taktik.icure.properties.CouchDbProperties
import org.taktik.icure.utils.FuzzyValues
import org.taktik.icure.utils.distinctBy
import org.taktik.icure.utils.distinctById

@FlowPreview
@Repository("calendarItemDAO")
@View(name = "all", map = "function(doc) { if (doc.java_type == 'org.taktik.icure.entities.CalendarItem' && !doc.deleted) emit( null, doc._id )}")
class CalendarItemDAOImpl(
	couchDbProperties: CouchDbProperties,
	@Qualifier("healthdataCouchDbDispatcher") couchDbDispatcher: CouchDbDispatcher,
	idGenerator: IDGenerator
) : GenericDAOImpl<CalendarItem>(couchDbProperties, CalendarItem::class.java, couchDbDispatcher, idGenerator), CalendarItemDAO {

	@View(name = "by_hcparty_and_startdate", map = "classpath:js/calendarItem/by_hcparty_and_startdate.js")
	override fun listCalendarItemByStartDateAndHcPartyId(startDate: Long?, endDate: Long?, hcPartyId: String): Flow<CalendarItem> = flow {
		val client = couchDbDispatcher.getClient(dbInstanceUrl)

		val from = ComplexKey.of(
			hcPartyId,
			startDate
		)
		val to = ComplexKey.of(
			hcPartyId,
			endDate ?: ComplexKey.emptyObject()
		)

		val viewQuery = createQuery(client, "by_hcparty_and_startdate")
			.startKey(from)
			.endKey(to)
			.includeDocs(true)

		emitAll(client.queryViewIncludeDocsNoValue<Array<String>, CalendarItem>(viewQuery).map { it.doc })
	}

	@View(name = "by_hcparty_and_enddate", map = "classpath:js/calendarItem/by_hcparty_and_enddate.js")
	override fun listCalendarItemByEndDateAndHcPartyId(startDate: Long?, endDate: Long?, hcPartyId: String): Flow<CalendarItem> = flow {
		val client = couchDbDispatcher.getClient(dbInstanceUrl)

		val from = ComplexKey.of(
			hcPartyId,
			startDate
		)
		val to = ComplexKey.of(
			hcPartyId,
			endDate ?: ComplexKey.emptyObject()
		)

		val viewQuery = createQuery(client, "by_hcparty_and_enddate")
			.startKey(from)
			.endKey(to)
			.includeDocs(true)

		emitAll(client.queryViewIncludeDocsNoValue<Array<String>, CalendarItem>(viewQuery).map { it.doc })
	}

	override fun listCalendarItemByPeriodAndHcPartyId(startDate: Long?, endDate: Long?, hcPartyId: String): Flow<CalendarItem> {
		val calendarItems = this.listCalendarItemByStartDateAndHcPartyId(startDate, endDate, hcPartyId)
		val calendarItemsEnd = this.listCalendarItemByEndDateAndHcPartyId(startDate, endDate, hcPartyId)

		return flowOf(calendarItems, calendarItemsEnd).flattenConcat().distinctById()
	}

	@View(name = "by_agenda_and_startdate", map = "classpath:js/calendarItem/by_agenda_and_startdate.js")
	override fun listCalendarItemByStartDateAndAgendaId(startDate: Long?, endDate: Long?, agendaId: String): Flow<CalendarItem> = flow {
		val client = couchDbDispatcher.getClient(dbInstanceUrl)

		val from = ComplexKey.of(
			agendaId,
			startDate
		)
		val to = ComplexKey.of(
			agendaId,
			endDate ?: ComplexKey.emptyObject()
		)

		val viewQuery = createQuery(client, "by_agenda_and_startdate")
			.startKey(from)
			.endKey(to)
			.includeDocs(true)

		emitAll(client.queryViewIncludeDocsNoValue<ComplexKey, CalendarItem>(viewQuery).map { it.doc })
	}

	@View(name = "by_agenda_and_enddate", map = "classpath:js/calendarItem/by_agenda_and_enddate.js")
	override fun listCalendarItemByEndDateAndAgendaId(startDate: Long?, endDate: Long?, agenda: String): Flow<CalendarItem> = flow {
		val client = couchDbDispatcher.getClient(dbInstanceUrl)

		val from = ComplexKey.of(
			agenda,
			startDate
		)
		val to = ComplexKey.of(
			agenda,
			endDate ?: ComplexKey.emptyObject()
		)

		val viewQuery = createQuery(client, "by_agenda_and_enddate")
			.startKey(from)
			.endKey(to)
			.includeDocs(true)

		emitAll(client.queryViewIncludeDocsNoValue<ComplexKey, CalendarItem>(viewQuery).map { it.doc })
	}

	override fun listCalendarItemByPeriodAndAgendaId(startDate: Long?, endDate: Long?, agendaId: String): Flow<CalendarItem> {
		return listCalendarItemByStartDateAndAgendaId(
			startDate?.let {
				/* 1 day in the past to catch long lasting events that could bracket the search period */
				FuzzyValues.getFuzzyDateTime(FuzzyValues.getDateTime(it).minusDays(1), ChronoUnit.SECONDS)
			},
			endDate,
			agendaId
		).filter {
			it.endTime?.let { et -> et > (startDate ?: 0) } ?: true
		}
	}

	@View(name = "by_hcparty_patient", map = "classpath:js/calendarItem/by_hcparty_patient_map.js")
	override fun listCalendarItemsByHcPartyAndPatient(hcPartyId: String, secretPatientKeys: List<String>): Flow<CalendarItem> = flow {
		val client = couchDbDispatcher.getClient(dbInstanceUrl)
		val keys = secretPatientKeys.map { fk -> ComplexKey.of(hcPartyId, fk) }
		val viewQuery = createQuery(client, "by_hcparty_patient").keys(keys).includeDocs(true)
		emitAll(client.queryViewIncludeDocs<Array<String>, String, CalendarItem>(viewQuery).distinctBy { it.id }.map { it.doc })
	}

	@View(name = "by_hcparty_patient_start_time", map = "classpath:js/calendarItem/By_hcparty_patient_start_time_map.js")
	override fun findCalendarItemsByHcPartyAndPatient(hcPartyId: String, secretPatientKey: String, pagination: PaginationOffset<ComplexKey>) = flow {
		val client = couchDbDispatcher.getClient(dbInstanceUrl)
		val viewQuery = pagedViewQuery<CalendarItem, ComplexKey>(client, "by_hcparty_patient_start_time", ComplexKey.of(hcPartyId, secretPatientKey, ComplexKey.emptyObject()), ComplexKey.of(hcPartyId, secretPatientKey, null), pagination, true)
		emitAll(client.queryViewIncludeDocs<Array<Any>, String, CalendarItem>(viewQuery))
	}

	/** keys have to be sorted Couchdb way */
	override fun findCalendarItemsByHcPartyAndPatient(hcPartyId: String, secretPatientKeys: List<String>, pagination: PaginationOffset<ComplexKey>) = flow {
		val client = couchDbDispatcher.getClient(dbInstanceUrl)
		val keys = secretPatientKeys.map { fk -> ComplexKey.of(hcPartyId, fk) }

		val constrainedKeys = pagination.startKey?.let {
			keys.indexOf(it).takeIf { idx -> idx >= 0 }?.let { start ->
				keys.subList(start, keys.size)
			} ?: emptyList()
		} ?: keys

		when {
			constrainedKeys.isEmpty() -> {
				//Do nothing
			}

			pagination.startDocumentId == null || constrainedKeys.size == 1 -> {
				emitAll(
					client.queryView(
						createQuery(client, "by_hcparty_patient")
							.keys(constrainedKeys)
							.startDocId(pagination.startDocumentId)
							.includeDocs(true)
							.reduce(false)
							.limit(pagination.limit),
						Array<String>::class.java, String::class.java, CalendarItem::class.java
					)
				)
			}

			else -> {
				val count = client.queryView(
					createQuery(client, "by_hcparty_patient")
						.key(constrainedKeys[0])
						.startDocId(pagination.startDocumentId)
						.includeDocs(true)
						.reduce(false)
						.limit(pagination.limit),
					Array<String>::class.java, String::class.java, CalendarItem::class.java
				).onEach { emit(it) }.count { it is ViewRowWithDoc<*,*,*> }

				if (count < pagination.limit) {
					emitAll(
						client.queryView(
							createQuery(client, "by_hcparty_patient")
								.keys(constrainedKeys.subList(1, constrainedKeys.size))
								.includeDocs(true)
								.reduce(false)
								.limit(pagination.limit - count),
							Array<String>::class.java, String::class.java, CalendarItem::class.java
						)
					)
				}
			}
		}
	}

	@View(name = "by_recurrence_id", map = "classpath:js/calendarItem/by_recurrence_id.js")
	override fun listCalendarItemsByRecurrenceId(recurrenceId: String): Flow<CalendarItem> = flow {
		val client = couchDbDispatcher.getClient(dbInstanceUrl)
		val viewQuery = createQuery(client, "by_recurrence_id").key(recurrenceId).includeDocs(true)
		emitAll(client.queryViewIncludeDocsNoValue<String, CalendarItem>(viewQuery).map { it.doc })
	}
}
