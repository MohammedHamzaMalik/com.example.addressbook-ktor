package com.example.addressbook

import com.addressBook.dbSetup.connectToDatabase
import com.addressBook.dbSetup.resetDatabase
import com.example.addressbook.apis.plugins.configureRouting
import com.example.addressbook.apis.plugins.configureSerialization
import io.ktor.server.application.*

fun main(args: Array<String>): Unit {
    io.ktor.server.netty.EngineMain.main(args)
    connectToDatabase()
    resetDatabase()
}

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureSerialization()
    configureRouting()
}
