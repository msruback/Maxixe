package com.msruback.maxixe.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.junit.After
import org.junit.Before

abstract class DatabaseTest {

    lateinit var maxixeDatabase: MaxixeDatabase

    @Before
    fun setupDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        println("Setup")
        maxixeDatabase = Room.inMemoryDatabaseBuilder(context, MaxixeDatabase::class.java)
            .allowMainThreadQueries().build()
    }

    @After
    fun teardownDatabase() {
        maxixeDatabase.close()
    }
}