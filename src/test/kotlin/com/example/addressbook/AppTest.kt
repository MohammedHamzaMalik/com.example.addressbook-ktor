package com.addressBook

import AppContext
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll

open class AppTest {
    companion object {
        lateinit var appCtx: AppContext

        @JvmStatic
        @BeforeAll
        fun setUp() {
            println("---------------")
            val db = connectToDatabase()
            appCtx = AppContext(db)
            resetDatabase()
        }

//        @JvmStatic
//        @AfterAll
//        fun tearDown() {
//            println("****************")
//            resetDatabase()
//        }
    }
}