package com.addressBook.tables

import org.jetbrains.exposed.sql.Table

object Groups: Table("groups") {
    val groupId = uuid("group_id").autoGenerate()
    val groupName = varchar("group_name", length = 100)

    override val primaryKey = PrimaryKey(groupId, name = "PK_Group_ID")
}

object AllGroupMembers: Table("group_members") {
    val groupMemberId = uuid("group_member_id").autoGenerate()
    val groupId = (uuid("group_id") references Groups.groupId).index()
    val contactId = (uuid("contact_id") references Contacts.contactId).index()

    override val primaryKey = PrimaryKey(groupMemberId, name = "PK_GroupMember_ID")
}