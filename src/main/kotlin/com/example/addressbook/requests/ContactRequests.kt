package com.commandPattern.addressBook.requests

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.util.*

@JsonSerialize
data class AddContactRequest(
    val firstName: String,
    val lastName: String,
    val emails: MutableMap<String, String>,
    val phoneNumbers: MutableMap<String, String>,
    val addresses: MutableMap<String, String>
)

@JsonSerialize
data class EditContactRequest(
    val contactId: UUID,
    val firstName: String,
    val lastName: String,
    val emails: MutableMap<String, String>,
    val phoneNumbers: MutableMap<String, String>,
    val addresses: MutableMap<String, String>
)

