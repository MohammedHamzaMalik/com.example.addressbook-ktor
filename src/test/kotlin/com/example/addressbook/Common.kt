package com.addressBook

import com.addressBook.tables.*
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction

internal fun connectToDatabase(): Database =
    Database.connect(
        HikariDataSource().apply {
            jdbcUrl = "jdbc:mysql://localhost:3306/address_book"
            username = "hamza"
            password = "password"
            isAutoCommit = false
            maximumPoolSize = 5
        }
    )

internal val schema = listOf<Table>(
    Contacts,
    PhoneNumbers,
    Emails,
    Addresses,
    Groups,
    AllGroupMembers
)

fun resetDatabase() {
    transaction {
        SchemaUtils.drop(*schema.toTypedArray())
        SchemaUtils.create(*schema.toTypedArray())
    }
}