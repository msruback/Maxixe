package com.msruback.maxixe.database

import com.msruback.maxixe.database.entities.Character
import com.msruback.maxixe.database.entities.CharacterTag
import com.msruback.maxixe.database.entities.Contact
import com.msruback.maxixe.database.entities.ContactTag
import com.msruback.maxixe.database.entities.Event
import com.msruback.maxixe.database.entities.EventTag
import com.msruback.maxixe.database.entities.Purchase
import com.msruback.maxixe.database.entities.PurchaseTag
import com.msruback.maxixe.database.entities.Socials
import com.msruback.maxixe.database.entities.Tag
import org.junit.Assert
import org.junit.Test

class TagTests : DatabaseTest() {
    private val testTag = Tag(0,"Test",0)
    private var testCharacter = Character(
        0,
        "Maxixe",
        "They/Them",
        "Your Fandom Assistant",
        "www.example.com"
    )
    private val testContact = Contact(
        0,
        "Test",
        "They/Them",
        "A Test User",
        Socials("example.com", "twitter.com", "bsky.app", "tumblr.com", "instagram.com")
    )
    private var testPurchase = Purchase(
        0,
        "Test Doodle",
        3.50,
        3.00,
        6.50,
        795416400000,
        0
    )
    private val testEvent = Event(0, "TestCon", "Test", 795416400000, 795416400000)

    @Test
    fun test_insert(){
        val id = maxixeDatabase.tagDao().insert(testTag)

        val storedTag = maxixeDatabase.tagDao().select(id)

        Assert.assertEquals(id, storedTag.id)
        Assert.assertEquals(testTag.name, storedTag.name)
        Assert.assertEquals(testTag.type, storedTag.type)
    }

    @Test
    fun test_selectAllByType(){
        val id = maxixeDatabase.tagDao().insert(testTag)

        val storedTags = maxixeDatabase.tagDao().selectAllByType(0)

        Assert.assertEquals(1, storedTags.size)
        Assert.assertEquals(id, storedTags[0].id)
        Assert.assertEquals(testTag.name, storedTags[0].name)
        Assert.assertEquals(testTag.type, storedTags[0].type)
    }

    @Test
    fun test_selectAllByCharacter(){
        testCharacter.owner = maxixeDatabase.contactDao().insert(testContact)
        val charId = maxixeDatabase.characterDao().insert(testCharacter)
        val tagId = maxixeDatabase.tagDao().insert(testTag)
        maxixeDatabase.characterDao().addTags(CharacterTag(charId, tagId))

        val storedTags = maxixeDatabase.tagDao().selectAllByCharacter(charId)

        Assert.assertEquals(1, storedTags.size)
        Assert.assertEquals(tagId, storedTags[0].id)
        Assert.assertEquals(testTag.name, storedTags[0].name)
        Assert.assertEquals(testTag.type, storedTags[0].type)
    }

    @Test
    fun test_selectAllByContact(){
        val conId = maxixeDatabase.contactDao().insert(testContact)
        val tagId = maxixeDatabase.tagDao().insert(testTag)
        maxixeDatabase.contactDao().addTags(ContactTag(conId, tagId))

        val storedTags = maxixeDatabase.tagDao().selectAllByContact(conId)

        Assert.assertEquals(1, storedTags.size)
        Assert.assertEquals(tagId, storedTags[0].id)
        Assert.assertEquals(testTag.name, storedTags[0].name)
        Assert.assertEquals(testTag.type, storedTags[0].type)
    }

    @Test
    fun test_selectAllByEvent(){
        val eventId = maxixeDatabase.eventDao().insert(testEvent)
        val tagId = maxixeDatabase.tagDao().insert(testTag)
        maxixeDatabase.eventDao().addTags(EventTag(eventId, tagId))

        val storedTags = maxixeDatabase.tagDao().selectAllByEvent(eventId)

        Assert.assertEquals(1, storedTags.size)
        Assert.assertEquals(tagId, storedTags[0].id)
        Assert.assertEquals(testTag.name, storedTags[0].name)
        Assert.assertEquals(testTag.type, storedTags[0].type)
    }

    @Test
    fun test_selectAllByPurchase() {
        testPurchase.seller = maxixeDatabase.contactDao().insert(testContact)
        val purchId = maxixeDatabase.purchaseDao().insert(testPurchase)
        val tagId = maxixeDatabase.tagDao().insert(testTag)

        maxixeDatabase.purchaseDao().addTags(PurchaseTag(purchId, tagId))

        val storedTags = maxixeDatabase.tagDao().selectAllByPurchase(purchId)

        Assert.assertEquals(1, storedTags.size)
        Assert.assertEquals(tagId, storedTags[0].id)
        Assert.assertEquals(testTag.name, storedTags[0].name)
        Assert.assertEquals(testTag.type, storedTags[0].type)
    }
}