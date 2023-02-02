package com.addressBook.testFiles

import com.addressBook.AppTest
import com.addressBook.entryPoints.addGroup
import com.addressBook.entryPoints.deleteGroup
import com.addressBook.entryPoints.editGroup
import com.addressBook.entryPoints.fetchGroup
import com.addressBook.getAddGroupRequest
import com.addressBook.getEditGroupRequest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class GroupTest: AppTest() {

    @Test
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

}
