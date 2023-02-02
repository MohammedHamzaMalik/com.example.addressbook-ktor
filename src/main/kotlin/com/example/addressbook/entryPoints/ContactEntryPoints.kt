package com.addressBook.entryPoints


import AppContext
import CommandContext
import arrow.core.Either
import com.addressBook.commands.AddContactCommand
import com.addressBook.commands.DeleteContactCommand
import com.addressBook.commands.EditContactCommand
import com.addressBook.commands.FetchContactCommand
import com.addressBook.handlers.Handler.addContactHandler
import com.addressBook.handlers.Handler.deleteContactHandler
import com.addressBook.handlers.Handler.editContactHandler
import com.addressBook.handlers.Handler.fetchContactHandler
import com.commandPattern.addressBook.dataClasses.Contact
import com.commandPattern.addressBook.requests.*
import java.util.UUID

fun addContact(
    ac: AppContext,
    req: AddContactRequest
): Either<Exception, Contact> {
    val cmdCtx = CommandContext(ac.db)
    val cmd = AddContactCommand(cmdCtx, req)
    return addContactHandler(cmd)
}

fun deleteContact(
    ac: AppContext,
    contactId: UUID
): Either<Exception, String> {
    val cmdCtx = CommandContext(ac.db)
    val cmd = DeleteContactCommand(cmdCtx, contactId)
    return deleteContactHandler(cmd)
}

fun editContact(
    ac: AppContext,
    contactId: UUID,
    req: EditContactRequest
): Either<Exception, String> {
    val cmdCtx = CommandContext(ac.db)
    val cmd = EditContactCommand(cmdCtx, contactId, req)
    return editContactHandler(cmd)
}

fun fetchContact(
    ac: AppContext,
    contactId: UUID
): Either<Exception, Contact> {
    val cmdCtx = CommandContext(ac.db)
    val cmd = FetchContactCommand(cmdCtx, contactId)
    return fetchContactHandler(cmd)
}