package com.example.addressbook.requests

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.util.*

@JsonSerialize
data class AddPhoneNumberRequest(
    val contactId: UUID,
    val phoneNumber: String,
    val phoneNumberType: String
)

@JsonSerialize
data class DeletePhoneNumberRequest(
    val contactId: UUID,
    val phoneNumberType: String
)

@JsonSerialize
data class UpdatePhoneNumberRequest(
    val contactId: UUID,
    val phoneNumber: String,
    val phoneNumberType: String
)

@JsonSerialize
data class FetchPhoneNumberRequest(
    val contactId: UUID,
    val phoneNumberType: String
)

@JsonSerialize
data class FetchAllPhoneNumbersRequest(
    val contactId: UUID
)
