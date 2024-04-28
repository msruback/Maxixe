package com.msruback.maxixe.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity("Socials",
        foreignKeys = [
    ForeignKey(
        entity = Contact::class,
        parentColumns = ["Id"],
        childColumns =  ["Contact"]
    )
]
)
data class Social(
    @PrimaryKey(true) @ColumnInfo("Id") val id: Long,
    @ColumnInfo("Contact", index = true) var contact: Long,
    @ColumnInfo("Label") var label:String,
    @ColumnInfo("Value") var value:String
)