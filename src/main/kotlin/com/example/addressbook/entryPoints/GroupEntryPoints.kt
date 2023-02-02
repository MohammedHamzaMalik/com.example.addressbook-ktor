package com.addressBook.entryPoints


import AppContext
import CommandContext
import arrow.core.Either
import com.addressBook.commands.*
import com.addressBook.dataClasses.Group
import com.addressBook.handlers.Handler.addGroupHandler
import com.addressBook.handlers.Handler.deleteGroupHandler
import com.addressBook.handlers.Handler.editGroupHandler
import com.addressBook.handlers.Handler.fetchAllGroupsHandler
import com.addressBook.handlers.Handler.fetchGroupHandler
import com.addressBook.requests.AddGroupRequest
import com.addressBook.requests.EditGroupRequest
import java.util.*

fun addGroup(
    ac: AppContext,
    req: AddGroupRequest
): Either<Exception, Group> {
    val cmdCtx = CommandContext(ac.db)
    val cmd = AddGroupCommand(cmdCtx, req)
    return addGroupHandler(cmd)
}

fun deleteGroup(
    ac: AppContext,
    groupId: UUID
): Either<Exception, String> {
    val cmdCtx = CommandContext(ac.db)
    val cmd = DeleteGroupCommand(cmdCtx, groupId)
    return deleteGroupHandler(cmd)
}

fun editGroup(
    ac: AppContext,
    groupId: UUID,
    req: EditGroupRequest
): Either<Exception, String> {
    val cmdCtx = CommandContext(ac.db)
    val cmd = EditGroupCommand(cmdCtx, groupId, req)
    return editGroupHandler(cmd)
}

fun fetchGroup(
    ac: AppContext,
    groupId: UUID
): Either<Exception, Group> {
    val cmdCtx = CommandContext(ac.db)
    val cmd = FetchGroupCommand(cmdCtx, groupId)
    return fetchGroupHandler(cmd)
}

fun fetchAllGroups(
    ac: AppContext
): Either<Exception, List<Group>> {
    val cmdCtx = CommandContext(ac.db)
    val cmd = FetchAllGroupsCommand(cmdCtx)
    return fetchAllGroupsHandler(cmd)
}