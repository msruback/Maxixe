package com.msruback.maxixe.database

import com.msruback.maxixe.database.entities.Character
import com.msruback.maxixe.database.entities.Contact
import com.msruback.maxixe.database.entities.Event
import com.msruback.maxixe.database.entities.Purchase
import com.msruback.maxixe.database.entities.PurchaseCharacter
import com.msruback.maxixe.database.entities.PurchaseTag
import com.msruback.maxixe.database.entities.Socials
import com.msruback.maxixe.database.entities.Tag
import org.junit.Assert
import org.junit.Test

class PurchaseTests : DatabaseTest() {
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
    private val testTag = Tag(0,"Test",1)

    @Test
    fun test_insert() {
        testPurchase.seller = maxixeDatabase.contactDao().insert(testContact)
        val id = maxixeDatabase.purchaseDao().insert(testPurchase)

        val storedPurchase = maxixeDatabase.purchaseDao().select(id)

        Assert.assertEquals(id, storedPurchase.purchase.id)
        Assert.assertEquals(testPurchase.desc, storedPurchase.purchase.desc)
        Assert.assertEquals(testPurchase.cost, storedPurchase.purchase.cost, 0.0)
        Assert.assertEquals(testPurchase.tip, storedPurchase.purchase.tip, 0.0)
        Assert.assertEquals(testPurchase.total, storedPurchase.purchase.total, 0.0)
        Assert.assertEquals(testPurchase.date, storedPurchase.purchase.date)
        Assert.assertEquals(testPurchase.seller, storedPurchase.purchase.seller)
        Assert.assertEquals(testPurchase.event, storedPurchase.purchase.event)

        Assert.assertEquals(testContact.name, storedPurchase.seller.name)
        Assert.assertEquals(testContact.desc, storedPurchase.seller.desc)
        Assert.assertEquals(testContact.pronouns, storedPurchase.seller.pronouns)
        Assert.assertEquals(testContact.socials, storedPurchase.seller.socials)
        Assert.assertEquals(testContact.isUser, storedPurchase.seller.isUser)

    }

    @Test
    fun test_insert_character() {
        testPurchase.seller = maxixeDatabase.contactDao().insert(testContact)
        val purchId = maxixeDatabase.purchaseDao().insert(testPurchase)
        val charId = maxixeDatabase.characterDao().insert(testCharacter)

        maxixeDatabase.purchaseDao().addCharacters(PurchaseCharacter(purchId, charId))

        val storedPurchase = maxixeDatabase.purchaseDao().select(purchId)

        Assert.assertEquals(purchId, storedPurchase.purchase.id)
        Assert.assertEquals(testPurchase.desc, storedPurchase.purchase.desc)
        Assert.assertEquals(testPurchase.cost, storedPurchase.purchase.cost, 0.0)
        Assert.assertEquals(testPurchase.tip, storedPurchase.purchase.tip, 0.0)
        Assert.assertEquals(testPurchase.total, storedPurchase.purchase.total, 0.0)
        Assert.assertEquals(testPurchase.date, storedPurchase.purchase.date)
        Assert.assertEquals(testPurchase.seller, storedPurchase.purchase.seller)
        Assert.assertEquals(testPurchase.event, storedPurchase.purchase.event)

        Assert.assertEquals(testContact.name, storedPurchase.seller.name)
        Assert.assertEquals(testContact.desc, storedPurchase.seller.desc)
        Assert.assertEquals(testContact.pronouns, storedPurchase.seller.pronouns)
        Assert.assertEquals(testContact.socials, storedPurchase.seller.socials)
        Assert.assertEquals(testContact.isUser, storedPurchase.seller.isUser)

        Assert.assertEquals(1, storedPurchase.characters.size)
        Assert.assertEquals(testCharacter.name, storedPurchase.characters[0].name)
        Assert.assertEquals(testCharacter.pronouns, storedPurchase.characters[0].pronouns)
        Assert.assertEquals(testCharacter.desc, storedPurchase.characters[0].desc)
        Assert.assertEquals(testCharacter.link, storedPurchase.characters[0].link)
        Assert.assertEquals(testCharacter.isUserOwned, storedPurchase.characters[0].isUserOwned)
        Assert.assertEquals(testCharacter.owner, storedPurchase.characters[0].owner)

    }

