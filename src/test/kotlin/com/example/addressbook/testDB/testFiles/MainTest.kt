package com.addressBook.testFiles

import com.addressBook.*
import com.addressBook.entryPoints.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class MainTest: AppTest() {

//    Test functions for Contact
        @Test
        @Order(1)
        fun `add contact`() {
            val req = getAddContactRequest()
            val contactResponseEither = addContact(appCtx, req)

            Assertions.assertTrue(contactResponseEither.isRight())
            val contactResponse = contactResponseEither.orNull()

            contactResponse?.let {contact ->
                Assertions.assertEquals("Hamza", contact.firstName)
                Assertions.assertEquals("Malik", contact.lastName)
                Assertions.assertTrue(contact.emails.containsValue("work@gmail.com"))
                Assertions.assertTrue(contact.phoneNumbers.containsValue("+91 123"))
                Assertions.assertTrue(contact.addresses.containsValue("ST"))
            }

            println(contactResponse)
        }

        @Test
        @Order(2)
        fun `fetch contact`() {
            val req = getAddContactRequest()
            val contactResponseEither = addContact(appCtx, req)

            Assertions.assertTrue(contactResponseEither.isRight())
            val contactResponse = contactResponseEither.orNull()

            contactResponse?.let {contact ->
                val fetchContactResponseEither = fetchContact(appCtx, contact.contactId)
                val fetchContactResponse = fetchContactResponseEither.orNull()

                fetchContactResponse?.let { fetchContact ->
                    Assertions.assertEquals("Hamza", fetchContact.firstName)
                    Assertions.assertEquals("Malik", fetchContact.lastName)
                    Assertions.assertTrue(contact.emails.containsValue("work@gmail.com"))
                    Assertions.assertTrue(contact.phoneNumbers.containsValue("+91 123"))
                    Assertions.assertTrue(contact.addresses.containsValue("ST"))
                }
            }
        }

        @Test
        @Order(3)
        fun `edit contact`() {

            val req = getAddContactRequest()
            val contactResponseEither = addContact(appCtx, req)

            Assertions.assertTrue(contactResponseEither.isRight())
            val contactResponse = contactResponseEither.orNull()

            contactResponse?.let { contact ->
                val editedContactRequest = getEditContactRequest()
                val editContactResponseEither = editContact(appCtx, contact.contactId, editedContactRequest)

                Assertions.assertTrue(editContactResponseEither.isRight())
                val editContactResponse = editContactResponseEither.orNull()

                editContactResponse?.let { editContact ->
                    Assertions.assertEquals("Contact is edited.",
                        editContact)
                }
            }
        }

        @Test
        @Order(4)
        fun `delete contact`() {
            val req = getAddContactRequest()
            val contactResponseEither = addContact(appCtx, req)

            Assertions.assertTrue(contactResponseEither.isRight())
            val contactResponse = contactResponseEither.orNull()

            contactResponse?.let { contact ->
                val deleteContactResponse = deleteContact(appCtx, contact.contactId).orNull()!!
                println(deleteContactResponse)

                Assertions.assertTrue(deleteContactResponse.toBoolean())
            }
        }


//    Test functions for Group
        @Test
        @Order(5)
        fun `add group`() {
            val req = getAddGroupRequest()
            val groupResponseEither = addGroup(appCtx, req)

            Assertions.assertTrue(groupResponseEither.isRight())
            val groupResponse = groupResponseEither.orNull()

            groupResponse?.let {
                Assertions.assertEquals("Vayana", it.groupName)
            }
        }

        @Test
        @Order(6)
        fun `fetch group`() {
            val req = getAddGroupRequest()
            val groupResponseEither = addGroup(appCtx, req)

            Assertions.assertTrue(groupResponseEither.isRight())
            val groupResponse = groupResponseEither.orNull()

            groupResponse?.let {
                val fetchGroupResponseEither = fetchGroup(appCtx, groupResponse.groupId)

                Assertions.assertTrue(fetchGroupResponseEither.isRight())
                val fetchGroupResponse = fetchGroupResponseEither.orNull()

                fetchGroupResponse?.let {
                    Assertions.assertEquals("Vayana", it.groupName)
                }
            }
        }

        @Test
        @Order(7)
        fun `edit group`() {
            val req = getAddGroupRequest()
            val groupResponseEither = addGroup(appCtx, req)

            Assertions.assertTrue(groupResponseEither.isRight())
            val groupResponse = groupResponseEither.orNull()

            groupResponse?.let {
                val editGroupRequest = getEditGroupRequest()
                val editGroupResponseEither = editGroup(appCtx, groupResponse.groupId, editGroupRequest)

                Assertions.assertTrue(editGroupResponseEither.isRight())
                val editGroupResponse = editGroupResponseEither.orNull()

                editGroupResponse?.let {
                    Assertions.assertEquals("Vayana Interns", editGroupRequest.groupName)
                }
            }
        }

        @Test
        @Order(8)
        fun `delete group`() {
            val req = getAddGroupRequest()
            val groupResponseEither = addGroup(appCtx, req)

            Assertions.assertTrue(groupResponseEither.isRight())
            val groupResponse = groupResponseEither.orNull()

            groupResponse?.let {
                val deleteGroupResponseEither = deleteGroup(appCtx, groupResponse.groupId)

                Assertions.assertTrue(deleteGroupResponseEither.isRight())
                val deleteGroupResponse = deleteGroupResponseEither.orNull()

                deleteGroupResponse?.let {
                    Assertions.assertTrue(it.toBoolean())
                }
            }
        }


//    Test functions for Group Member
        @Test
        @Order(9)
        fun `add group member`() {
            val groupReq = getAddGroupRequest()
            val groupResponseEither = addGroup(appCtx, groupReq)

            Assertions.assertTrue(groupResponseEither.isRight())
            val groupResponse = groupResponseEither.orNull()

            groupResponse?.let {groupIt ->
                val contactReq = getAddContactRequest()
                val contactResponseEither = addContact(appCtx, contactReq)

                Assertions.assertTrue(contactResponseEither.isRight())
                val contactResponse = contactResponseEither.orNull()

                contactResponse?.let {contactIt ->
                    val req = getAddGroupMemberRequest(groupIt.groupId, contactIt.contactId)
                    val groupMemberResponseEither = addGroupMember(appCtx, req)

                    Assertions.assertTrue(groupMemberResponseEither.isRight())
                    val groupMemberResponse = groupMemberResponseEither.orNull()

                    groupMemberResponse?.let {groupMemberIt ->
                        Assertions.assertEquals("Member with contact id ${contactIt.contactId} " +
                                "is added to group with group id ${groupIt.groupId}.",
                            groupMemberIt)
                    }
                }
            }
        }

        @Test
        @Order(10)
        fun `delete group member`() {
            val groupReq = getAddGroupRequest()
            val groupResponseEither = addGroup(appCtx, groupReq)

            Assertions.assertTrue(groupResponseEither.isRight())
            val groupResponse = groupResponseEither.orNull()

            groupResponse?.let {groupIt ->
                val contactReq = getAddContactRequest()
                val contactResponseEither = addContact(appCtx, contactReq)

                Assertions.assertTrue(contactResponseEither.isRight())
                val contactResponse = contactResponseEither.orNull()

                contactResponse?.let {contactIt ->
                    val groupMemberReq = getAddGroupMemberRequest(groupIt.groupId, contactIt.contactId)
                    val groupMemberResponseEither = addGroupMember(appCtx, groupMemberReq)

                    Assertions.assertTrue(groupMemberResponseEither.isRight())
                    val groupMemberResponse = groupMemberResponseEither.orNull()

                    groupMemberResponse?.let {groupMemberIt ->
                        Assertions.assertEquals("Member with contact id ${contactIt.contactId} " +
                                "is added to group with group id ${groupIt.groupId}.",
                            groupMemberIt)

                        val req = getDeleteGroupMemberRequest(groupIt.groupId, contactIt.contactId)
                        val deleteGroupMemberResponseEither = deleteGroupMember(appCtx, req)

                        Assertions.assertTrue(deleteGroupMemberResponseEither.isRight())
                        val deleteGroupMemberResponse = deleteGroupMemberResponseEither.orNull()

                        deleteGroupMemberResponse?.let {deleteGroupMemberIt ->
                            Assertions.assertEquals("Member with contact id ${contactIt.contactId} " +
                                    "is deleted from group with group id ${groupIt.groupId}.",
                                deleteGroupMemberIt)
                        }
                    }
                }
            }
        }

        @Test
        @Order(11)
        fun `fetch group member`() {
            val groupReq = getAddGroupRequest()
            val groupResponseEither = addGroup(appCtx, groupReq)

            Assertions.assertTrue(groupResponseEither.isRight())
            val groupResponse = groupResponseEither.orNull()

            groupResponse?.let {groupIt ->
                val contactReq = getAddContactRequest()
                val contactResponseEither = addContact(appCtx, contactReq)

                Assertions.assertTrue(contactResponseEither.isRight())
                val contactResponse = contactResponseEither.orNull()

                contactResponse?.let {contactIt ->
                    val groupMemberReq = getAddGroupMemberRequest(groupIt.groupId, contactIt.contactId)
                    val groupMemberResponseEither = addGroupMember(appCtx, groupMemberReq)

                    Assertions.assertTrue(groupMemberResponseEither.isRight())
                    val groupMemberResponse = groupMemberResponseEither.orNull()

                    groupMemberResponse?.let {groupMemberIt ->
                        Assertions.assertEquals("Member with contact id ${contactIt.contactId} " +
                                "is added to group with group id ${groupIt.groupId}.",
                            groupMemberIt)

                        val req = getFetchGroupMemberRequest(groupIt.groupId, contactIt.contactId)
                        val fetchGroupMemberResponseEither = fetchGroupMember(appCtx, req)

                        Assertions.assertTrue(fetchGroupMemberResponseEither.isRight())
                        val fetchGroupMemberResponse = fetchGroupMemberResponseEither.orNull()

                        fetchGroupMemberResponse?.let {fetchGroupMemberIt ->
                            Assertions.assertEquals("Hamza", fetchGroupMemberIt.firstName)
                            Assertions.assertEquals("Khan", fetchGroupMemberIt.lastName)
                        }
                    }
                }
            }
        }

        @Test
        @Order(12)
        fun `connect contact and groups`() {
            val contactReq = getAddContactRequest()
            val contactResponseEither = addContact(appCtx, contactReq)

            Assertions.assertTrue(contactResponseEither.isRight())
            val contactResponse = contactResponseEither.orNull()

            contactResponse?.let { contactIt ->
                val groupReq = getAddGroupRequest()
                val groupResponseEither = addGroup(appCtx, groupReq)

                Assertions.assertTrue(groupResponseEither.isRight())
                val groupResponse = groupResponseEither.orNull()

                val groupReq2 = getAddGroupRequest2()
                val groupResponseEither2 = addGroup(appCtx, groupReq2)

                Assertions.assertTrue(groupResponseEither2.isRight())
                val groupResponse2 = groupResponseEither2.orNull()

                groupResponse?.let { groupIt ->
                    groupResponse2?.let { groupIt2 ->
                        val req = getConnectContactWithGroupsRequest(
                            contactIt.contactId,
                            listOf(groupIt.groupId, groupIt2.groupId)
                        )
                        val connectContactWithGroupsResponseEither = connectContactWithGroups(appCtx, req)

                        Assertions.assertTrue(connectContactWithGroupsResponseEither.isRight())
                        val connectContactWithGroupsResponse = connectContactWithGroupsResponseEither.orNull()

                        connectContactWithGroupsResponse?.let { connectContactWithGroupsIt ->
                            Assertions.assertEquals(
                                "Contact with contact id ${contactIt.contactId} " +
                                        "is added to groups with group ids ${
                                            listOf(
                                                groupIt.groupId,
                                                groupIt2.groupId
                                            )
                                        }.",
                                connectContactWithGroupsIt
                            )
                        }
                    }
                }
            }
        }

        @Test
        @Order(13)
        fun `connect group and contacts`() {
            val groupReq = getAddGroupRequest()
            val groupResponseEither = addGroup(appCtx, groupReq)

            Assertions.assertTrue(groupResponseEither.isRight())
            val groupResponse = groupResponseEither.orNull()

            groupResponse?.let { groupIt ->
                val contactReq = getAddContactRequest()
                val contactResponseEither = addContact(appCtx, contactReq)

                Assertions.assertTrue(contactResponseEither.isRight())
                val contactResponse = contactResponseEither.orNull()

                val contactReq2 = getAddContactRequest()
                val contactResponseEither2 = addContact(appCtx, contactReq2)

                Assertions.assertTrue(contactResponseEither2.isRight())
                val contactResponse2 = contactResponseEither2.orNull()

                contactResponse?.let { contactIt ->
                    contactResponse2?.let { contactIt2 ->
                        val req = getConnectGroupWithContactsRequest(
                            groupIt.groupId,
                            listOf(contactIt.contactId, contactIt2.contactId)
                        )
                        val connectGroupWithContactsResponseEither = connectGroupWithContacts(appCtx, req)

                        Assertions.assertTrue(connectGroupWithContactsResponseEither.isRight())
                        val connectGroupWithContactsResponse = connectGroupWithContactsResponseEither.orNull()

                        connectGroupWithContactsResponse?.let { connectGroupWithContactsIt ->
                            Assertions.assertEquals(
                                "Group with group id ${groupIt.groupId} " +
                                        "is added to contacts with contact ids ${
                                            listOf(
                                                contactIt.contactId,
                                                contactIt2.contactId
                                            )
                                        }.",
                                connectGroupWithContactsIt
                            )
                        }
                    }
                }
            }
        }
}