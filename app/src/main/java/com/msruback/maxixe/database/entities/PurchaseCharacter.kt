package com.msruback.maxixe.database.entities

import androidx.room.*

@Entity("Purchase_Characters",
    primaryKeys = ["Purchase","Character"],
    foreignKeys = [
        ForeignKey(
            entity = Purchase::class,
            parentColumns = ["Id"],
            childColumns = ["Purchase"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Character::class,
            parentColumns = ["Id"],
            childColumns = ["Character"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PurchaseCharacter (
    @ColumnInfo("Purchase", index = true)val purchase: Long,
    @ColumnInfo("Character", index = true)val character: Long
)