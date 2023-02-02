package com.addressBook.tables

import org.jetbrains.exposed.sql.Table

object Contacts : Table("contacts") {
    val contactId = uuid("contact_id").autoGenerate()
    val firstName = varchar("first_name", length = 100)
    val lastName = varchar("last_name", length = 100)

    override val primaryKey = PrimaryKey(contactId, name = "PK_Contact_ID")
}

object PhoneNumbers: Table("phone_numbers") {
    val phoneNumberId = uuid("phone_number_id").autoGenerate()
    val contactId = (uuid("contact_id") references Contacts.contactId).index()
    val phoneNumberType = varchar("phone_number_type", length = 100)
    val phoneNumber = varchar("phone_number", length = 100)

    override val primaryKey = PrimaryKey(phoneNumberId, name = "PK_PhoneNumber_ID")
}

object Emails: Table("emails") {
    val emailId = uuid("email_id").autoGenerate()
    val contactId = (uuid("contact_id") references Contacts.contactId).index()
    val emailType = varchar("email_type", length = 100)
    val email = varchar("email", length = 100)

    override val primaryKey = PrimaryKey(emailId, name = "PK_Email_ID")

//        fun addNew(cid: UUID, et: String, eml: String): InsertStatement<Number> {
//            return insert {
//                it[contactId] = cid
//                it[emailType] = et
//                it[email] = eml
//            }
//        }
}

object Addresses: Table("addresses") {
    val addressId = uuid("address_id").autoGenerate()
    val contactId = (uuid("contact_id") references Contacts.contactId).index()
    val addressType = varchar("address_type", length = 100)
    val address = varchar("address", length = 100)

    override val primaryKey = PrimaryKey(addressId, name = "PK_Address_ID")
}