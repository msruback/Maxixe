package com.msruback.maxixe.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity("Purchase_Tags",
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
class PurchaseTag {
}