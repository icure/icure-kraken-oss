package org.taktik.icure.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.github.pozo.KotlinBuilder
import org.ektorp.Attachment
import org.taktik.icure.entities.base.CodeStub
import org.taktik.icure.entities.base.Encryptable
import org.taktik.icure.entities.base.StoredICureDocument
import org.taktik.icure.entities.embed.Delegation
import org.taktik.icure.entities.embed.RevisionInfo
import org.taktik.icure.utils.DynamicInitializer
import org.taktik.icure.utils.invoke
import org.taktik.icure.validation.AutoFix
import org.taktik.icure.validation.NotNull
import org.taktik.icure.validation.ValidCode

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@KotlinBuilder
data class Classification(
        @JsonProperty("_id") override val id: String,
        @JsonProperty("_rev") override val rev: String? = null,
        @field:NotNull(autoFix = AutoFix.NOW) override val created: Long? = null,
        @field:NotNull(autoFix = AutoFix.NOW) override val modified: Long? = null,
        @field:NotNull(autoFix = AutoFix.CURRENTUSERID) override val author: String? = null,
        @field:NotNull(autoFix = AutoFix.CURRENTHCPID) override val responsible: String? = null,
        override val medicalLocationId: String? = null,
        @field:ValidCode(autoFix = AutoFix.NORMALIZECODE) override val tags: Set<CodeStub> = setOf(),
        @field:ValidCode(autoFix = AutoFix.NORMALIZECODE) override val codes: Set<CodeStub> = setOf(),
        override val endOfLife: Long? = null,
        @JsonProperty("deleted") override val deletionDate: Long? = null,

        val parentId: String?,
        val label: String = "",

        override val secretForeignKeys: Set<String> = setOf(),
        override val cryptedForeignKeys: Map<String, Set<Delegation>> = mapOf(),
        override val delegations: Map<String, Set<Delegation>> = mapOf(),
        override val encryptionKeys: Map<String, Set<Delegation>> = mapOf(),
        override val encryptedSelf: String? = null,
        @JsonProperty("_attachments") override val attachments: Map<String, Attachment>? = null,
        @JsonProperty("_revs_info") override val revisionsInfo: List<RevisionInfo>? = null,
        @JsonProperty("_conflicts") override val conflicts: List<String>? = null,
        @JsonProperty("rev_history") override val revHistory: Map<String, String>? = null

) : StoredICureDocument, Encryptable {
    companion object : DynamicInitializer<Classification>

    fun merge(other: Classification) = Classification(args = this.solveConflictsWith(other))
    fun solveConflictsWith(other: Classification) = super<StoredICureDocument>.solveConflictsWith(other) + super<Encryptable>.solveConflictsWith(other) + mapOf(
            "parentId" to (this.parentId ?: other.parentId),
            "label" to if (this.label.isBlank()) other.label else this.label
    )

    override fun withIdRev(id: String?, rev: String) = if (id != null) this.copy(id = id, rev = rev) else this.copy(rev = rev)
    override fun withDeletionDate(deletionDate: Long?) = this.copy(deletionDate = deletionDate)
    override fun withTimestamps(created: Long?, modified: Long?) =
            when {
                created != null && modified != null -> this.copy(created = created, modified = modified)
                created != null -> this.copy(created = created)
                modified != null -> this.copy(modified = modified)
                else -> this
            }
}
