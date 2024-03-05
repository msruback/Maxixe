package com.msruback.maxixe.database.entities

import androidx.room.*

data class Subscription (
    @PrimaryKey(autoGenerate = true) @ColumnInfo("Id") val id:Long,
    @ColumnInfo("Name") var name: String,
    @ColumnInfo("Link") var link: String,
    @ColumnInfo("Desc") var desc: String,
    @ColumnInfo("Is_Ongoing") var isOngoing: Boolean,
    @ColumnInfo("Start") var start: Long,
    @ColumnInfo("End") var end: Long?
    )