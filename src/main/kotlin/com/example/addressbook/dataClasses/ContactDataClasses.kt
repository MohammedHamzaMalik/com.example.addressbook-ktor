package com.commandPattern.addressBook.dataClasses

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.util.UUID

@JsonSerialize
data class Contact(
    val contactId: UUID,
    val firstName: String,
    val lastName: String,
    val emails: MutableMap<String, String>,
    val phoneNumbers: MutableMap<String, String>,
    val addresses: MutableMap<String, String>
)

