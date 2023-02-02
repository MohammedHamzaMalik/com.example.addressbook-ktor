package com.example.addressbook.apis.plugins

import com.example.addressbook.apis.routes.groupRoutes
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    routing {
        groupRoutes()
    }
}
