//package com.example.addressbook.apis.routes
//
//import AppContext
//import com.addressBook.dbSetup.connectToDatabase
//import com.addressBook.entryPoints.addContact
//import com.addressBook.tables.Contacts
//import com.commandPattern.addressBook.dataClasses.Contact
//import com.commandPattern.addressBook.requests.AddContactRequest
//import io.ktor.server.application.*
//import io.ktor.server.request.*
//import io.ktor.server.response.*
//import io.ktor.server.routing.*
//import org.jetbrains.exposed.sql.exists
//import org.jetbrains.exposed.sql.insert
//import org.jetbrains.exposed.sql.select
//import org.jetbrains.exposed.sql.selectAll
//
//fun Route.contactRoutes() {
//    route("/contacts") {
//        get("/all") {
//            val contacts = Contacts.selectAll()
//            call.respond(contacts)
//        }
//        post {
//            val contact = call.receive<Contact>()
//            if (Contacts.select { Contacts.contactId eq contact.contactId }.empty()) {
//                addContact(AppContext(connectToDatabase()),
//                    AddContactRequest(
//                        contact.firstName,
//                        contact.lastName,
//                        ,
//                call.respondText("Contact added")
//            } else {
//                call.respondText("Contact already exists")
//            }
//        }
//    }
//}