package com.addressBook.testFiles

import arrow.core.orNull
import arrow.core.right
import com.addressBook.AppTest
import com.addressBook.entryPoints.addContact
import com.addressBook.entryPoints.deleteContact
import com.addressBook.entryPoints.editContact
import com.addressBook.entryPoints.fetchContact
import com.addressBook.getAddContactRequest
import com.addressBook.getEditContactRequest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class ContactTest: AppTest() {

    @Test
    fun `add contact`() {
        val req = getAddContactRequest()
        val contactResponseEither = addContact(appCtx, req)

        Assertions.assertTrue(contactResponseEither.isRight())
        val contactResponse = contactResponseEither.orNull()

        contactResponse?.let {contact ->
            Assertions.assertEquals("Hamza", contact.firstName)
            Assertions.assertEquals("Malik", contact.lastName)
            Assertions.assertTrue(contact.emails.containsValue("work@gmail.com"))
            Assertions.assertTrue(contact.phoneNumbers.containsValue("+91 123"))
            Assertions.assertTrue(contact.addresses.containsValue("ST"))
        }

        println(contactResponse)
    }

    @Test
    fun `fetch contact`() {
        val req = getAddContactRequest()
        val contactResponseEither = addContact(appCtx, req)

        Assertions.assertTrue(contactResponseEither.isRight())
        val contactResponse = contactResponseEither.orNull()

        contactResponse?.let {contact ->
            val fetchContactResponseEither = fetchContact(appCtx, contact.contactId)
            val fetchContactResponse = fetchContactResponseEither.orNull()

            fetchContactResponse?.let { fetchContact ->
                Assertions.assertEquals("Hamza", fetchContact.firstName)
                Assertions.assertEquals("Malik", fetchContact.lastName)
                Assertions.assertTrue(contact.emails.containsValue("work@gmail.com"))
                Assertions.assertTrue(contact.phoneNumbers.containsValue("+91 123"))
                Assertions.assertTrue(contact.addresses.containsValue("ST"))
            }
        }
    }

    @Test
    fun `edit contact`() {

        val req = getAddContactRequest()
        val contactResponseEither = addContact(appCtx, req)

        Assertions.assertTrue(contactResponseEither.isRight())
        val contactResponse = contactResponseEither.orNull()

        contactResponse?.let { contact ->
            val editedContactRequest = getEditContactRequest()
            val editContactResponseEither = editContact(appCtx, contact.contactId, editedContactRequest)

            Assertions.assertTrue(editContactResponseEither.isRight())
            val editContactResponse = editContactResponseEither.orNull()

            editContactResponse?.let { editContact ->
                Assertions.assertEquals("Contact is edited.",
                                        editContact)
            }
        }
    }

    @Test
    fun `delete contact`() {
        val req = getAddContactRequest()
        val contactResponseEither = addContact(appCtx, req)

        Assertions.assertTrue(contactResponseEither.isRight())
        val contactResponse = contactResponseEither.orNull()

        contactResponse?.let { contact ->
            val deleteContactResponse = deleteContact(appCtx, contact.contactId).orNull()!!
            println(deleteContactResponse)

            Assertions.assertTrue(deleteContactResponse.toBoolean())
        }
    }
}