package com.msruback.maxixe.database.entities

import androidx.room.*

@Entity(
    "Purchases",
    foreignKeys = [
        ForeignKey(
            entity = Contact::class,
            parentColumns = ["Id"],
            childColumns = ["Seller"]
        )
    ]
)
data class Purchase(
    @PrimaryKey(true) @ColumnInfo("Id") val id: Int,
    @ColumnInfo("Cost") var cost: Double,
    @ColumnInfo("Tip") var tip: Double,
    @ColumnInfo("Total") var total: Double,
    @ColumnInfo("Date") var date: Long,
    @ColumnInfo("Seller", index = true) var seller: Int
)