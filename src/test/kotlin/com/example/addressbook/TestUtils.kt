package com.addressBook

import com.addressBook.requests.*
import com.commandPattern.addressBook.requests.AddContactRequest
import com.commandPattern.addressBook.requests.EditContactRequest
import java.util.*

fun getAddContactRequest(): AddContactRequest {
    val contact = AddContactRequest(
        "Hamza","Malik",
        mutableMapOf("work" to "work@gmail.com","home" to "home@gmail.com"),
        mutableMapOf("work" to "+91 123","home" to "+91 234"),
        mutableMapOf("HOME" to "ST","WORK" to "BRC")
    )
    return contact
}

fun getAddContactRequest2(): AddContactRequest {
    val contact = AddContactRequest(
        "Vishesh","Modi",
        mutableMapOf("work" to "work@gmail.com","home" to "home@gmail.com"),
        mutableMapOf("work" to "+91 123","home" to "+91 234"),
        mutableMapOf("HOME" to "ST","WORK" to "BRC")
    )
    return contact
}

fun getEditContactRequest(): EditContactRequest {
    return EditContactRequest(
        UUID.randomUUID(),
        "Mohammed Hamza","Malik",
        mutableMapOf("work" to "hamza@gmail.com","home" to "home@gmail.com"),
        mutableMapOf("work" to "+91 123","home" to "+91 234"),
        mutableMapOf("HOME" to "ST","WORK" to "BRC")
    )
}

fun getAddGroupRequest(): AddGroupRequest {
    return AddGroupRequest("Vayana")
}

fun getAddGroupRequest2(): AddGroupRequest {
    return AddGroupRequest("PDPU")
}

fun getEditGroupRequest(): EditGroupRequest {
    return EditGroupRequest(UUID.randomUUID(),"Vayana Interns")
}

fun getAddGroupMemberRequest(groupId: UUID, contactId: UUID): AddGroupMemberRequest {
    return AddGroupMemberRequest(groupId, contactId)
}

fun getDeleteGroupMemberRequest(groupId: UUID, contactId: UUID): DeleteGroupMemberRequest {
    return DeleteGroupMemberRequest(groupId, contactId)
}

fun getFetchGroupMemberRequest(groupId: UUID, contactId: UUID): FetchGroupMemberRequest {
    return FetchGroupMemberRequest(groupId, contactId)
}

fun getConnectContactWithGroupsRequest(contactId: UUID, groupIds: List<UUID>): ConnectContactwithGroupsRequest {
    return ConnectContactwithGroupsRequest(contactId, groupIds)
}

fun getConnectGroupWithContactsRequest(groupId: UUID, contactIds: List<UUID>): ConnectGroupwtihContactsRequest {
    return ConnectGroupwtihContactsRequest(groupId, contactIds)
}