package org.taktik.icure.services.external.rest.v2.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.github.pozo.KotlinBuilder

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@KotlinBuilder
data class ReplicateCommandDto(
	val sourceUrl: String,
	val sourceUsername: String,
	val sourcePassword: String,
	val targetUrl: String,
	val targetUsername: String,
	val targetPassword: String,
	val id: String? = null,
) {

}
