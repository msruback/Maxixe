package com.msruback.maxixe.database

import com.msruback.maxixe.database.entities.CharacterTag
import com.msruback.maxixe.database.entities.ContactTag
import com.msruback.maxixe.database.entities.EventTag
import com.msruback.maxixe.database.entities.PurchaseTag
import com.msruback.maxixe.database.exampledata.basicCharacter
import com.msruback.maxixe.database.exampledata.basicContact
import com.msruback.maxixe.database.exampledata.basicEvent
import com.msruback.maxixe.database.exampledata.basicPurchase
import com.msruback.maxixe.database.exampledata.basicTag
import org.junit.Assert
import org.junit.Test

class TagTests : DatabaseTest() {
    @Test
    fun test_insert(){
        val id = maxixeDatabase.tagDao().insert(basicTag)

        val storedTag = maxixeDatabase.tagDao().select(id)

        Assert.assertEquals(id, storedTag.id)
        Assert.assertEquals(basicTag.name, storedTag.name)
        Assert.assertEquals(basicTag.type, storedTag.type)
    }

    @Test
    fun test_selectAllByType(){
        val id = maxixeDatabase.tagDao().insert(basicTag)

        val storedTags = maxixeDatabase.tagDao().selectAllByType(0)

        Assert.assertEquals(1, storedTags.size)
        Assert.assertEquals(id, storedTags[0].id)
        Assert.assertEquals(basicTag.name, storedTags[0].name)
        Assert.assertEquals(basicTag.type, storedTags[0].type)
    }

    @Test
    fun test_selectAllByCharacter(){
        val charId = maxixeDatabase.characterDao().insert(basicCharacter)
        val tagId = maxixeDatabase.tagDao().insert(basicTag)
        maxixeDatabase.characterDao().addTags(CharacterTag(charId, tagId))

        val storedTags = maxixeDatabase.tagDao().selectAllByCharacter(charId)

        Assert.assertEquals(1, storedTags.size)
        Assert.assertEquals(tagId, storedTags[0].id)
        Assert.assertEquals(basicTag.name, storedTags[0].name)
        Assert.assertEquals(basicTag.type, storedTags[0].type)
    }

    @Test
    fun test_selectAllByContact(){
        val conId = maxixeDatabase.contactDao().insert(basicContact)
        val tagId = maxixeDatabase.tagDao().insert(basicTag)
        maxixeDatabase.contactDao().addTags(ContactTag(conId, tagId))

        val storedTags = maxixeDatabase.tagDao().selectAllByContact(conId)

        Assert.assertEquals(1, storedTags.size)
        Assert.assertEquals(tagId, storedTags[0].id)
        Assert.assertEquals(basicTag.name, storedTags[0].name)
        Assert.assertEquals(basicTag.type, storedTags[0].type)
    }

    @Test
    fun test_selectAllByEvent(){
        val eventId = maxixeDatabase.eventDao().insert(basicEvent)
        val tagId = maxixeDatabase.tagDao().insert(basicTag)
        maxixeDatabase.eventDao().addTags(EventTag(eventId, tagId))

        val storedTags = maxixeDatabase.tagDao().selectAllByEvent(eventId)

        Assert.assertEquals(1, storedTags.size)
        Assert.assertEquals(tagId, storedTags[0].id)
        Assert.assertEquals(basicTag.name, storedTags[0].name)
        Assert.assertEquals(basicTag.type, storedTags[0].type)
    }

    @Test
    fun test_selectAllByPurchase() {
        val purchaseId = maxixeDatabase.purchaseDao().insert(basicPurchase)
        val tagId = maxixeDatabase.tagDao().insert(basicTag)

        maxixeDatabase.purchaseDao().addTags(PurchaseTag(purchaseId, tagId))

        val storedTags = maxixeDatabase.tagDao().selectAllByPurchase(purchaseId)

        Assert.assertEquals(1, storedTags.size)
        Assert.assertEquals(tagId, storedTags[0].id)
        Assert.assertEquals(basicTag.name, storedTags[0].name)
        Assert.assertEquals(basicTag.type, storedTags[0].type)
    }
}