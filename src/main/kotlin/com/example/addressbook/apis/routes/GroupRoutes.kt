package com.example.addressbook.apis.routes

import AppContext
import arrow.core.getOrElse
import arrow.core.right
import com.addressBook.dbSetup.connectToDatabase
import com.addressBook.dbSetup.resetDatabase
import com.addressBook.entryPoints.*
import com.addressBook.requests.AddGroupRequest
import com.addressBook.requests.EditGroupRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Route.groupRoutes() {
    val dbCon = connectToDatabase()
    route("/groups") {
        get {
            val allGroupsFetched = fetchAllGroups(AppContext(dbCon))
            call.respond(allGroupsFetched)
        }
        get("/{groupId}") {
            val groupId = call.parameters["groupId"]
            val groupFetched = fetchGroup(AppContext(dbCon), UUID.fromString(groupId)
                )
            call.respond(groupFetched)
        }
        post {
            val group = call.receive<AddGroupRequest>()
            val groupAdded = addGroup(AppContext(dbCon),
                AddGroupRequest(
                    group.groupName
                )
            )
            call.respond(groupAdded)
        }
        put("/{groupId}") {
            val groupId = call.parameters["groupId"]
            val groupEdited = editGroup(AppContext(dbCon), UUID.fromString(groupId),
                EditGroupRequest(
                    UUID.fromString(groupId),
                    "PDEU"
                )
            )
            call.respondText(groupEdited.toString(), status = HttpStatusCode.OK)
        }
        delete("/{groupId}") {
            val groupId = call.parameters["groupId"]
            val groupDeleted = deleteGroup(AppContext(dbCon), UUID.fromString(groupId)
                )
            call.respondText(groupDeleted.toString(), status = HttpStatusCode.OK)
        }


        get("/{groupId}/groupMembers") {
            val groupId = call.parameters["groupId"]
            val groupMembers = displayContactsByGroupId(AppContext(dbCon), UUID.fromString(groupId))
            call.respond(groupMembers)
        }
    }
}