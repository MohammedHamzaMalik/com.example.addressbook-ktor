package com.example.addressbook.apis.plugins

import com.example.addressbook.apis.routes.groupRoutes
import io.ktor.serialization.jackson.*
import com.fasterxml.jackson.databind.*
import io.ktor.server.response.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        jackson {
                enable(SerializationFeature.INDENT_OUTPUT)
            }
    }
    routing {
//        groupRoutes()
        get("/json/jackson") {
                call.respond(mapOf("hello" to "world"))
            }
    }
}
