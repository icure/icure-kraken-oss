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

//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.careteam

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.github.pozo.KotlinBuilder
import org.taktik.couchdb.entity.Attachment
import org.taktik.icure.entities.base.StoredDocument
import org.taktik.icure.entities.embed.RevisionInfo
import org.taktik.icure.fhir.entities.r4.DomainResource
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.Resource
import org.taktik.icure.fhir.entities.r4.annotation.Annotation
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.contactpoint.ContactPoint
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.narrative.Narrative
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.reference.Reference

/**
 * Planned participants in the coordination and delivery of care for a patient or group
 *
 * The Care Team includes all the people and organizations who plan to participate in the
 * coordination and delivery of care for a patient.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@KotlinBuilder
data class CareTeam(
        @JsonProperty("_rev")
  override val rev: String? = null,
        @JsonProperty("deleted")
  override val deletionDate: Long? = null,
        @JsonProperty("_attachments")
  override val attachments: Map<String, Attachment>? = null,
        @JsonProperty("_revs_info")
  override val revisionsInfo: List<RevisionInfo>? = null,
        @JsonProperty("_conflicts")
  override val conflicts: List<String>? = null,
        @JsonProperty("rev_history")
  override val revHistory: Map<String, String>? = null,
        val category: List<CodeableConcept> = listOf(),
        override val contained: List<Resource> = listOf(),
        /**
   * Encounter created as part of
   */
  val encounter: Reference? = null,
        override val extension: List<Extension> = listOf(),
        /**
   * Logical id of this artifact
   */
  @JsonProperty("_id")
  override val id: String,
        val identifier: List<Identifier> = listOf(),
        /**
   * A set of rules under which this content was created
   */
  override val implicitRules: String? = null,
        /**
   * Language of the resource content
   */
  override val language: String? = null,
        val managingOrganization: List<Reference> = listOf(),
        /**
   * Metadata about the resource
   */
  override val meta: Meta? = null,
        override val modifierExtension: List<Extension> = listOf(),
        /**
   * Name of the team, such as crisis assessment team
   */
  val name: String? = null,
        val note: List<Annotation> = listOf(),
        val participant: List<CareTeamParticipant> = listOf(),
        /**
   * Time period team covers
   */
  val period: Period? = null,
        val reasonCode: List<CodeableConcept> = listOf(),
        val reasonReference: List<Reference> = listOf(),
        /**
   * proposed | active | suspended | inactive | entered-in-error
   */
  val status: String? = null,
        /**
   * Who care team is for
   */
  val subject: Reference? = null,
        val telecom: List<ContactPoint> = listOf(),
        /**
   * Text summary of the resource, for human interpretation
   */
  override val text: Narrative? = null
) : StoredDocument, DomainResource {
  override fun withIdRev(id: String?, rev: String): CareTeam = if (id != null) this.copy(id = id,
      rev = rev) else this.copy(rev = rev)

  override fun withDeletionDate(deletionDate: Long?): CareTeam = this.copy(deletionDate =
      deletionDate)
}
