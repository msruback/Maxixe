package com.msruback.maxixe.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity("Sub_Events",
    primaryKeys = ["Main","Sub"],
    foreignKeys = [
        ForeignKey(
            entity = Event::class,
            parentColumns = ["Id"],
            childColumns = ["Main"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Event::class,
            parentColumns = ["Id"],
            childColumns = ["Sub"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class SubEvent(
    @ColumnInfo("Main", index = true)val main: Long,
    @ColumnInfo("Sub", index = true)val sub: Long
)