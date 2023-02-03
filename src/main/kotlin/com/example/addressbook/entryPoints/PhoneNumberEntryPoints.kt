package com.example.addressbook.entryPoints

import AppContext
import CommandContext
import arrow.core.Either
import com.addressBook.handlers.Handler.addPhoneNumberInContactHandler
import com.example.addressbook.commands.AddPhoneNumberCommand
import com.example.addressbook.requests.AddPhoneNumberRequest

fun addPhoneNumberInContact(
    ac: AppContext,
    req: AddPhoneNumberRequest
): Either<Exception, String> {
    val cmdCtx = CommandContext(ac.db)
    val cmd = AddPhoneNumberCommand(cmdCtx, req)
    return addPhoneNumberInContactHandler(cmd)
}

