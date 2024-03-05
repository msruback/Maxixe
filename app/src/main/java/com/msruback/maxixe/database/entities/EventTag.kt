package com.msruback.maxixe.database.entities

import androidx.room.*

@Entity("Event_Tags",
    primaryKeys = ["Event","Tag"],
    foreignKeys = [
        ForeignKey(
            entity = Event::class,
            parentColumns = ["Id"],
            childColumns = ["Event"],
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
data class EventTag (
    @ColumnInfo("Event", index = true)val event: Long,
    @ColumnInfo("Tag", index = true)val tag: Long
)