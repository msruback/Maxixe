package com.msruback.maxixe.database.dao

import androidx.room.*
import com.msruback.maxixe.database.entities.Contact
import com.msruback.maxixe.database.entities.ContactTag

@Dao
interface ContactDao {
    @Query("SELECT COUNT() FROM Contacts")
    fun count(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(contact: Contact): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addTags(vararg contactTag: ContactTag)

    @Query("SELECT * FROM Contacts WHERE Id = :id")
    fun select(id: Long): Contact

    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT * FROM Contacts " +
            "INNER JOIN Contact_Tags ON Contact = Id " +
            "WHERE Tag = :tag")
    fun selectAllByTag(tag: Long): List<Contact>
}