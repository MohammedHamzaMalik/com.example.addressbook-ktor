package com.example.addressbook.apis.routes

import AppContext
import com.addressBook.dbSetup.connectToDatabase
import com.addressBook.dbSetup.resetDatabase
import com.addressBook.entryPoints.addGroup
import com.addressBook.entryPoints.fetchGroup
import com.addressBook.entryPoints.fetchAllGroups
import com.addressBook.requests.AddGroupRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Route.groupRoutes() {
    val dbCon = connectToDatabase()
    resetDatabase()
    route("/groups") {
        get {
            val allGroupsFetched = fetchAllGroups(AppContext(dbCon)).orNull()!!
            call.respond(allGroupsFetched)
        }
        get("/{groupId}") {
            val uuid = call.parameters["groupId"]!!
            val groupFetched = fetchGroup(AppContext(dbCon), UUID.fromString(uuid)
                ).orNull()!!
            call.respond(groupFetched)
        }
        post {
            val group = call.receive<AddGroupRequest>()
            val groupAdded = addGroup(AppContext(dbCon),
                AddGroupRequest(
                    group.groupName
                )
            ).orNull()!!
            call.respondText("Group added ${groupAdded.groupId}", status = HttpStatusCode.Created)
        }
        put("/{id}") {
            call.respondText("Update group with id ${call.parameters["id"]}")
        }
        delete("/{id}") {
            call.respondText("Delete group with id ${call.parameters["id"]}")
        }
    }
}