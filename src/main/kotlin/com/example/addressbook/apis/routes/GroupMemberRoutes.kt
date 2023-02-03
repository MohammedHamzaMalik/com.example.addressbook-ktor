package com.example.addressbook.apis.routes

import AppContext
import com.addressBook.dbSetup.connectToDatabase
import com.addressBook.entryPoints.*
import com.addressBook.requests.*
import com.commandPattern.addressBook.requests.AddContactRequest
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Route.groupMemberRoutes() {
    val dbCon = connectToDatabase()
    route("/groupMembers"){
        get {
            val allGroupMembersFetched = displayAllGroupMembersOfAllGroups(AppContext(dbCon))
            call.respond(allGroupMembersFetched)
        }
        get("/{groupId}/{contactId}") {
            val groupId = call.parameters["groupId"]
            val contactId = call.parameters["contactId"]
            val fetchedGroupMember = fetchGroupMember(AppContext(dbCon),
                    FetchGroupMemberRequest(
                        UUID.fromString(groupId),
                        UUID.fromString(contactId)
                    )
                )
            call.respond(fetchedGroupMember)
        }
        post {
            val groupMember = call.receive<AddGroupMemberRequest>()
            val contactAdded = addGroupMember(AppContext(connectToDatabase()),
                    AddGroupMemberRequest(
                        groupMember.groupId,
                        groupMember.contactId
                    )
                )
            val connectContactWithGroup = connectContactWithGroups(AppContext(connectToDatabase()),
                    ConnectContactWithGroupsRequest(
                        groupMember.groupId,
                        listOf(groupMember.contactId)
                    )
            )
            val connectGroupWithContact = connectGroupWithContacts(AppContext(connectToDatabase()),
                    ConnectGroupWithContactsRequest(
                        groupMember.contactId,
                        listOf(groupMember.groupId)
                    )
            )
            call.respond(contactAdded)
            call.respond(connectContactWithGroup)
            call.respond(connectGroupWithContact)
        }
        delete("/{groupId}/{contactId}") {
            val groupId = call.parameters["groupId"]
            val contactId = call.parameters["contactId"]
            val groupMemberDeleted = deleteGroupMember(AppContext(connectToDatabase()),
                    DeleteGroupMemberRequest(
                        UUID.fromString(groupId),
                        UUID.fromString(contactId)
                    )
                )
            call.respond(groupMemberDeleted)
        }
    }
}