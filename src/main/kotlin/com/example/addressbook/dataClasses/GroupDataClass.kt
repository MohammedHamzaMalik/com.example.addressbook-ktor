package com.addressBook.dataClasses

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.util.*

@JsonSerialize
data class Group(
    val groupId: UUID,
    val groupName: String
)