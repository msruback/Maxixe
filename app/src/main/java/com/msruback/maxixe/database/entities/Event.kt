package com.msruback.maxixe.database.entities

import androidx.room.*

@Entity("Events")
data class Event(
    @PrimaryKey(true) @ColumnInfo("Id") val id: Long,
    @ColumnInfo("Name") var name: String,
    @ColumnInfo("Byline") var byline: String,
    @ColumnInfo("Desc") var desc: String,
    @ColumnInfo("Location") var location: String,
    @ColumnInfo("Start") var start: Long,
    @ColumnInfo("End") var end: Long,
    @ColumnInfo("Is_Sub_Event") var isSubEvent: Boolean = false
)