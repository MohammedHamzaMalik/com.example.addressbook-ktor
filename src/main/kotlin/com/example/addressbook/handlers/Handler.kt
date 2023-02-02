package com.addressBook.handlers

import arrow.core.Either
import com.addressBook.commands.*
import com.addressBook.dataClasses.Group
import com.commandPattern.addressBook.dataClasses.*

object Handler {

//    Handler functions for CONTACTS
    fun addContactHandler(cmd: AddContactCommand): Either<Exception, Contact> {
        return cmd.execute()
    }
    fun deleteContactHandler(cmd: DeleteContactCommand): Either<Exception, String> {
        return cmd.execute()
    }
    fun editContactHandler(cmd: EditContactCommand): Either<Exception, String> {
        return cmd.execute()
    }
    fun fetchContactHandler(cmd: FetchContactCommand): Either<Exception, Contact> {
        return cmd.execute()
    }


//    Handler functions for GROUPS
    fun addGroupHandler(cmd: AddGroupCommand): Either<Exception, Group> {
        return cmd.execute()
    }
    fun deleteGroupHandler(cmd: DeleteGroupCommand): Either<Exception, String> {
        return cmd.execute()
    }
    fun editGroupHandler(cmd: EditGroupCommand): Either<Exception, String> {
        return cmd.execute()
    }
    fun fetchGroupHandler(cmd: FetchGroupCommand): Either<Exception, Group> {
        return cmd.execute()
    }
    fun fetchAllGroupsHandler(cmd: FetchAllGroupsCommand): Either<Exception, List<Group>> {
        return cmd.execute()
    }


//    Handler functions for GROUP MEMBERS
    fun addGroupMemberHandler(cmd: AddGroupMemberCommand): Either<Exception, String> {
        return cmd.execute()
    }
    fun deleteGroupMemberHandler(cmd: DeleteGroupMemberCommand): Either<Exception, String> {
        return cmd.execute()
    }
    fun fetchGroupMemberHandler(cmd: FetchGroupMemberCommand): Either<Exception, Contact> {
        return cmd.execute()
    }
    fun connectContactWithGroupsHandler(cmd: ConnectContactWithGroupsCommand): Either<Exception, String> {
        return cmd.execute()
    }
    fun connectGroupWithContactsHandler(cmd: ConnectGroupWithContactsCommand): Either<Exception, String> {
        return cmd.execute()
    }

    fun displayGroupMembersHandler(cmd: DisplayGroupMembersCommand): Either<Exception, List<Contact>> {
        return cmd.execute()
    }

    fun displayGroupMembersByGroupIdHandler(cmd: DisplayContactsByGroupIdCommand): Either<Exception, List<Contact>> {
        return cmd.execute()
    }
}