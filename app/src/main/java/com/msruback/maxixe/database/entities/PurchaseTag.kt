package com.msruback.maxixe.database.entities

import androidx.room.*

@Entity("Purchase_Tags",
    primaryKeys = ["Purchase","Tag"],
    foreignKeys = [
        ForeignKey(
            entity = Purchase::class,
            parentColumns = ["Id"],
            childColumns = ["Purchase"],
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
class PurchaseTag (
    @ColumnInfo("Purchase", index = true)val purchase: Long,
    @ColumnInfo("Tag", index = true)val tag: Long
)