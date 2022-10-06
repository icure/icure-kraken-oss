package org.taktik.icure.asyncdao

import java.net.URI
import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.test.context.ActiveProfiles
import org.taktik.couchdb.ViewQueryResultEvent
import org.taktik.couchdb.ViewRowWithDoc
import org.taktik.icure.asynclogic.CodeLogic
import org.taktik.icure.db.PaginationOffset
import org.taktik.icure.entities.base.Code
import org.taktik.icure.services.external.rest.v1.dto.CodeDto
import org.taktik.icure.services.external.rest.v1.mapper.base.CodeMapper
import org.taktik.icure.services.external.rest.v1.utils.paginatedList
import org.taktik.icure.test.ICureTestApplication

suspend fun List<Code>.shouldContainAllTheVersions(codeDAO: CodeDAO) =
	this.groupBy {
		Pair(it.type!!, it.code!!)
	}.onEach {
		val versionCounter = codeDAO.listCodeIdsByTypeCodeVersionInterval(
			startType = it.key.first,
			startCode = it.key.second,
			startVersion = null,
			endType = it.key.first,
			endCode = it.key.second,
			endVersion = null
		).count()
		it.value.fold(setOf<String>()){ acc, code -> acc + code.id }.size shouldBe versionCounter
	}

suspend fun List<Code>.shouldContainOnlyLatestVersions(codeDAO: CodeDAO) =
	this.onEach {
		val versionCounter = codeDAO.listCodeIdsByTypeCodeVersionInterval(
			startType = it.type,
			startCode = it.code,
			startVersion = null,
			endType = it.type,
			endCode = it.code,
			endVersion = null
		).toList()
		val latestVersion = versionCounter.maxOrNull()?.let { codeId ->
			codeId.split('|')[2]
		}
		latestVersion shouldNotBe null
		it.version shouldNotBe null
		it.version shouldBe latestVersion
	}

suspend fun Flow<ViewQueryResultEvent>.toListOfCodes() =
	this.filter { it is ViewRowWithDoc<*, *, *> }
		.map { (it as ViewRowWithDoc<*, *, *>).doc as Code }
		.toList()

private const val TEST_CACHE = "build/tests/icureCache"

