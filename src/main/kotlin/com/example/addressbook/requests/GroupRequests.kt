package com.addressBook.requests

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.util.*

@JsonSerialize
data class AddGroupRequest(
    val groupName: String
)

data class EditGroupRequest(
    val groupId: UUID,
    val groupName: String
)