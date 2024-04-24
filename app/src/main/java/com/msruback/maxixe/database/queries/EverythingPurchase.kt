package com.msruback.maxixe.database.queries

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.msruback.maxixe.database.entities.Character
import com.msruback.maxixe.database.entities.Contact
import com.msruback.maxixe.database.entities.Event
import com.msruback.maxixe.database.entities.Purchase
import com.msruback.maxixe.database.entities.PurchaseCharacter
import com.msruback.maxixe.database.entities.PurchaseTag
import com.msruback.maxixe.database.entities.Tag

data class EverythingPurchase(
    @Embedded val purchase: Purchase,
    @Relation(
        entity = Contact::class,
        parentColumn = "Seller",
        entityColumn = "Id"
    ) val seller: Contact?,
    @Relation(
        entity = Character::class,
        parentColumn = "Id",
        entityColumn = "Id",
        associateBy = Junction(
            value = PurchaseCharacter::class,
            parentColumn = "Purchase",
            entityColumn = "Character"
        )
    )
    val characters: List<Character>,
    @Relation(
        entity = Tag::class,
        parentColumn = "Id",
        entityColumn = "Id",
        associateBy = Junction(
            value = PurchaseTag::class,
            parentColumn = "Purchase",
            entityColumn = "Tag"
        )
    )
    val tags: List<Tag>,
    @Relation(
        entity = Event::class,
        parentColumn = "Event",
        entityColumn = "Id"
    ) val event: Event? = null
)