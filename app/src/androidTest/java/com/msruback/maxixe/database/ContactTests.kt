package com.msruback.maxixe.database

import com.msruback.maxixe.database.entities.Contact
import com.msruback.maxixe.database.entities.ContactTag
import com.msruback.maxixe.database.entities.Socials
import com.msruback.maxixe.database.entities.Tag
import org.junit.Assert
import org.junit.Test

class ContactTests : DatabaseTest() {
    private val testContact = Contact(
        0,
        "Test",
        "They/Them",
        "A Test User",
        Socials("example.com", "twitter.com", "bsky.app", "tumblr.com", "instagram.com")
    )
    private val testTag = Tag(0,"Test",3)

    @Test
    fun test_insert() {
        val id = maxixeDatabase.contactDao().insert(testContact)

        val storedContact = maxixeDatabase.contactDao().select(id)

        Assert.assertEquals(id, storedContact.id)
        Assert.assertEquals(testContact.name, storedContact.name)
        Assert.assertEquals(testContact.desc, storedContact.desc)
        Assert.assertEquals(testContact.pronouns, storedContact.pronouns)
        Assert.assertEquals(testContact.socials, storedContact.socials)
        Assert.assertEquals(testContact.isUser, storedContact.isUser)
    }


    @Test
    fun test_selectAllByTag(){
        val conId = maxixeDatabase.contactDao().insert(testContact)
        val tagId = maxixeDatabase.tagDao().insert(testTag)
        maxixeDatabase.contactDao().addTags(ContactTag(conId, tagId))

        val storedContacts = maxixeDatabase.contactDao().selectAllByTag(tagId)

        Assert.assertEquals( 1, storedContacts.size)
        Assert.assertEquals( conId, storedContacts[0].id)
        Assert.assertEquals(testContact.name, storedContacts[0].name)
        Assert.assertEquals(testContact.desc, storedContacts[0].desc)
        Assert.assertEquals(testContact.pronouns, storedContacts[0].pronouns)
        Assert.assertEquals(testContact.socials, storedContacts[0].socials)
        Assert.assertEquals(testContact.isUser, storedContacts[0].isUser)
    }
}