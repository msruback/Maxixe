package com.msruback.maxixe.database.dao

import androidx.room.*
import com.msruback.maxixe.database.entities.Tag

@Dao
interface TagDao {
    @Query("SELECT COUNT() FROM Tags")
    fun count(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(tag: Tag): Long

    @Query("SELECT * FROM Tags WHERE Id = :id")
    fun select(id: Long): Tag

    @Query("SELECT * FROM Tags WHERE Type = :type")
    fun selectAllByType(type: Int = 0): List<Tag>

    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT * FROM Tags " +
            "INNER JOIN Character_Tags ON Tag = Id " +
            "WHERE Character = :character")
    fun selectAllByCharacter(character: Long): List<Tag>

    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT * FROM Tags " +
            "INNER JOIN Contact_Tags ON Tag = Id " +
            "WHERE Contact = :contact")
    fun selectAllByContact(contact: Long): List<Tag>

    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT * FROM Tags " +
            "INNER JOIN Event_Tags ON Tag = Id " +
            "WHERE Event = :event")
    fun selectAllByEvent(event: Long): List<Tag>

    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT * FROM Tags " +
            "INNER JOIN Purchase_Tags ON Tag = Id " +
            "WHERE Purchase = :purchase")
    fun selectAllByPurchase(purchase: Long): List<Tag>
}