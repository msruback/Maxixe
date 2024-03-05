package com.msruback.maxixe.database.entities

import androidx.room.*

@Entity("Tags")
data class Tag (
    @PrimaryKey(true) @ColumnInfo("Id") val id: Long,
    @ColumnInfo("Name") val name: String,
    // 0:Any 1:Purchase 2:Character 3:Contact 4:Event
    @ColumnInfo("Type") val type: Int
)