package io.wisoft.ktor

import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.setBody
import io.ktor.server.testing.withTestApplication
import io.ktor.util.KtorExperimentalAPI
import io.wisoft.ktor.domain.Customers
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@KtorExperimentalAPI
class CustomerRoutesTest {

    @AfterEach
    fun dropSchema(): Unit = transaction {
        SchemaUtils.drop(Customers)
    }

    @Test
    fun `새로운 고객 등록`(): Unit = withTestApplication({ module(testing = true) }) {
        handleRequest(HttpMethod.Post, "/customers") {
            addHeader(
                HttpHeaders.ContentType,
                ContentType.Application.Json.toString()
            )
            setBody(
                """{
                      "firstName": "Daemon",
                      "lastName": "Park",
                      "email": "moochi@kakao.com"
                      }
                """.trimIndent()
            )
        }.apply {
            assertEquals(HttpStatusCode.Created, response.status())
            assertEquals("Customer stored correctly", response.content)
        }
    }

    @Test
    fun `전체 고객 조회`(): Unit = withTestApplication({ module(testing = true) }) {
        handleRequest(HttpMethod.Post, "/customers") {
            addHeader(
                HttpHeaders.ContentType,
                ContentType.Application.Json.toString()
            )
            setBody(
                """{
                      "firstName": "Daemon",
                      "lastName": "Park",
                      "email": "moochi@kakao.com"
                      }
                """.trimIndent()
            )
        }
        with(handleRequest(HttpMethod.Get, "/customers")) {
            println(response.content)
            assertEquals(HttpStatusCode.OK, response.status())
        }
    }

    @Test
    fun `없는 고객 조회`(): Unit = withTestApplication({ module(testing = true) }) {
        with(handleRequest(HttpMethod.Get, "/customers/9999")) {
            assertEquals(HttpStatusCode.NotFound, response.status())
        }
    }

    @Test
    fun `아이디가 1인 고객 조회`(): Unit = withTestApplication({ module(testing = true) }) {
        handleRequest(HttpMethod.Post, "/customers") {
            addHeader(
                HttpHeaders.ContentType,
                ContentType.Application.Json.toString()
            )
            setBody(
                """{
                      "firstName": "Daemon",
                      "lastName": "Park",
                      "email": "moochi@kakao.com"
                      }
                """.trimIndent()
            )
        }
        with(handleRequest(HttpMethod.Get, "/customers/1")) {
            assertEquals(HttpStatusCode.OK, response.status())
        }
    }

    @Test
    fun `아이디가 1인 고객 수정`(): Unit = withTestApplication({ module(testing = true) }) {
        handleRequest(HttpMethod.Post, "/customers") {
            addHeader(
                HttpHeaders.ContentType,
                ContentType.Application.Json.toString()
            )
            setBody(
                """{
                      "firstName": "Daemon",
                      "lastName": "Park",
                      "email": "moochi@kakao.com"
                      }
                """.trimIndent()
            )
        }
        handleRequest(HttpMethod.Put, "/customers/1") {
            addHeader(
                HttpHeaders.ContentType,
                ContentType.Application.Json.toString()
            )
            setBody(
                """{
                      "firstName": "Moochi",
                      "lastName": "Park",
                      "email": "moochi@kakao.com"
                      }
                """.trimIndent()
            )
        }.apply {
            assertEquals(HttpStatusCode.NoContent, response.status())
        }
    }

    @Test
    fun `아이디가 1인 고객 삭제`(): Unit = withTestApplication({ module(testing = true) }) {
        handleRequest(HttpMethod.Post, "/customers") {
            addHeader(
                HttpHeaders.ContentType,
                ContentType.Application.Json.toString()
            )
            setBody(
                """{
                      "firstName": "Daemon",
                      "lastName": "Park",
                      "email": "moochi@kakao.com"
                      }
                """.trimIndent()
            )
        }
        with(handleRequest(HttpMethod.Delete, "/customers/1")) {
            assertEquals(HttpStatusCode.Accepted, response.status())
        }
    }
}
