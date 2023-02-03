package com.example.addressbook.apis.routes

import AppContext
import com.addressBook.dbSetup.connectToDatabase
import com.addressBook.dbSetup.resetDatabase
import com.addressBook.entryPoints.*
import com.addressBook.tables.Contacts
import com.commandPattern.addressBook.dataClasses.Contact
import com.commandPattern.addressBook.requests.AddContactRequest
import com.commandPattern.addressBook.requests.EditContactRequest
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.exists
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import java.util.*

fun Route.contactRoutes() {
    val dbCon = connectToDatabase()
    route("/contacts") {
        get() {
            val fetchedContacts = fetchAllContacts(AppContext(dbCon))
            call.respond(fetchedContacts)
        }
        get("/{contactId}") {
            val contactId = call.parameters["contactId"]
            val contactFetched = fetchContact(AppContext(dbCon), UUID.fromString(contactId))
            call.respond(contactFetched)
        }
        post {
            val contact = call.receive<AddContactRequest>()
            val contactAdded = addContact(AppContext(connectToDatabase()),
                    AddContactRequest(
                        contact.firstName,
                        contact.lastName,
                        contact.emails,
                        contact.phoneNumbers,
                        contact.addresses
                    )
                )
            call.respond(contactAdded)
        }
        put("/{contactId}"){
            val contactId = call.parameters["contactId"]
            val contact = call.receive<EditContactRequest>()
            val contactEdited = editContact(AppContext(connectToDatabase()),
                    UUID.fromString(contactId),
                    EditContactRequest(
                        UUID.fromString(contactId),
                        contact.firstName,
                        contact.lastName,
                        contact.emails,
                        contact.phoneNumbers,
                        contact.addresses
                    )
                )
            call.respond(contactEdited)
        }
        delete("/{contactId}") {
            val contactId = call.parameters["contactId"]
            val contactDeleted = deleteContact(AppContext(connectToDatabase()), UUID.fromString(contactId))
            call.respond(contactDeleted)
        }
    }
}