@SpringBootTest(
	classes = [ICureTestApplication::class],
	properties = [
		"spring.main.allow-bean-definition-overriding=true",
		"icure.objectstorage.icureCloudUrl=test",
		"icure.objectstorage.cacheLocation=$TEST_CACHE",
		"icure.objectstorage.backlogToObjectStorage=true",
		"icure.objectstorage.sizeLimit=1000",
		"icure.objectstorage.migrationDelayMs=1000",
	],
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("app")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CodeDAOTest(
	@Autowired val codeDAO: CodeDAO,
	@Autowired val codeMapper: CodeMapper,
	@Autowired val codeLogic: CodeLogic,
	@Autowired val objectMapper: ObjectMapper
): StringSpec() {

	val region = "be"
	val language = "fr"
	val codeType = "CD-FED-COUNTRY"
	val startPaginationOffset = PaginationOffset<List<String?>>(null, null, null, 1001)

	init {
		runBlocking {
			val resolver = PathMatchingResourcePatternResolver(javaClass.classLoader)

			// Imports the codes into the database
			resolver.getResources("classpath*:/org/taktik/icure/db/codes/region_codes.json").forEach {
					codeLogic.importCodesFromJSON(it.inputStream)
			}

			val totalUniqueCodes = codeDAO.listCodeIdsByTypeCodeVersionInterval(
				startType = codeType,
				startCode = null,
				startVersion = null,
				endType = codeType,
				endCode = null,
				endVersion = null
			).fold(setOf<String>()) { acc, it ->
				val (type, code, _) = it.split('|')
				acc + "${type}|${code}"
			}.count()

			testLatestVersionFilterOnFindCodesBy(codeDAO, region, codeType, startPaginationOffset, totalUniqueCodes, codeMapper)
			testLatestVersionFilterOnFindCodesByLanguageLabel(codeDAO, region, language, startPaginationOffset, totalUniqueCodes, codeMapper)
			testLatestVersionFilterOnFindCodesByLanguageTypeLabel(codeDAO, region, language, codeType, startPaginationOffset, totalUniqueCodes, codeMapper)
			testLatestVersionFilterOnListCodesBy(codeDAO, region, codeType, totalUniqueCodes)

		}
	}
}

private suspend fun StringSpec.testLatestVersionFilterOnListCodesBy(
	codeDAO: CodeDAO,
	region: String,
	codeType: String,
	totalUniqueCodes: Int
){

	"listCodesBy should return all the versions of the codes if no version or code are specified" {
		val codes = codeDAO.listCodesBy(region, codeType, null, null).toList()
		codes.size shouldBeGreaterThan 0
		codes.shouldContainAllTheVersions(codeDAO)
	}

	"listCodesBy should return all the versions of a single code if no version is specified " {
		val codes = codeDAO.listCodesBy(region, codeType, "pg", null).toList()
		codes.size shouldBeGreaterThan 0
		codes.shouldContainAllTheVersions(codeDAO)
	}

	"listCodesBy should return a single version of a single code if all parameters are specified" {
		val version = "1.2"
		val codes = codeDAO.listCodesBy(region, codeType, "pg", version).toList()
		codes.size shouldBe 1
		codes[0].version shouldNotBe null
		codes[0].version shouldBe version
	}

	"listCodesBy should return the latest version of a single code if all parameters are specified" {
		val codes = codeDAO.listCodesBy(region, codeType, "pg", "latest").toList()
		codes.size shouldBe 1
		codes.shouldContainOnlyLatestVersions(codeDAO)
	}

	"listCodesBy should return the latest version of all the codes if no code is specified" {
		val codes = codeDAO.listCodesBy(region, codeType, null, "latest").toList()
		codes.size shouldBe totalUniqueCodes
		codes.shouldContainOnlyLatestVersions(codeDAO)
	}

}

private suspend fun StringSpec.testLatestVersionFilterOnFindCodesByLanguageTypeLabel(
	codeDAO: CodeDAO,
	region: String,
	language: String,
	codeType: String,
	startPaginationOffset: PaginationOffset<List<String?>>,
	totalUniqueCodes: Int,
	codeMapper: CodeMapper,
	codeToCodeDto: (Code) -> CodeDto = { it: Code -> codeMapper.map(it) }
) {

	"findCodesBy should return all the versions of the codes if no version, type, or label are specified" {
		val codes = codeDAO.findCodesByLabel(region, language, null, null, null, startPaginationOffset)
			.toListOfCodes()
		codes.size shouldBeGreaterThanOrEqual totalUniqueCodes
		codes.shouldContainAllTheVersions(codeDAO)
	}

	"findCodesBy should return all the versions of the matching codes if no version or label are specified" {
		val codes = codeDAO.findCodesByLabel(region, language, codeType, null, null, startPaginationOffset)
			.toListOfCodes()
		codes.size shouldBeGreaterThan 0
		codes.shouldContainAllTheVersions(codeDAO)
	}

	"findCodesBy should return all the versions of the matching codes of a type if no version is specified" {
		val codes = codeDAO.findCodesByLabel(region, language, codeType, "al", null, startPaginationOffset)
			.toListOfCodes()
		codes.size shouldBeGreaterThan 0
		codes.shouldContainAllTheVersions(codeDAO)
	}

	"findCodesBy should return a specific version of all the codes if a version and type but no label are specified" {
		val version = "1.2"
		val codes = codeDAO.findCodesByLabel(region, language, codeType, null, version, startPaginationOffset)
			.toListOfCodes()
		codes.size shouldBeGreaterThanOrEqual totalUniqueCodes
		codes.onEach {
			it.version shouldNotBe null
			it.version shouldBe version
		}
	}

	"findCodesBy should return a specific version of the codes if a version and type but no label are specified paginating the results" {
		val version = "1.2"
		val totalSize = codeDAO.findCodesByLabel(region, language, codeType,null, version, startPaginationOffset)
			.toListOfCodes().size
		totalSize shouldBeGreaterThanOrEqual totalUniqueCodes
		val pageSize = totalSize / 2
		val reducedPaginationOffset = PaginationOffset<List<String?>>(null, null, null, pageSize + 1)
		var page = codeDAO.findCodesByLabel(region, language, codeType, null, version, reducedPaginationOffset)
			.paginatedList(codeToCodeDto, pageSize)
		val codes = page.rows.map { codeMapper.map(it) }.toMutableList()
		while(page.nextKeyPair != null) {
			val newOffset = PaginationOffset<List<String?>>(
				(page.nextKeyPair!!.startKey!! as Array<String>).toList(),
				page.nextKeyPair!!.startKeyDocId, null, pageSize + 1)
			page = codeDAO.findCodesByLabel(region, language, codeType, null, version, newOffset)
				.paginatedList(codeToCodeDto, pageSize)
			codes.addAll(page.rows.map { codeMapper.map(it) })
		}
		codes.size shouldBe totalSize
		codes.onEach {
			it.version shouldNotBe null
			it.version shouldBe version
		}
	}

	"findCodesBy should return a specific version of all the codes if latest version and type but no label are specified" {
		val codes = codeDAO.findCodesByLabel(region, language, codeType, null, "latest", startPaginationOffset)
			.toListOfCodes()
		codes.size shouldBeGreaterThanOrEqual totalUniqueCodes
		codes.shouldContainOnlyLatestVersions(codeDAO)
	}

	"findCodesBy should return a specific version of the codes if latest version and type but no label are specified paginating the results dividing them in a half" {
		val totalSize = codeDAO.findCodesByLabel(region, language, codeType, null, "latest", startPaginationOffset)
			.toListOfCodes().size
		totalSize shouldBeGreaterThanOrEqual totalUniqueCodes
		val pageSize = totalSize / 2
		val reducedPaginationOffset = PaginationOffset<List<String?>>(null, null, null, pageSize + 1)
		var page = codeDAO.findCodesByLabel(region, language, codeType, null, "latest", reducedPaginationOffset)
			.paginatedList(codeToCodeDto, pageSize)
		val codes = page.rows.map { codeMapper.map(it) }.toMutableList()
		while(page.nextKeyPair != null) {
			val newOffset = PaginationOffset<List<String?>>(
				(page.nextKeyPair!!.startKey!! as Array<String>).toList(),
				page.nextKeyPair!!.startKeyDocId, null, pageSize + 1)
			page = codeDAO.findCodesByLabel(region, language, codeType, null, "latest", newOffset)
				.paginatedList(codeToCodeDto, pageSize)
			codes.addAll(page.rows.map { codeMapper.map(it) })
		}
		codes.size shouldBeGreaterThanOrEqual totalSize
		codes.shouldContainOnlyLatestVersions(codeDAO)
	}

	"findCodesBy should return a specific version of all the codes if a version, type, and a label are specified" {
		val version = "1.2"
		val codes = codeDAO.findCodesByLabel(region, language, codeType, "al", version, startPaginationOffset)
			.toListOfCodes()
		codes.size shouldBeGreaterThan 0
		codes.onEach {
			it.version shouldNotBe null
			it.version shouldBe version
		}
	}

	"findCodesBy should return a specific version of the codes if a version, type, and label are specified paginating the results" {
		val version = "1.2"
		val label = "al"
		val totalSize = codeDAO.findCodesByLabel(region, language, codeType, label, version, startPaginationOffset)
			.toListOfCodes().size
		totalSize shouldBeGreaterThan 0
		val pageSize = totalSize / 2
		val reducedPaginationOffset = PaginationOffset<List<String?>>(null, null, null, pageSize + 1)
		var page = codeDAO.findCodesByLabel(region, language, codeType, label, version, reducedPaginationOffset)
			.paginatedList(codeToCodeDto, pageSize)
		val codes = page.rows.map { codeMapper.map(it) }.toMutableList()
		while(page.nextKeyPair != null) {
			val newOffset = PaginationOffset<List<String?>>(
				(page.nextKeyPair!!.startKey!! as Array<String>).toList(),
				page.nextKeyPair!!.startKeyDocId, null, pageSize + 1)
			page = codeDAO.findCodesByLabel(region, language, codeType, label, version, newOffset)
				.paginatedList(codeToCodeDto, pageSize)
			codes.addAll(page.rows.map { codeMapper.map(it) })
		}
		codes.size shouldBe totalSize
		codes.onEach {
			it.version shouldNotBe null
			it.version shouldBe version
		}
	}

	"findCodesBy should return a specific version of all the codes if latest version, type, and a label are specified" {
		val codes = codeDAO.findCodesByLabel(region, language, codeType, "al", "latest", startPaginationOffset)
			.toListOfCodes()
		codes.size shouldBeGreaterThan 0
		codes.shouldContainOnlyLatestVersions(codeDAO)
	}

	"findCodesBy should return a specific version of the codes if latest version, type, and a label are specified paginating the results dividing them in a half" {
		val label = "al"
		val totalSize = codeDAO.findCodesByLabel(region, language, codeType, label, "latest", startPaginationOffset)
			.toListOfCodes().size
		totalSize shouldBeGreaterThan 0
		val pageSize = totalSize / 2
		val reducedPaginationOffset = PaginationOffset<List<String?>>(null, null, null, pageSize + 1)
		var page = codeDAO.findCodesByLabel(region, language, codeType, label, "latest", reducedPaginationOffset)
			.paginatedList(codeToCodeDto, pageSize)
		val codes = page.rows.map { codeMapper.map(it) }.toMutableList()
		while(page.nextKeyPair != null) {
			val newOffset = PaginationOffset<List<String?>>(
				(page.nextKeyPair!!.startKey!! as Array<String>).toList(),
				page.nextKeyPair!!.startKeyDocId, null, pageSize + 1)
			page = codeDAO.findCodesByLabel(region, language, codeType, label, "latest", newOffset)
				.paginatedList(codeToCodeDto, pageSize)
			codes.addAll(page.rows.map { codeMapper.map(it) })
		}
		codes.size shouldBeGreaterThanOrEqual totalSize
		codes.shouldContainOnlyLatestVersions(codeDAO)
	}
}

private suspend fun StringSpec.testLatestVersionFilterOnFindCodesByLanguageLabel(
	codeDAO: CodeDAO,
	region: String,
	language: String,
	startPaginationOffset: PaginationOffset<List<String?>>,
	totalUniqueCodes: Int,
	codeMapper: CodeMapper,
	codeToCodeDto: (Code) -> CodeDto = { it: Code -> codeMapper.map(it) }
) {

	"findCodesBy should return all the versions of the codes if no version or label are specified" {
		val codes = codeDAO.findCodesByLabel(region, language, null, null, startPaginationOffset)
			.toListOfCodes()
		codes.size shouldBeGreaterThanOrEqual totalUniqueCodes
		codes.shouldContainAllTheVersions(codeDAO)
	}

	"findCodesBy should return all the versions of the matching codes if no version is specified" {
		val codes = codeDAO.findCodesByLabel(region, language, "al", null, startPaginationOffset)
			.toListOfCodes()
		codes.size shouldBeGreaterThan 0
		codes.shouldContainAllTheVersions(codeDAO)
	}

	"findCodesBy should return a specific version of all the codes if a version but no label are specified" {
		val version = "1.2"
		val codes = codeDAO.findCodesByLabel(region, language, null, version, startPaginationOffset)
			.toListOfCodes()
		codes.size shouldBeGreaterThanOrEqual totalUniqueCodes
		codes.onEach {
			it.version shouldNotBe null
			it.version shouldBe version
		}
	}

	"findCodesBy should return a specific version of the codes if a version but no label are specified paginating the results" {
		val version = "1.2"
		val totalSize = codeDAO.findCodesByLabel(region, language, null, version, startPaginationOffset)
			.toListOfCodes().size
		totalSize shouldBeGreaterThanOrEqual totalUniqueCodes
		val pageSize = totalSize / 2
		val reducedPaginationOffset = PaginationOffset<List<String?>>(null, null, null, pageSize + 1)
		var page = codeDAO.findCodesByLabel(region, language, null, version, reducedPaginationOffset)
			.paginatedList(codeToCodeDto, pageSize)
		val codes = page.rows.map { codeMapper.map(it) }.toMutableList()
		while(page.nextKeyPair != null) {
			val newOffset = PaginationOffset<List<String?>>(
				(page.nextKeyPair!!.startKey!! as Array<String>).toList(),
				page.nextKeyPair!!.startKeyDocId, null, pageSize + 1)
			page = codeDAO.findCodesByLabel(region, language, null, version, newOffset)
				.paginatedList(codeToCodeDto, pageSize)
			codes.addAll(page.rows.map { codeMapper.map(it) })
		}
		codes.size shouldBe totalSize
		codes.onEach {
			it.version shouldNotBe null
			it.version shouldBe version
		}
	}

	"findCodesBy should return a specific version of all the codes if latest version but no label are specified" {
		val codes = codeDAO.findCodesByLabel(region, language, null, "latest", startPaginationOffset)
			.toListOfCodes()
		codes.size shouldBeGreaterThanOrEqual totalUniqueCodes
		codes.shouldContainOnlyLatestVersions(codeDAO)
	}

	"findCodesBy should return a specific version of the codes if latest version but no label are specified paginating the results dividing them in a half" {
		val totalSize = codeDAO.findCodesByLabel(region, language, null, "latest", startPaginationOffset)
			.toListOfCodes().size
		totalSize shouldBeGreaterThanOrEqual totalUniqueCodes
		val pageSize = totalSize / 2
		val reducedPaginationOffset = PaginationOffset<List<String?>>(null, null, null, pageSize + 1)
		var page = codeDAO.findCodesByLabel(region, language, null, "latest", reducedPaginationOffset)
			.paginatedList(codeToCodeDto, pageSize)
		val codes = page.rows.map { codeMapper.map(it) }.toMutableList()
		while(page.nextKeyPair != null) {
			val newOffset = PaginationOffset<List<String?>>(
				(page.nextKeyPair!!.startKey!! as Array<String>).toList(),
				page.nextKeyPair!!.startKeyDocId, null, pageSize + 1)
			page = codeDAO.findCodesByLabel(region, language, null, "latest", newOffset)
				.paginatedList(codeToCodeDto, pageSize)
			codes.addAll(page.rows.map { codeMapper.map(it) })
		}
		codes.size shouldBeGreaterThanOrEqual totalSize
		codes.shouldContainOnlyLatestVersions(codeDAO)
	}

	"findCodesBy should return a specific version of all the codes if a version and a label are specified" {
		val version = "1.2"
		val codes = codeDAO.findCodesByLabel(region, language, "al", version, startPaginationOffset)
			.toListOfCodes()
		codes.size shouldBeGreaterThan 0
		codes.onEach {
			it.version shouldNotBe null
			it.version shouldBe version
		}
	}

	"findCodesBy should return a specific version of the codes if a version and a label are specified paginating the results" {
		val version = "1.2"
		val label = "al"
		val totalSize = codeDAO.findCodesByLabel(region, language, label, version, startPaginationOffset)
			.toListOfCodes().size
		totalSize shouldBeGreaterThan 0
		val pageSize = totalSize / 2
		val reducedPaginationOffset = PaginationOffset<List<String?>>(null, null, null, pageSize + 1)
		var page = codeDAO.findCodesByLabel(region, language, label, version, reducedPaginationOffset)
			.paginatedList(codeToCodeDto, pageSize)
		val codes = page.rows.map { codeMapper.map(it) }.toMutableList()
		while(page.nextKeyPair != null) {
			val newOffset = PaginationOffset<List<String?>>(
				(page.nextKeyPair!!.startKey!! as Array<String>).toList(),
				page.nextKeyPair!!.startKeyDocId, null, pageSize + 1)
			page = codeDAO.findCodesByLabel(region, language, label, version, newOffset)
				.paginatedList(codeToCodeDto, pageSize)
			codes.addAll(page.rows.map { codeMapper.map(it) })
		}
		codes.size shouldBe totalSize
		codes.onEach {
			it.version shouldNotBe null
			it.version shouldBe version
		}
	}

	"findCodesBy should return a specific version of all the codes if latest version and a label are specified" {
		val codes = codeDAO.findCodesByLabel(region, language, "al", "latest", startPaginationOffset)
			.toListOfCodes()
		codes.size shouldBeGreaterThan 0
		codes.shouldContainOnlyLatestVersions(codeDAO)
	}

	"findCodesBy should return a specific version of the codes if latest version and a label are specified paginating the results dividing them in a half" {
		val label = "al"
		val totalSize = codeDAO.findCodesByLabel(region, language, label, "latest", startPaginationOffset)
			.toListOfCodes().size
		totalSize shouldBeGreaterThan 0
		val pageSize = totalSize / 2
		val reducedPaginationOffset = PaginationOffset<List<String?>>(null, null, null, pageSize + 1)
		var page = codeDAO.findCodesByLabel(region, language, label, "latest", reducedPaginationOffset)
			.paginatedList(codeToCodeDto, pageSize)
		val codes = page.rows.map { codeMapper.map(it) }.toMutableList()
		while(page.nextKeyPair != null) {
			val newOffset = PaginationOffset<List<String?>>(
				(page.nextKeyPair!!.startKey!! as Array<String>).toList(),
				page.nextKeyPair!!.startKeyDocId, null, pageSize + 1)
			page = codeDAO.findCodesByLabel(region, language, label, "latest", newOffset)
				.paginatedList(codeToCodeDto, pageSize)
			codes.addAll(page.rows.map { codeMapper.map(it) })
		}
		codes.size shouldBeGreaterThanOrEqual totalSize
		codes.shouldContainOnlyLatestVersions(codeDAO)
	}
}

private suspend fun StringSpec.testLatestVersionFilterOnFindCodesBy(
	codeDAO: CodeDAO,
	region: String,
	codeType: String,
	startPaginationOffset: PaginationOffset<List<String?>>,
	totalUniqueCodes: Int,
	codeMapper: CodeMapper,
	codeToCodeDto: (Code) -> CodeDto = { it: Code -> codeMapper.map(it) }
){

	"findCodesBy should return all the versions of the codes if no version or code are specified" {
		val codes = codeDAO.findCodesBy(region, codeType, null, null, startPaginationOffset, 1f)
			.toListOfCodes()
		codes.size shouldBeGreaterThan 0
		codes.shouldContainAllTheVersions(codeDAO)
	}

	"findCodesBy should return all the versions of a single code if no version is specified " {
		val codes = codeDAO.findCodesBy(region, codeType, "pg", null, startPaginationOffset, 1f)
			.toListOfCodes()
		codes.size shouldBeGreaterThan 0
		codes.shouldContainAllTheVersions(codeDAO)
	}

	"findCodesBy should return a single version of a single code if all parameters are specified" {
		val version = "1.2"
		val codes = codeDAO.findCodesBy(region, codeType, "pg", version, startPaginationOffset, 1f)
			.toListOfCodes()
		codes.size shouldBe 1
		codes[0].version shouldNotBe null
		codes[0].version shouldBe version
	}

	"findCodesBy should return the latest version of a single code if all parameters are specified" {
		val codes = codeDAO.findCodesBy(region, codeType, "pg", "latest", startPaginationOffset, 1f)
			.toListOfCodes()
		codes.size shouldBe 1
		codes.shouldContainOnlyLatestVersions(codeDAO)
	}

	"findCodesBy should return the latest version of all the codes if no code is specified" {
		val codes = codeDAO.findCodesBy(region, codeType, null, "latest", startPaginationOffset, 1f)
			.toListOfCodes()
		codes.size shouldBe totalUniqueCodes
		codes.shouldContainOnlyLatestVersions(codeDAO)
	}

	"findCodesBy should return the latest version of all the codes if no code is specified and the results are paginated" {
		val pageSize = totalUniqueCodes / 2
		val reducedPaginationOffset = PaginationOffset<List<String?>>(null, null, null, pageSize + 1)
		var page = codeDAO.findCodesBy(region, codeType, null, "latest", reducedPaginationOffset, 1f)
			.paginatedList(codeToCodeDto, pageSize)
		val codes = page.rows.map { codeMapper.map(it) }.toMutableList()
		while(page.nextKeyPair != null) {
			val newOffset = PaginationOffset<List<String?>>(
				(page.nextKeyPair!!.startKey!! as Array<String>).toList(),
				page.nextKeyPair!!.startKeyDocId, null, pageSize + 1)
			page = codeDAO.findCodesBy(region, codeType, null, "latest", newOffset, 1f)
				.paginatedList(codeToCodeDto, pageSize)
			codes.addAll(page.rows.map { codeMapper.map(it) })
		}
		codes.size shouldBe totalUniqueCodes
		codes.shouldContainOnlyLatestVersions(codeDAO)
	}

}
