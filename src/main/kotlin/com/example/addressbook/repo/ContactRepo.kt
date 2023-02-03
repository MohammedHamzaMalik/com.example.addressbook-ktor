package com.addressBook.repo

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.addressBook.tables.Addresses
import com.addressBook.tables.Contacts
import com.addressBook.tables.Emails
import com.addressBook.tables.PhoneNumbers
import com.commandPattern.addressBook.dataClasses.Contact
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*


//    Main functions with logic for CONTACT table

object ContactRepo {
    fun addContactInTable(contact: Contact): Either<Exception, Contact> {
        return try {
            transaction {
                Contacts.insert {
                    it[this.contactId] = contact.contactId
                    it[this.firstName] = contact.firstName
                    it[this.lastName] = contact.lastName
                }

                contact.emails.forEach { (type, eml) ->
                    Emails.insert {
                        it[contactId] = contact.contactId
                        it[emailType] = type
                        it[email] = eml
                    }
                }
                contact.phoneNumbers.forEach { (type, number) ->
                    PhoneNumbers.insert {
                        it[contactId] = contact.contactId
                        it[phoneNumberType] = type
                        it[phoneNumber] = number
                    }
                }
                contact.addresses.forEach { (type, addressValue) ->
                    Addresses.insert {
                        it[contactId] = contact.contactId
                        it[addressType] = type
                        it[address] = addressValue
                    }
                }
            }
            Either.Right(contact)
        } catch (e: Exception) {
            Either.Left(Exception("Error while adding contact in table."))
        }
    }
    fun deleteContactInTable(contactId: UUID): Either<Exception, String> {
        return try {
            transaction {
                PhoneNumbers.deleteWhere { PhoneNumbers.contactId eq contactId }
                Emails.deleteWhere { Emails.contactId eq contactId }
                Addresses.deleteWhere { Addresses.contactId eq contactId }
                Contacts.deleteWhere { Contacts.contactId eq contactId }
            }
            Either.Right("Contact is deleted.")
        } catch (e: Exception) {
            Either.Left(Exception("Error while deleting contact in table."))
        }
    }
    fun editContactInTable(contactId: UUID, contact: Contact): Either<Exception, String> {
        return try {
            transaction {
                Contacts.update({ Contacts.contactId eq contactId }) {
                    it[firstName] = contact.firstName
                    it[lastName] = contact.lastName
                }

                contact.emails.forEach { (type, eml) ->
                    Emails.update({ Emails.contactId eq contactId }) {
                        it[emailType] = type
                        it[email] = eml
                    }
                }

                contact.phoneNumbers.forEach { (type, number) ->
                    PhoneNumbers.update({ PhoneNumbers.contactId eq contactId }) {
                        it[phoneNumberType] = type
                        it[phoneNumber] = number
                    }
                }

                contact.addresses.forEach { (type, addressValue) ->
                    Addresses.update({ Addresses.contactId eq contactId }) {
                        it[addressType] = type
                        it[address] = addressValue
                    }
                }
            }
            Either.Right("Contact is edited.")
        } catch (e: Exception) {
            Either.Left(Exception("Error while editing contact in table."))
        }
    }
    fun fetchContactInTable(contactId: UUID): Either<Exception, Contact> {
        /*return Contacts.select { Contacts.contactId eq contactId }
            .map { Contact(it[Contacts.contactId], it[Contacts.firstName], it[Contacts.lastName],
                it[Emails.emailType], it[PhoneNumbers.phoneNumberType], it[Addresses.addressType]) }
            .firstOrNull()
        */
        return try {
            lateinit var contact: Contact
            transaction {
                val contactRow = Contacts.select { Contacts.contactId eq contactId }.firstOrNull()
                if (contactRow != null) {
                    val firstName = contactRow[Contacts.firstName]
                    val lastName = contactRow[Contacts.lastName]
                    val emails = Emails.select { Emails.contactId eq contactId }.associate {
                        it[Emails.emailType] to it[Emails.email]
                    }
                    val phoneNumbers = PhoneNumbers.select { PhoneNumbers.contactId eq contactId }.associate {
                        it[PhoneNumbers.phoneNumberType] to it[PhoneNumbers.phoneNumber]
                    }
                    val addresses = Addresses.select { Addresses.contactId eq contactId }.associate {
                        it[Addresses.addressType] to it[Addresses.address]
                    }
                    contact = Contact(
                        contactId, firstName, lastName, emails.toMutableMap(), phoneNumbers.toMutableMap(),
                        addresses.toMutableMap()
                    )
                }
            }
            Either.Right(contact)
        } catch (e: Exception) {
            Either.Left(Exception("Error while fetching contact in table."))
        }
    }

    fun fetchAllContactsInTable(): Either<Exception, List<Contact>> {
        return try {
            val contacts = mutableListOf<Contact>()
            transaction {
                Contacts.selectAll().forEach { contactRow ->
                    val contactId = contactRow[Contacts.contactId]
                    val firstName = contactRow[Contacts.firstName]
                    val lastName = contactRow[Contacts.lastName]
                    val emails = Emails.select { Emails.contactId eq contactId }.associate {
                        it[Emails.emailType] to it[Emails.email]
                    }
                    val phoneNumbers = PhoneNumbers.select { PhoneNumbers.contactId eq contactId }.associate {
                        it[PhoneNumbers.phoneNumberType] to it[PhoneNumbers.phoneNumber]
                    }
                    val addresses = Addresses.select { Addresses.contactId eq contactId }.associate {
                        it[Addresses.addressType] to it[Addresses.address]
                    }
                    contacts.add(
                        Contact(
                            contactId, firstName, lastName, emails.toMutableMap(), phoneNumbers.toMutableMap(),
                            addresses.toMutableMap()
                        )
                    )
                }
            }
            Either.Right(contacts)
        } catch (e: Exception) {
            Either.Left(Exception("Error while fetching all contacts in table."))
        }
    }
}