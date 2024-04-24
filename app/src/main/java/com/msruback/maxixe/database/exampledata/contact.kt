package com.msruback.maxixe.database.exampledata

import com.msruback.maxixe.database.entities.Contact
import com.msruback.maxixe.database.entities.Socials

val basicContact = Contact(
    0,
    "Test",
    "They/Them",
    "A Test User",
    Socials("example.com", "twitter.com", "bsky.app", "tumblr.com", "instagram.com")
)
val seller = Contact(0, "Seller", "They/Them", "Seller Description", Socials())