package com.msruback.maxixe.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class MaxixeDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: MaxixeDatabase? = null

        fun getDatabase(context: Context): MaxixeDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MaxixeDatabase::class.java,
                    "maxixe_database"
                ).build()
                INSTANCE = instance

                instance
            }
        }

    }
}