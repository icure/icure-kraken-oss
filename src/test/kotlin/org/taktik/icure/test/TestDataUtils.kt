package org.taktik.icure.test

import java.net.URI
import java.util.Base64
import java.util.UUID
import kotlin.random.Random
import com.ninjasquad.springmockk.isMock
import io.kotest.matchers.shouldBe
import io.mockk.MockK
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import org.apache.commons.codec.digest.DigestUtils
import org.springframework.core.io.buffer.DefaultDataBufferFactory
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.taktik.icure.asynclogic.AsyncSessionLogic
import org.taktik.icure.security.database.DatabaseUserDetails

val random = Random(System.currentTimeMillis())

fun ByteArray.asDataBufferFlow() = flowOf(DefaultDataBufferFactory.sharedInstance.wrap(this))

fun Iterable<ByteArray>.asDataBufferFlow() = asFlow().map { DefaultDataBufferFactory.sharedInstance.wrap(it) }

val ByteArray.sizeL get() = size.toLong()

val ByteArray.md5 get() = Base64.getEncoder().encodeToString(DigestUtils.md5(this))

fun newId() = UUID.randomUUID().toString()

fun randomBytes(size: Int) = random.nextBytes(size)

fun randomUri() = URI("https://example.com/${newId()}")

fun AsyncSessionLogic.setCurrentUserData(
	user: String,
	password: String
) {
	isMock shouldBe true
	val sessionContext = mockk<AsyncSessionLogic.AsyncSessionContext>()
	val authentication = mockk<Authentication>()
	val credentials = mockk<UsernamePasswordAuthenticationToken>()
	coEvery { getCurrentSessionContext() } returns sessionContext
	coEvery { sessionContext.getAuthentication() } returns authentication
	coEvery { authentication.credentials } returns credentials
	coEvery { credentials.principal } returns user
	coEvery { credentials.credentials } returns password
}
