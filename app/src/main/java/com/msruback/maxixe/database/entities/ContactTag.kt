package com.msruback.maxixe.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity("Contact_Tags",
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
class ContactTag (
    @ColumnInfo("Contact", index = true)val contact: Int,
    @ColumnInfo("Tag", index = true)val tag: Int
)