    @Test
    fun test_insert_event() {
        testPurchase.seller = maxixeDatabase.contactDao().insert(testContact)
        testPurchase.event = maxixeDatabase.eventDao().insert(testEvent)
        val id = maxixeDatabase.purchaseDao().insert(testPurchase)

        val storedPurchase = maxixeDatabase.purchaseDao().select(id)

        Assert.assertEquals(id, storedPurchase.purchase.id)
        Assert.assertEquals(testPurchase.desc, storedPurchase.purchase.desc)
        Assert.assertEquals(testPurchase.cost, storedPurchase.purchase.cost, 0.0)
        Assert.assertEquals(testPurchase.tip, storedPurchase.purchase.tip, 0.0)
        Assert.assertEquals(testPurchase.total, storedPurchase.purchase.total, 0.0)
        Assert.assertEquals(testPurchase.date, storedPurchase.purchase.date)
        Assert.assertEquals(testPurchase.seller, storedPurchase.purchase.seller)
        Assert.assertEquals(testPurchase.event, storedPurchase.purchase.event)

        Assert.assertEquals(testContact.name, storedPurchase.seller.name)
        Assert.assertEquals(testContact.desc, storedPurchase.seller.desc)
        Assert.assertEquals(testContact.pronouns, storedPurchase.seller.pronouns)
        Assert.assertEquals(testContact.socials, storedPurchase.seller.socials)
        Assert.assertEquals(testContact.isUser, storedPurchase.seller.isUser)

        Assert.assertNotNull(storedPurchase.event)
        Assert.assertEquals(testEvent.name, storedPurchase.event!!.name)
        Assert.assertEquals(testEvent.start, storedPurchase.event!!.start)
        Assert.assertEquals(testEvent.end, storedPurchase.event!!.end)
    }

    @Test
    fun test_insert_tag() {
        testPurchase.seller = maxixeDatabase.contactDao().insert(testContact)
        val purchId = maxixeDatabase.purchaseDao().insert(testPurchase)
        val tagId = maxixeDatabase.tagDao().insert(testTag)

        maxixeDatabase.purchaseDao().addTags(PurchaseTag(purchId, tagId))

        val storedPurchase = maxixeDatabase.purchaseDao().select(purchId)

        Assert.assertEquals(purchId, storedPurchase.purchase.id)
        Assert.assertEquals(testPurchase.desc, storedPurchase.purchase.desc)
        Assert.assertEquals(testPurchase.cost, storedPurchase.purchase.cost, 0.0)
        Assert.assertEquals(testPurchase.tip, storedPurchase.purchase.tip, 0.0)
        Assert.assertEquals(testPurchase.total, storedPurchase.purchase.total, 0.0)
        Assert.assertEquals(testPurchase.date, storedPurchase.purchase.date)
        Assert.assertEquals(testPurchase.seller, storedPurchase.purchase.seller)
        Assert.assertEquals(testPurchase.event, storedPurchase.purchase.event)

        Assert.assertEquals(testContact.name, storedPurchase.seller.name)
        Assert.assertEquals(testContact.desc, storedPurchase.seller.desc)
        Assert.assertEquals(testContact.pronouns, storedPurchase.seller.pronouns)
        Assert.assertEquals(testContact.socials, storedPurchase.seller.socials)
        Assert.assertEquals(testContact.isUser, storedPurchase.seller.isUser)

        Assert.assertEquals(1, storedPurchase.tags.size)
        Assert.assertEquals(testTag.name, storedPurchase.tags[0].name)
        Assert.assertEquals(testTag.type, storedPurchase.tags[0].type)
    }

    @Test
    fun test_selectAll(){
        testPurchase.seller = maxixeDatabase.contactDao().insert(testContact)
        val purchId = maxixeDatabase.purchaseDao().insert(testPurchase)

        val storedPurchases = maxixeDatabase.purchaseDao().selectAll()
        Assert.assertEquals(purchId, storedPurchases[0].purchase.id)
        Assert.assertEquals(testPurchase.desc, storedPurchases[0].purchase.desc)
        Assert.assertEquals(testPurchase.cost, storedPurchases[0].purchase.cost, 0.0)
        Assert.assertEquals(testPurchase.tip, storedPurchases[0].purchase.tip, 0.0)
        Assert.assertEquals(testPurchase.total, storedPurchases[0].purchase.total, 0.0)
        Assert.assertEquals(testPurchase.date, storedPurchases[0].purchase.date)
        Assert.assertEquals(testPurchase.seller, storedPurchases[0].purchase.seller)
        Assert.assertEquals(testPurchase.event, storedPurchases[0].purchase.event)
        Assert.assertEquals(testContact.name, storedPurchases[0].seller.name)
        Assert.assertEquals(testContact.desc, storedPurchases[0].seller.desc)
        Assert.assertEquals(testContact.pronouns, storedPurchases[0].seller.pronouns)
        Assert.assertEquals(testContact.socials, storedPurchases[0].seller.socials)
        Assert.assertEquals(testContact.isUser, storedPurchases[0].seller.isUser)
    }

