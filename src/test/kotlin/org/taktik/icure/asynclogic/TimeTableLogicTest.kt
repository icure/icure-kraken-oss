package org.taktik.icure.asynclogic

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import org.taktik.icure.asynclogic.impl.TimeTableLogicImpl
import org.taktik.icure.entities.Agenda
import org.taktik.icure.entities.CalendarItemType
import org.taktik.icure.entities.TimeTable
import org.taktik.icure.entities.embed.TimeTableHour
import org.taktik.icure.entities.embed.TimeTableItem
import org.taktik.icure.properties.CouchDbProperties
import org.taktik.icure.test.SessionMock
import org.taktik.icure.test.fakedaos.FakeGroupDAO
import org.taktik.icure.test.fakedaos.FakeTimeTableDAO
import org.taktik.icure.test.newId
import org.taktik.icure.test.randomUri

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class TimeTableLogicTest : StringSpec({
	val hcpId = newId()
	val uri = randomUri()
	val groupId = newId()
	val agendaId = newId()
	lateinit var sessionMock: SessionMock
	lateinit var timeTableLogic: TimeTableLogic

	beforeEach {
		clearAllMocks()
		val agendaLogic = mockk<AgendaLogic>()
		coEvery { agendaLogic.getAgenda(any()) } answers { Agenda(id = firstArg()) }
		every { agendaLogic.getAnonymousAgendasByUser(any(), any()) } answers { flowOf(Agenda(id = agendaId, userId = secondArg())) }
		val calendarItemLogic = mockk<CalendarItemLogic>()
		every { calendarItemLogic.getCalendarItemByPeriodAndAgendaId(any(), any(), agendaId) } answers { flowOf() }
		every { calendarItemLogic.getCalendarItemByPeriodAndAgendaId(any(), any(), any(), agendaId) } answers { flowOf() }
		val calendarItemTypeLogic = mockk<CalendarItemTypeLogic>()
		every { calendarItemTypeLogic.getAnonymousCalendarItemTypes(any(), any()) } answers { secondArg<Collection<String>>().map { CalendarItemType(id = it) }.asFlow() }
		sessionMock = SessionMock()
		timeTableLogic = TimeTableLogicImpl(
			CouchDbProperties(),
			FakeTimeTableDAO(),
			FakeGroupDAO(),
			agendaLogic,
			calendarItemLogic,
			calendarItemTypeLogic,
			sessionMock.sessionLogic
		)
	}

	suspend fun <T> withAuthenticatedHcpContext(hcpId: String, block: suspend () -> T): T =
		sessionMock.withAuthenticatedHcpContext(uri, groupId, hcpId, block)

	suspend fun TimeTable.create(): TimeTable {
		check(rev == null)
		return withAuthenticatedHcpContext(hcpId) {
			timeTableLogic.createTimeTable(this).shouldNotBeNull()
		}
	}

	"Legacy timetables should return results" {
		val calendarItemTypeId = newId()
		TimeTable(
			id = newId(),
			agendaId = agendaId,
			startTime = 20221015170000L,
			endTime = 20321006170000L,
			items = listOf(
				TimeTableItem(
					rrule = "FREQ=WEEKLY;INTERVAL=1;UNTIL=20321006170000;BYDAY=SA,WE,TH,MO",
					hours = listOf(
						TimeTableHour(
							startHour = 80000,
							endHour = 170000,
						)
					),
					calendarItemTypeId = calendarItemTypeId,
					publicTimeTableItem = true,
				)
			),
		).create()

		withAuthenticatedHcpContext(hcpId) {
			val result = timeTableLogic.getAvailabilitiesByPeriodAndCalendarItemTypeId(groupId, newId(), 20221016100000L, 20221018220000L, calendarItemTypeId, null, false, true, hcpId).toList()
			result.size shouldBe 1
		}
	}

})
