package com.commandPattern.addressBook.dataClasses

import java.util.UUID

data class Contact(
    val contactId: UUID,
    val firstName: String,
    val lastName: String,
    val emails: MutableMap<String, String>,
    val phoneNumbers: MutableMap<String, String>,
    val addresses: MutableMap<String, String>
)

