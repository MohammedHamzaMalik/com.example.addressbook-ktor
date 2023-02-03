package com.addressBook.requests

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.util.*

@JsonSerialize
data class AddGroupMemberRequest(
    val groupId: UUID,
    val contactId: UUID
)

data class DeleteGroupMemberRequest(
    val groupId: UUID,
    val contactId: UUID
)

data class FetchGroupMemberRequest(
    val groupId: UUID,
    val contactId: UUID
)

data class ConnectContactWithGroupsRequest(
    val contactId: UUID,
    val groupIds: List<UUID>
)

data class ConnectGroupWithContactsRequest(
    val groupId: UUID,
    val contactIds: List<UUID>
)

data class DisplayContactsByGroupIdRequest(
    val groupId: UUID
)