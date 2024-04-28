package com.msruback.maxixe.database.queries

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.msruback.maxixe.database.entities.Character
import com.msruback.maxixe.database.entities.Contact
import com.msruback.maxixe.database.entities.ContactTag
import com.msruback.maxixe.database.entities.Social
import com.msruback.maxixe.database.entities.Tag

data class EverythingContact(
    @Embedded val contact: Contact,
    @Relation(
        entity = Social::class,
        parentColumn = "Id",
        entityColumn = "Contact"
    )
    val socials: List<Social>,
    @Relation(
        entity = Tag::class,
        parentColumn = "Id",
        entityColumn = "Id",
        associateBy = Junction(
            value = ContactTag::class,
            parentColumn = "Contact",
            entityColumn = "Tag"
        )
    )
    val tags: List<Tag>,
    @Relation(
        entity = Character::class,
        parentColumn = "Id",
        entityColumn = "Owner"
    )
    val characters: List<Character>,
)