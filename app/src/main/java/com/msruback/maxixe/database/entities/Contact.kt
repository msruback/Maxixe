package com.msruback.maxixe.database.entities

import androidx.room.*

@Entity("Contacts")
data class Contact(
    @PrimaryKey(true) @ColumnInfo("Id") val id: Long,
    @ColumnInfo("Name") var name: String,
    @ColumnInfo("Pronouns") var pronouns: String,
    @ColumnInfo("Desc") var desc: String,
    @Embedded var socials: Socials,
    @ColumnInfo("IsUser") var isUser: Boolean = false
)