package com.addressBook.requests

import java.util.*

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

data class ConnectContactwithGroupsRequest(
    val contactId: UUID,
    val groupIds: List<UUID>
)

data class ConnectGroupwtihContactsRequest(
    val groupId: UUID,
    val contactIds: List<UUID>
)

data class DisplayContactsByGroupIdRequest(
    val groupId: UUID
)