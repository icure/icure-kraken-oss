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

package org.taktik.icure.asyncdao

import kotlinx.coroutines.flow.Flow
import org.taktik.couchdb.BulkUpdateResult
import org.taktik.couchdb.DocIdentifier
import org.taktik.icure.dao.Option
import org.taktik.icure.entities.base.Identifiable

interface InternalDAO<T : Identifiable<String>> {

    fun getAll(): Flow<T>
    fun getAllIds(): Flow<String>
    suspend fun get(id: String, vararg options: Option): T?
    suspend fun get(id: String, rev: String?, vararg options: Option): T?
    fun getList(ids: Collection<String>): Flow<T>
    suspend fun save(entity: T): T?
    fun save(entities: Flow<T>): Flow<DocIdentifier>
    suspend fun update(entity: T): T?
    fun list(ids: List<String>): Flow<T>

    fun save(entities: List<T>): Flow<DocIdentifier>
    suspend fun purge(entities: Flow<T>): Flow<BulkUpdateResult>
    suspend fun remove(entities: Flow<T>): Flow<BulkUpdateResult>

    suspend fun forceInitStandardDesignDocument(updateIfExists: Boolean)
}