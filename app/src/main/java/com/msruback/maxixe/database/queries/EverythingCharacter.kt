package com.msruback.maxixe.database.queries

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.msruback.maxixe.database.entities.Character
import com.msruback.maxixe.database.entities.CharacterTag
import com.msruback.maxixe.database.entities.Purchase
import com.msruback.maxixe.database.entities.PurchaseCharacter
import com.msruback.maxixe.database.entities.Tag

data class EverythingCharacter(
    @Embedded val character: Character,
    @Relation(
        entity = Purchase::class,
        parentColumn = "Id",
        entityColumn = "Id",
        associateBy = Junction(
            value = PurchaseCharacter::class,
            parentColumn = "Character",
            entityColumn = "Purchase"
        )
    )
    val purchases: List<PurchaseSellerEvent>,
    @Relation(
        entity = Tag::class,
        parentColumn = "Id",
        entityColumn = "Id",
        associateBy = Junction(
            value = CharacterTag::class,
            parentColumn = "Character",
            entityColumn = "Tag"
        )
    )
    val tags: List<Tag>
)