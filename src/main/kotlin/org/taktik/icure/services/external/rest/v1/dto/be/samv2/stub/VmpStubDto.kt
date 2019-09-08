package org.taktik.icure.entities.samv2.stub

import org.taktik.icure.entities.samv2.embed.StoredDocumentWithPeriod
import org.taktik.icure.services.external.rest.v1.dto.be.samv2.embed.SamTextDto
import org.taktik.icure.services.external.rest.v1.dto.be.samv2.stub.VmpGroupStubDto
import java.io.Serializable

class VmpStubDto(
        id: String? = null,
        var code: String? = null,
        var vmpGroup: VmpGroupStubDto? = null,
        var name: SamTextDto? = null
) : StoredDocumentWithPeriod(id), Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as VmpStubDto

        if (code != other.code) return false
        if (vmpGroup != other.vmpGroup) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (code?.hashCode() ?: 0)
        result = 31 * result + (vmpGroup?.hashCode() ?: 0)
        result = 31 * result + (name?.hashCode() ?: 0)
        return result
    }
}
