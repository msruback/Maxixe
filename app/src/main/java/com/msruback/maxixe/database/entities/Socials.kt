package com.msruback.maxixe.database.entities

import androidx.room.ColumnInfo

data class Socials(
    @ColumnInfo("Website") var website: String,
    @ColumnInfo("Twitter") var twitter: String,
    @ColumnInfo("Bsky") var bsky: String,
    @ColumnInfo("Tumblr") var tumblr: String,
    @ColumnInfo("Insta") var insta: String
)