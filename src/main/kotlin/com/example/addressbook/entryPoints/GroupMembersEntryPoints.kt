package com.addressBook.entryPoints

import AppContext
import CommandContext
import arrow.core.Either
import com.addressBook.commands.*
import com.addressBook.handlers.Handler.addGroupMemberHandler
import com.addressBook.handlers.Handler.connectContactWithGroupsHandler
import com.addressBook.handlers.Handler.connectGroupWithContactsHandler
import com.addressBook.handlers.Handler.deleteGroupMemberHandler
import com.addressBook.handlers.Handler.displayGroupMembersByGroupIdHandler
import com.addressBook.handlers.Handler.displayGroupMembersHandler
import com.addressBook.handlers.Handler.fetchGroupMemberHandler
import com.addressBook.requests.*
import com.commandPattern.addressBook.dataClasses.Contact
import java.util.*

fun addGroupMember(
    ac: AppContext,
    req: AddGroupMemberRequest
): Either<Exception, String> {
    val cmdCtx = CommandContext(ac.db)
    val cmd = AddGroupMemberCommand(cmdCtx, req)
    return addGroupMemberHandler(cmd)
}


fun deleteGroupMember(
    ac: AppContext,
    req: DeleteGroupMemberRequest
): Either<Exception, String> {
    val cmdCtx = CommandContext(ac.db)
    val cmd = DeleteGroupMemberCommand(cmdCtx, req)
    return deleteGroupMemberHandler(cmd)
}

fun fetchGroupMember(
    ac: AppContext,
    req: FetchGroupMemberRequest
): Either<Exception, Contact> {
    val cmdCtx = CommandContext(ac.db)
    val cmd = FetchGroupMemberCommand(cmdCtx, req)
    return fetchGroupMemberHandler(cmd)
}
fun connectContactWithGroups(
    ac: AppContext,
    req: ConnectContactWithGroupsRequest
): Either<Exception, String> {
    val cmdCtx = CommandContext(ac.db)
    val cmd = ConnectContactWithGroupsCommand(cmdCtx, req)
    return connectContactWithGroupsHandler(cmd)
}

fun connectGroupWithContacts(
    ac: AppContext,
    req: ConnectGroupWithContactsRequest
): Either<Exception, String> {
    val cmdCtx = CommandContext(ac.db)
    val cmd = ConnectGroupWithContactsCommand(cmdCtx, req)
    return connectGroupWithContactsHandler(cmd)
}

fun displayAllGroupMembersOfAllGroups(
    ac: AppContext
): Either<Exception, List<Contact>> {
    val cmdCtx = CommandContext(ac.db)
    val cmd = DisplayGroupMembersCommand(cmdCtx)
    return displayGroupMembersHandler(cmd)
}

fun displayContactsByGroupId(
    ac: AppContext,
    groupId: UUID
): Either<Exception, List<Contact>> {
    val cmdCtx = CommandContext(ac.db)
    val cmd = DisplayContactsByGroupIdCommand(cmdCtx, groupId)
    return displayGroupMembersByGroupIdHandler(cmd)
}