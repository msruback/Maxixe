package com.msruback.maxixe.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity("Contact_Tags",
    primaryKeys = ["Contact","Tag"],
    foreignKeys = [
        ForeignKey(
            entity = Contact::class,
            parentColumns = ["Id"],
            childColumns = ["Contact"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Tag::class,
            parentColumns = ["Id"],
            childColumns = ["Tag"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ContactTag (
    @ColumnInfo("Contact", index = true)val contact: Long,
    @ColumnInfo("Tag", index = true)val tag: Long
)