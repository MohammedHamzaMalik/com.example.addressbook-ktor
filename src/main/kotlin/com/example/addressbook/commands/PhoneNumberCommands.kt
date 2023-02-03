package com.example.addressbook.commands

import CommandContext
import arrow.core.Either
import com.addressBook.commands.Command
import com.example.addressbook.repo.PhoneNumberRepo
import com.example.addressbook.requests.AddPhoneNumberRequest
import java.util.*

class AddPhoneNumberCommand(
    val cmdCtx: CommandContext,
    private val requests: AddPhoneNumberRequest
): Command {
    override fun execute(): Either<Exception, String> = PhoneNumberRepo.addPhoneNumberInContactInTable(
        requests.contactId,
        requests.phoneNumberType,
        requests.phoneNumber
    )
}

class DeletePhoneNumberCommand(
    val cmdCtx: CommandContext,
    private val contactId: UUID,
    private val phoneNumberType: String
): Command {
    override fun execute(): Either<Exception, String> {
        return PhoneNumberRepo.deletePhoneNumberInContact(contactId, phoneNumberType)
    }
}

class UpdatePhoneNumberCommand(
    val cmdCtx: CommandContext,
    private val contactId: UUID,
    private val phoneNumberType: String,
    private val phoneNumber: String
): Command {
    override fun execute(): Either<Exception, String> {
        return PhoneNumberRepo.updatePhoneNumberInContact(contactId, phoneNumberType, phoneNumber)
    }
}

class FetchPhoneNumberCommand(
    val cmdCtx: CommandContext,
    private val contactId: UUID,
    private val phoneNumberType: String
): Command {
    override fun execute(): Either<Exception, String> {
        return PhoneNumberRepo.fetchPhoneNumberInContact(contactId, phoneNumberType)
    }
}

class FetchAllPhoneNumbersCommand(
    val cmdCtx: CommandContext,
    private val contactId: UUID
): Command {
    override fun execute(): Either<Exception, List<String>> {
        return PhoneNumberRepo.fetchAllPhoneNumbersInContact(contactId)
    }
}