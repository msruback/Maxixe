package com.msruback.maxixe.database.dao

import androidx.room.*
import com.msruback.maxixe.database.entities.Character
import com.msruback.maxixe.database.entities.CharacterTag

@Dao
interface CharacterDao {
    @Query("SELECT COUNT() FROM Characters")
    fun count(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(character: Character): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addTags(vararg characterTag: CharacterTag)

    @Query("SELECT * FROM Characters WHERE Id = :id")
    fun select(id: Long): Character

    @Query("SELECT * FROM Characters WHERE Owner = :owner")
    fun selectAllByOwner(owner: Long): List<Character>

    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT * FROM Characters " +
            "INNER JOIN Purchase_Characters On Character = Id " +
            "WHERE Purchase = :purchase")
    fun selectAllByPurchase(purchase: Long): List<Character>

    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT * FROM Characters " +
            "INNER JOIN Character_Tags ON Character = Id " +
            "WHERE Tag = :tag")
    fun selectAllByTag(tag: Long): List<Character>
}