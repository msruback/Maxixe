package com.msruback.maxixe.database.dao

import androidx.room.*
import com.msruback.maxixe.database.entities.Event
import com.msruback.maxixe.database.entities.EventTag

@Dao
interface EventDao {
    @Query("SELECT COUNT() FROM Events")
    fun count(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(event: Event): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addTags(vararg eventTag: EventTag)

    @Query("SELECT * FROM Events WHERE Id = :id")
    fun select(id: Long): Event

    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT * FROM Events " +
            "INNER JOIN Event_Tags ON Event = Id " +
            "WHERE Tag = :tag")
    fun selectAllByTag(tag: Long): List<Event>
}