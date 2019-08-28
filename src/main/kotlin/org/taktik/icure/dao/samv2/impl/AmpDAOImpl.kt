package org.taktik.icure.dao.samv2.impl

import org.ektorp.ComplexKey
import org.ektorp.support.View
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository
import org.taktik.icure.dao.impl.GenericDAOImpl
import org.taktik.icure.dao.impl.ektorp.CouchDbICureConnector
import org.taktik.icure.dao.impl.idgenerators.IDGenerator
import org.taktik.icure.dao.samv2.AmpDAO
import org.taktik.icure.db.PaginatedList
import org.taktik.icure.db.PaginationOffset
import org.taktik.icure.db.StringUtils
import org.taktik.icure.entities.samv2.Amp

@Repository("ampDAO")
@View(name = "all", map = "function(doc) { if (doc.java_type == 'org.taktik.icure.entities.samv2.Amp' && !doc.deleted) emit( null, doc._id )}")
class AmpDAOImpl @Autowired
constructor(@Qualifier("couchdbDrugs") couchdb: CouchDbICureConnector, idGenerator: IDGenerator) : GenericDAOImpl<Amp>(Amp::class.java, couchdb, idGenerator), AmpDAO {
    @View(name = "by_groupcode", map = "classpath:js/amp/By_groupcode.js")
    override fun findAmpsByVmpGroupCode(vmpgCode: String, paginationOffset: PaginationOffset<*>): PaginatedList<Amp> {
        val from = vmpgCode
        val to = vmpgCode

        return pagedQueryView(
                "by_groupcode",
                from,
                to,
                paginationOffset,
                false
        )
    }

    @View(name = "by_groupid", map = "classpath:js/amp/By_groupid.js")
    override fun findAmpsByVmpGroupId(vmpgId: String, paginationOffset: PaginationOffset<*>): PaginatedList<Amp> {
        val from = vmpgId
        val to = vmpgId

        return pagedQueryView(
                "by_groupid",
                from,
                to,
                paginationOffset,
                false
        )
    }

    @View(name = "by_code", map = "classpath:js/amp/By_code.js")
    override fun findAmpsByVmpCode(vmpCode: String, paginationOffset: PaginationOffset<*>): PaginatedList<Amp> {
        val from = vmpCode
        val to = vmpCode

        return pagedQueryView(
                "by_code",
                from,
                to,
                paginationOffset,
                false
        )
    }

    @View(name = "by_id", map = "classpath:js/amp/By_id.js")
    override fun findAmpsByVmpId(vmpId: String, paginationOffset: PaginationOffset<*>): PaginatedList<Amp> {
        val from = vmpId
        val to = vmpId

        return pagedQueryView(
                "by_id",
                from,
                to,
                paginationOffset,
                false
        )
    }

    override fun listAmpIdsByVmpGroupCode(vmpgCode: String, paginationOffset: PaginationOffset<*>): List<String> {
        val from = vmpgCode
        val to = vmpgCode

        return db.queryView(createQuery(
                "by_groupcode")
                .includeDocs(false)
                .startKey(from)
                .endKey(to), String::class.java)
    }

    override fun listAmpIdsByVmpGroupId(vmpgId: String, paginationOffset: PaginationOffset<*>): List<String> {
        val from = vmpgId
        val to = vmpgId

        return db.queryView(createQuery(
                "by_groupid")
                .includeDocs(false)
                .startKey(from)
                .endKey(to), String::class.java)
    }

    override fun listAmpIdsByVmpCode(vmpCode: String, paginationOffset: PaginationOffset<*>): List<String> {
        val from = vmpCode
        val to = vmpCode

        return db.queryView(createQuery(
                "by_code")
                .includeDocs(false)
                .startKey(from)
                .endKey(to), String::class.java)
    }

    override fun listAmpIdsByVmpId(vmpId: String, paginationOffset: PaginationOffset<*>): List<String> {
        val from = vmpId
        val to = vmpId

        return db.queryView(createQuery(
                "by_id")
                .includeDocs(false)
                .startKey(from)
                .endKey(to), String::class.java)
    }

    init {
        initStandardDesignDocument()
    }

    @View(name = "by_language_label", map = "classpath:js/amp/By_language_label.js")
    override fun findAmpsByLabel(language: String?, label: String?, pagination: PaginationOffset<*>?): PaginatedList<Amp> {
        val sanitizedLabel = label?.let { StringUtils.sanitizeString(it)}
        @Suppress("UNCHECKED_CAST") val startKey = if (pagination?.startKey == null) null else pagination.startKey as MutableList<Any?>
        if (startKey != null && startKey.size > 2 && startKey[2] != null) {
            startKey[2] = StringUtils.sanitizeString(startKey[2] as String)
        }
        val from = if (startKey == null)
            ComplexKey.of(
                    language ?: "\u0000",
                    if (sanitizedLabel == null) "\u0000" else sanitizedLabel
            )
        else
            ComplexKey.of(*startKey.toTypedArray())
        val to = ComplexKey.of(
                if (language == null) ComplexKey.emptyObject() else if (sanitizedLabel == null) language + "\ufff0" else language,
                if (sanitizedLabel == null) ComplexKey.emptyObject() else sanitizedLabel + "\ufff0"
        )

        return pagedQueryView(
                "by_language_label",
                from,
                to,
                pagination,
                false
        )
    }

    override fun listAmpIdsByLabel(language: String?, label: String?): List<String> {
        val sanitizedLabel= label?.let { StringUtils.sanitizeString(it) }
        val from =
                ComplexKey.of(
                        language ?: "\u0000",
                        if (sanitizedLabel == null) "\u0000" else sanitizedLabel
                )

        val to = ComplexKey.of(
                if (language == null) ComplexKey.emptyObject() else if (sanitizedLabel == null) language + "\ufff0" else language,
                if (sanitizedLabel == null) ComplexKey.emptyObject() else sanitizedLabel + "\ufff0"
        )

        return db.queryView(createQuery("by_language_label")
                .includeDocs(false)
                .startKey(from)
                .endKey(to), String::class.java)
    }

}
