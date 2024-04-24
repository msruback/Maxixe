package com.msruback.maxixe.database.queries

import androidx.room.Embedded
import androidx.room.Relation
import com.msruback.maxixe.database.entities.Contact
import com.msruback.maxixe.database.entities.Event
import com.msruback.maxixe.database.entities.Purchase

data class PurchaseSellerEvent(
    @Embedded val purchase: Purchase,
    @Relation(
        entity = Contact::class,
        parentColumn = "Seller",
        entityColumn = "Id"
    ) val seller: Contact? = null,
    @Relation(
        entity = Event::class,
        parentColumn = "Event",
        entityColumn = "Id"
    ) val event: Event? = null
)