    @Test
    fun test_selectAllByCharacter() {
        testPurchase.seller = maxixeDatabase.contactDao().insert(testContact)
        val purchId = maxixeDatabase.purchaseDao().insert(testPurchase)
        val charId = maxixeDatabase.characterDao().insert(testCharacter)

        maxixeDatabase.purchaseDao().addCharacters(PurchaseCharacter(purchId, charId))

        val storedPurchases = maxixeDatabase.purchaseDao().selectAllByCharacter(charId)

        Assert.assertEquals( 1, storedPurchases.size)
        Assert.assertEquals(purchId, storedPurchases[0].id)
        Assert.assertEquals(testPurchase.desc, storedPurchases[0].desc)
        Assert.assertEquals(testPurchase.cost, storedPurchases[0].cost, 0.0)
        Assert.assertEquals(testPurchase.tip, storedPurchases[0].tip, 0.0)
        Assert.assertEquals(testPurchase.total, storedPurchases[0].total, 0.0)
        Assert.assertEquals(testPurchase.date, storedPurchases[0].date)
        Assert.assertEquals(testPurchase.seller, storedPurchases[0].seller)
        Assert.assertEquals(testPurchase.event, storedPurchases[0].event)
    }

    @Test
    fun test_selectAllByEvent() {
        testPurchase.seller = maxixeDatabase.contactDao().insert(testContact)
        testPurchase.event = maxixeDatabase.eventDao().insert(testEvent)
        val purchId = maxixeDatabase.purchaseDao().insert(testPurchase)

        val storedPurchases = maxixeDatabase.purchaseDao().selectAllByEvent(testPurchase.event!!)

        Assert.assertEquals( 1, storedPurchases.size)
        Assert.assertEquals(purchId, storedPurchases[0].id)
        Assert.assertEquals(testPurchase.desc, storedPurchases[0].desc)
        Assert.assertEquals(testPurchase.cost, storedPurchases[0].cost, 0.0)
        Assert.assertEquals(testPurchase.tip, storedPurchases[0].tip, 0.0)
        Assert.assertEquals(testPurchase.total, storedPurchases[0].total, 0.0)
        Assert.assertEquals(testPurchase.date, storedPurchases[0].date)
        Assert.assertEquals(testPurchase.seller, storedPurchases[0].seller)
        Assert.assertEquals(testPurchase.event, storedPurchases[0].event)
    }

    @Test
    fun test_selectAllBySeller() {
        testPurchase.seller = maxixeDatabase.contactDao().insert(testContact)
        val purchId = maxixeDatabase.purchaseDao().insert(testPurchase)

        val storedPurchases = maxixeDatabase.purchaseDao().selectAllBySeller(testPurchase.seller)

        Assert.assertEquals( 1, storedPurchases.size)
        Assert.assertEquals(purchId, storedPurchases[0].id)
        Assert.assertEquals(testPurchase.desc, storedPurchases[0].desc)
        Assert.assertEquals(testPurchase.cost, storedPurchases[0].cost, 0.0)
        Assert.assertEquals(testPurchase.tip, storedPurchases[0].tip, 0.0)
        Assert.assertEquals(testPurchase.total, storedPurchases[0].total, 0.0)
        Assert.assertEquals(testPurchase.date, storedPurchases[0].date)
        Assert.assertEquals(testPurchase.seller, storedPurchases[0].seller)
        Assert.assertEquals(testPurchase.event, storedPurchases[0].event)
    }

    @Test
    fun test_selectAllByTag() {
        testPurchase.seller = maxixeDatabase.contactDao().insert(testContact)
        val purchId = maxixeDatabase.purchaseDao().insert(testPurchase)
        val tagId = maxixeDatabase.tagDao().insert(testTag)

        maxixeDatabase.purchaseDao().addTags(PurchaseTag(purchId, tagId))

        val storedPurchases = maxixeDatabase.purchaseDao().selectAllByTag(tagId)

        Assert.assertEquals( 1, storedPurchases.size)
        Assert.assertEquals(purchId, storedPurchases[0].id)
        Assert.assertEquals(testPurchase.desc, storedPurchases[0].desc)
        Assert.assertEquals(testPurchase.cost, storedPurchases[0].cost, 0.0)
        Assert.assertEquals(testPurchase.tip, storedPurchases[0].tip, 0.0)
        Assert.assertEquals(testPurchase.total, storedPurchases[0].total, 0.0)
        Assert.assertEquals(testPurchase.date, storedPurchases[0].date)
        Assert.assertEquals(testPurchase.seller, storedPurchases[0].seller)
        Assert.assertEquals(testPurchase.event, storedPurchases[0].event)
    }

}