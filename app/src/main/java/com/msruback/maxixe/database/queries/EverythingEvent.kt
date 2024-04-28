package com.msruback.maxixe.database.queries

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.msruback.maxixe.database.entities.Event
import com.msruback.maxixe.database.entities.EventTag
import com.msruback.maxixe.database.entities.SubEvent
import com.msruback.maxixe.database.entities.Tag

data class EverythingEvent(
    @Embedded val event: Event,
    @Relation(
        entity = Event::class,
        parentColumn = "Id",
        entityColumn = "Id",
        associateBy = Junction(
            value = SubEvent::class,
            parentColumn = "Main",
            entityColumn = "Sub"
        )
    )
    val subEvent: List<Event>,
    @Relation(
        entity = Tag::class,
        parentColumn = "Id",
        entityColumn = "Id",
        associateBy = Junction(
            value = EventTag::class,
            parentColumn = "Event",
            entityColumn = "Tag"
        )
    )
    val tags: List<Tag>
)