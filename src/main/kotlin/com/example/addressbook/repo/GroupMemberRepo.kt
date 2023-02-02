package com.commandPattern.addressBook.storages

import arrow.core.Either
import com.addressBook.tables.*
import com.commandPattern.addressBook.dataClasses.Contact
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

object GroupMemberRepo {


//    Main function with logic for GROUP MEMBER
    fun addGroupMemberInTable(groupId: UUID, contactId: UUID): Either<Exception, String> {
        return try {
            transaction {
                AllGroupMembers.insert {
                    it[this.groupId] = groupId
                    it[this.contactId] = contactId
                }
            }
            Either.Right("Member with contact id $contactId is added to group with group id $groupId.")
        } catch (e: Exception) {
            Either.Left(Exception("Error while adding member with contact id $contactId to group with group id $groupId." +
                    " Please check if the group and contact exist."))
        }
    }

    fun deleteGroupMemberInTable(groupId: UUID, contactId: UUID): Either<Exception, String> {
        return try {
            transaction {
                AllGroupMembers.deleteWhere {
                    (AllGroupMembers.groupId eq groupId) and
                            (AllGroupMembers.contactId eq contactId)
                }
            }
            Either.Right("Member with contact id $contactId is deleted from group with group id $groupId.")
        } catch (e: Exception) {
            Either.Left(Exception("Error while deleting member with contact id $contactId from group with group id $groupId." +
                    " Please check if the group and contact exist."))
        }
    }
    fun fetchGroupMemberInTable(groupId: UUID, contactId: UUID): Either<Exception, Contact> {
        return try {
            lateinit var groupMember: Contact
            transaction {
                val groupMemberRow = AllGroupMembers.select {
                    (AllGroupMembers.groupId eq groupId) and (AllGroupMembers.contactId eq contactId)
                }.firstOrNull()
                if (groupMemberRow != null) {
                    val firstName = Contacts.select { Contacts.contactId eq contactId }
                        .firstOrNull()?.get(Contacts.firstName)
                    val lastName = Contacts.select { Contacts.contactId eq contactId }
                        .firstOrNull()?.get(Contacts.lastName)
                    val emails = Emails.select { Emails.contactId eq contactId }.associate {
                        it[Emails.emailType] to it[Emails.email]
                    }
                    val phoneNumbers = PhoneNumbers.select { PhoneNumbers.contactId eq contactId }.associate {
                        it[PhoneNumbers.phoneNumberType] to it[PhoneNumbers.phoneNumber]
                    }
                    val addresses = Addresses.select { Addresses.contactId eq contactId }.associate {
                        it[Addresses.addressType] to it[Addresses.address]
                    }
                    groupMember = Contact(
                        contactId,
                        firstName!!,
                        lastName!!,
                        emails.toMutableMap(),
                        phoneNumbers.toMutableMap(),
                        addresses.toMutableMap()
                    )
                }
            }
            Either.Right(groupMember)
        } catch (e: Exception) {
            Either.Left(Exception("Error while fetching member with contact id $contactId from group with group id $groupId." +
                    " Please check if the group and contact exist."))
        }
    }


//    Main function with logic for GROUP MEMBER connection with GROUP and CONTACT
    fun connectContactWithGroupsInTable(contactId: UUID, groupIds: List<UUID>): Either<Exception, String> {
        return try {
            groupIds.forEach {id ->
                transaction {
                    AllGroupMembers.insert {
                        it[this.contactId] = contactId
                        it[this.groupId] = id
                    }
                }
            }
            Either.Right("Contact with contact id $contactId is added to groups with group ids $groupIds.")
        } catch (e: Exception) {
            Either.Left(Exception("Error while adding contact with contact id $contactId to groups with group ids $groupIds." +
                    " Please check if the group and contact exist."))
        }
    }

    fun connectGroupWithContactsInTable(groupId: UUID, contactIds: List<UUID>): Either<Exception, String> {
        return try {
            contactIds.forEach { id ->
                transaction {
                    AllGroupMembers.insert {
                        it[this.groupId] = groupId
                        it[this.contactId] = id
                    }
                }
            }
            Either.Right("Group with group id $groupId is added to contacts with contact ids $contactIds.")
        } catch (e: Exception) {
            Either.Left(Exception("Error while adding group with group id $groupId to contacts with contact ids $contactIds." +
                    " Please check if the group and contact exist."))
        }
    }

    fun displayAllGroupMembersInTable(): Either<Exception, List<Contact>> {
        return try {
            val groupMembers = mutableListOf<Contact>()
            transaction {
                AllGroupMembers.selectAll().forEach {
                    val contactId = it[AllGroupMembers.contactId]
                    val firstName = Contacts.select { Contacts.contactId eq contactId }
                        .firstOrNull()?.get(Contacts.firstName)
                    val lastName = Contacts.select { Contacts.contactId eq contactId }
                        .firstOrNull()?.get(Contacts.lastName)
                    val emails = Emails.select { Emails.contactId eq contactId }.associate {
                        it[Emails.emailType] to it[Emails.email]
                    }
                    val phoneNumbers = PhoneNumbers.select { PhoneNumbers.contactId eq contactId }.associate {
                        it[PhoneNumbers.phoneNumberType] to it[PhoneNumbers.phoneNumber]
                    }
                    val addresses = Addresses.select { Addresses.contactId eq contactId }.associate {
                        it[Addresses.addressType] to it[Addresses.address]
                    }
                    groupMembers.add(
                        Contact(
                            contactId,
                            firstName!!,
                            lastName!!,
                            emails.toMutableMap(),
                            phoneNumbers.toMutableMap(),
                            addresses.toMutableMap()
                        )
                    )
                }
            }
            Either.Right(groupMembers)
        } catch (e: Exception) {
            Either.Left(Exception("Error while fetching all group members."))
        }
    }

    fun displayAllContactsOfGroupInTable(groupId: UUID): Either<Exception, List<Contact>> {
        return try {
            val groupMembers = mutableListOf<Contact>()
            transaction {
                AllGroupMembers.select { AllGroupMembers.groupId eq groupId }.forEach {
                    val contactId = it[AllGroupMembers.contactId]
                    val firstName = Contacts.select { Contacts.contactId eq contactId }
                        .firstOrNull()?.get(Contacts.firstName)
                    val lastName = Contacts.select { Contacts.contactId eq contactId }
                        .firstOrNull()?.get(Contacts.lastName)
                    val emails = Emails.select { Emails.contactId eq contactId }.associate {
                        it[Emails.emailType] to it[Emails.email]
                    }
                    val phoneNumbers = PhoneNumbers.select { PhoneNumbers.contactId eq contactId }.associate {
                        it[PhoneNumbers.phoneNumberType] to it[PhoneNumbers.phoneNumber]
                    }
                    val addresses = Addresses.select { Addresses.contactId eq contactId }.associate {
                        it[Addresses.addressType] to it[Addresses.address]
                    }
                    groupMembers.add(
                        Contact(
                            contactId,
                            firstName!!,
                            lastName!!,
                            emails.toMutableMap(),
                            phoneNumbers.toMutableMap(),
                            addresses.toMutableMap()
                        )
                    )
                }
            }
            Either.Right(groupMembers)
        } catch (e: Exception) {
            Either.Left(Exception("Error while fetching all contacts of group with group id $groupId."))
        }
    }
}