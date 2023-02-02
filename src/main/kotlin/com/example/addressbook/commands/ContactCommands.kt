package com.addressBook.commands


import CommandContext
import arrow.core.Either
import com.addressBook.repo.*
import com.commandPattern.addressBook.dataClasses.Contact
import com.commandPattern.addressBook.requests.*
import java.util.UUID

fun AddContactRequest.toContact() = Contact(
    contactId = UUID.randomUUID(),
    firstName = this@toContact.firstName,
    lastName = this@toContact.lastName,
    emails = this@toContact.emails,
    phoneNumbers = this@toContact.phoneNumbers,
    addresses = this@toContact.addresses
)

fun EditContactRequest.toContact() = Contact(
    contactId = this@toContact.contactId,
    firstName = this@toContact.firstName,
    lastName = this@toContact.lastName,
    emails = this@toContact.emails,
    phoneNumbers = this@toContact.phoneNumbers,
    addresses = this@toContact.addresses
)

class AddContactCommand(
    val cmdCtx: CommandContext,
    private val requests: AddContactRequest
): Command {
    override fun execute(): Either<Exception, Contact> = ContactRepo.addContactInTable(requests.toContact())
}

class DeleteContactCommand(
    val cmdCtx: CommandContext,
    private val contactId: UUID
): Command {
    override fun execute(): Either<Exception, String> {
        return ContactRepo.deleteContactInTable(contactId)
    }
}

class EditContactCommand(
    val cmdCtx: CommandContext,
    private val contactId: UUID,
    private val requests: EditContactRequest
): Command {
    override fun execute(): Either<Exception, String> {
        return ContactRepo.editContactInTable(contactId, requests.toContact())
    }
}

class FetchContactCommand(
    val cmdCtx: CommandContext,
    private val contactId: UUID
): Command {
    override fun execute(): Either<Exception, Contact> {
        return ContactRepo.fetchContactInTable(contactId)
    }
}