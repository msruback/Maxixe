package com.msruback.maxixe.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.msruback.maxixe.database.dao.*
import com.msruback.maxixe.database.entities.*

@Database(
    entities = [Character::class, CharacterTag::class, Contact::class, ContactTag::class,
        Event::class, EventTag::class, Purchase::class, PurchaseCharacter::class, PurchaseTag::class,
        Tag::class],
    version = 1,
    exportSchema = true
)
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

    abstract fun characterDao(): CharacterDao
    abstract fun contactDao(): ContactDao
    abstract fun eventDao(): EventDao
    abstract fun purchaseDao(): PurchaseDao
    abstract fun tagDao(): TagDao
}