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

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.ektorp.support.View
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository
import org.taktik.couchdb.entity.ComplexKey
import org.taktik.couchdb.queryViewIncludeDocs
import org.taktik.icure.asyncdao.ReceiptDAO
import org.taktik.icure.dao.impl.idgenerators.IDGenerator
import org.taktik.icure.entities.Receipt
import org.taktik.icure.properties.CouchDbProperties
import org.taktik.icure.utils.createQuery
import java.net.URI

@Repository("receiptDAO")
@View(name = "all", map = "function(doc) { if (doc.java_type === 'org.taktik.icure.entities.Receipt' && !doc.deleted) emit(null, doc._id)}")
class ReceiptDAOImpl(couchDbProperties: CouchDbProperties,
                     @Qualifier("healthdataCouchDbDispatcher") couchDbDispatcher: CouchDbDispatcher, idGenerator: IDGenerator) : GenericIcureDAOImpl<Receipt>(Receipt::class.java, couchDbProperties, couchDbDispatcher, idGenerator), ReceiptDAO {
    @View(name = "by_reference", map = "classpath:js/receipt/By_ref.js")
    override fun listByReference(ref: String): Flow<Receipt> {
        val client = couchDbDispatcher.getClient(dbInstanceUrl)
        return client.queryViewIncludeDocs<String, String, Receipt>(createQuery<Receipt>("by_reference").startKey(ref).endKey(ref + "\ufff0").includeDocs(true)).map { it.doc }
    }

    @View(name = "by_date", map = "function(doc) { if (doc.java_type === 'org.taktik.icure.entities.Receipt' && !doc.deleted) emit(doc.created)}")
    override fun listAfterDate(date: Long): Flow<Receipt> {
        val client = couchDbDispatcher.getClient(dbInstanceUrl)
        return client.queryViewIncludeDocs<String, String, Receipt>(createQuery<Receipt>("by_date").startKey(999999999999L).endKey(date).descending(true).includeDocs(true)).map { it.doc }
    }

    @View(name = "by_category", map = "function(doc) { if (doc.java_type === 'org.taktik.icure.entities.Receipt' && !doc.deleted) emit([doc.category,doc.subCategory,doc.created])}")
    override fun listByCategory(category: String, subCategory: String, startDate: Long, endDate: Long): Flow<Receipt> {
        val client = couchDbDispatcher.getClient(dbInstanceUrl)
        return client.queryViewIncludeDocs<Array<String>, String,Receipt>(createQuery<Receipt>("by_date").startKey(ComplexKey.of(category, subCategory, startDate ?: 999999999999L)).endKey(ComplexKey.of(category, subCategory, endDate)).descending(true).includeDocs(true)).map { it.doc }
    }

    @View(name = "by_doc_id", map = "function(doc) { if (doc.java_type === 'org.taktik.icure.entities.Receipt' && !doc.deleted) emit(doc.documentId)}")
    override fun listByDocId(date: Long): Flow<Receipt> {
        val client = couchDbDispatcher.getClient(dbInstanceUrl)
        return client.queryViewIncludeDocs<String, String, Receipt>(createQuery<Receipt>("by_date").startKey(999999999999L).endKey(date).descending(true).includeDocs(true)).map{ it.doc }
    }
}