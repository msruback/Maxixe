package com.msruback.maxixe.database.entities

import androidx.room.*

@Entity("Character_Tags",
        foreignKeys = [
                ForeignKey(
                        entity = Character::class,
                        parentColumns = ["Id"],
                        childColumns = ["Character"],
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
data class CharacterTag (
        @ColumnInfo("Character", index = true)val character: Int,
        @ColumnInfo("Tag", index = true)val tag: Int
)