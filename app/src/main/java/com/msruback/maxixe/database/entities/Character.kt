package com.msruback.maxixe.database.entities

import androidx.room.*

@Entity(
    "Characters",
    foreignKeys = [
        ForeignKey(
            entity = Contact::class,
            parentColumns = ["Id"],
            childColumns =  ["Owner"]
        )
    ]
)
data class Character(
    @PrimaryKey(true) @ColumnInfo("Id") val id:Int,
    @ColumnInfo("Name") var name: String,
    @ColumnInfo("Pronouns") var pronouns: String,
    @ColumnInfo("Desc") var desc: String,
    @ColumnInfo("Link") var link: String,
    @ColumnInfo("Owner", index = true) var owner: Int,
    @ColumnInfo("IsUserOwned") var isUserOwned: Boolean = false
)