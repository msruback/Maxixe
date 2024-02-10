package com.msruback.maxixe.database.entities

import androidx.room.*

@Entity("Events")
data class Event(
    @PrimaryKey(true) @ColumnInfo("Id") val id: Int,
    @ColumnInfo("Name") var name: String,
    @ColumnInfo("Location") var location: String,
    @ColumnInfo("Start") var start: Long,
    @ColumnInfo("End") var end: Long
)