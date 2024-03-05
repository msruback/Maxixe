package com.msruback.maxixe.database.entities

import androidx.room.*

@Entity(
    "Purchases",
    foreignKeys = [
        ForeignKey(
            entity = Contact::class,
            parentColumns = ["Id"],
            childColumns = ["Seller"]
        ),
        ForeignKey(
            entity = Event::class,
            parentColumns = ["Id"],
            childColumns = ["Event"]
        )
    ]
)
data class Purchase(
    @PrimaryKey(true) @ColumnInfo("Id") val id: Long,
    @ColumnInfo("Desc") var desc: String,
    @ColumnInfo("Cost") var cost: Double,
    @ColumnInfo("Tip") var tip: Double,
    @ColumnInfo("Total") var total: Double,
    @ColumnInfo("Date") var date: Long,
    @ColumnInfo("Seller", index = true) var seller: Long,
    @ColumnInfo("Event", index = true) var event: Long? = null
)