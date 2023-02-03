package com.example.addressbook.repo

import arrow.core.Either
import com.addressBook.tables.PhoneNumbers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

object PhoneNumberRepo {
    fun addPhoneNumberInContactInTable(contactId: UUID, phoneNumberType: String, phoneNumber: String): Either<Exception, String> {
        return try {
            transaction {
                PhoneNumbers.insert {
                    it[this.contactId] = contactId
                    it[this.phoneNumberType] = phoneNumberType
                    it[this.phoneNumber] = phoneNumber
                }
            }
            Either.Right("Phone number is added.")
        } catch (e: Exception) {
            Either.Left(Exception("Error while adding phone number in contact."))
        }
    }

    fun deletePhoneNumberInContact(contactId: UUID, phoneNumberType: String): Either<Exception, String> {
        return try {
            transaction {
                PhoneNumbers.deleteWhere { PhoneNumbers.contactId eq contactId and (PhoneNumbers.phoneNumberType eq phoneNumberType) }
            }
            Either.Right("Phone number is deleted.")
        } catch (e: Exception) {
            Either.Left(Exception("Error while deleting phone number in contact."))
        }
    }

    fun updatePhoneNumberInContact(contactId: UUID, phoneNumberType: String, phoneNumber: String): Either<Exception, String> {
        return try {
            transaction {
                PhoneNumbers.update({ PhoneNumbers.contactId eq contactId and (PhoneNumbers.phoneNumberType eq phoneNumberType) }) {
                    it[this.phoneNumber] = phoneNumber
                }
            }
            Either.Right("Phone number is updated.")
        } catch (e: Exception) {
            Either.Left(Exception("Error while updating phone number in contact."))
        }
    }

    fun fetchPhoneNumberInContact(contactId: UUID, phoneNumberType: String): Either<Exception, String> {
        return try {
            val phoneNumber = transaction {
                PhoneNumbers.select { PhoneNumbers.contactId eq contactId and (PhoneNumbers.phoneNumberType eq phoneNumberType) }
                    .map { it[PhoneNumbers.phoneNumber] }
                    .first()
            }
            Either.Right(phoneNumber)
        } catch (e: Exception) {
            Either.Left(Exception("Error while fetching phone number in contact."))
        }
    }

    fun fetchAllPhoneNumbersInContact(contactId: UUID): Either<Exception, List<String>> {
        return try {
            val phoneNumbers = transaction {
                PhoneNumbers.select { PhoneNumbers.contactId eq contactId }
                    .map { it[PhoneNumbers.phoneNumber] }
            }
            Either.Right(phoneNumbers)
        } catch (e: Exception) {
            Either.Left(Exception("Error while fetching all phone numbers in contact."))
        }
    }
}