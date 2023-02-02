package com.addressBook.testFiles

import com.addressBook.*
import com.addressBook.entryPoints.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GroupMemberTest: AppTest() {

    @Test
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