package com.example

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.*

class OrderRouteTests {
    @Test
    fun testGetOrder() = testApplication {
        val response = client.get("/order/01/total")
        assertEquals("23.15", response.bodyAsText()
        )
        assertEquals(HttpStatusCode.OK, response.status)
    }
}