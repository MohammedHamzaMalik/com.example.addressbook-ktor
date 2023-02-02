package com.addressBook.commands


import CommandContext
import arrow.core.Either
import com.addressBook.dataClasses.Group
import com.addressBook.repo.*
import com.addressBook.requests.AddGroupRequest
import com.addressBook.requests.EditGroupRequest
import java.util.*

fun AddGroupRequest.toGroup() = Group(
    groupId = UUID.randomUUID(),
    groupName = this@toGroup.groupName
)

fun EditGroupRequest.toGroup() = Group(
    groupId = this@toGroup.groupId,
    groupName = this@toGroup.groupName
)

class AddGroupCommand(
    val cmdCtx: CommandContext,
    private val request: AddGroupRequest
): Command {
    override fun execute(): Either<Exception, Group> = GroupRepo.addGroupInTable(request.toGroup())
}

class DeleteGroupCommand(
    val cmdCtx: CommandContext,
    private val groupId: UUID
): Command {
    override fun execute(): Either<Exception, String> {
        return GroupRepo.deleteGroupInTable(groupId)
    }

}

class EditGroupCommand(
    val cmdCtx: CommandContext,
    private val groupId: UUID,
    private val request: EditGroupRequest
): Command {
    override fun execute(): Either<Exception, String> {
        return GroupRepo.editGroupInTable(groupId, request.toGroup())
    }
}

class FetchGroupCommand(
    val cmdCtx: CommandContext,
    private val groupId: UUID
): Command {
    override fun execute(): Either<Exception, Group> {
        return GroupRepo.fetchGroupInTable(groupId)
    }
}

class FetchAllGroupsCommand(
    val cmdCtx: CommandContext
): Command {
    override fun execute(): Either<Exception, List<Group>> {
        return GroupRepo.fetchAllGroupsInTable()
    }
}