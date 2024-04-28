package com.msruback.maxixe.database


import com.msruback.maxixe.database.entities.PurchaseCharacter
import com.msruback.maxixe.database.entities.PurchaseTag
import com.msruback.maxixe.database.exampledata.basicCharacter
import com.msruback.maxixe.database.exampledata.basicEvent
import com.msruback.maxixe.database.exampledata.basicPurchase
import com.msruback.maxixe.database.exampledata.basicTag
import com.msruback.maxixe.database.exampledata.purchaseWithSeller
import org.junit.Assert
import org.junit.Test

class PurchaseTests : DatabaseTest() {

    @Test
    fun test_insert(){
        val id = maxixeDatabase.purchaseDao().insert(basicPurchase)

        val storedPurchase = maxixeDatabase.purchaseDao().select(id)

        Assert.assertEquals(id, storedPurchase.purchase.id)
        Assert.assertEquals(basicPurchase.desc, storedPurchase.purchase.desc)
        Assert.assertEquals(basicPurchase.cost, storedPurchase.purchase.cost, 0.0)
        Assert.assertEquals(basicPurchase.tip, storedPurchase.purchase.tip, 0.0)
        Assert.assertEquals(basicPurchase.total, storedPurchase.purchase.total, 0.0)
        Assert.assertEquals(basicPurchase.date, storedPurchase.purchase.date)
        Assert.assertEquals(basicPurchase.seller, storedPurchase.purchase.seller)
        Assert.assertEquals(basicPurchase.event, storedPurchase.purchase.event)
    }
    @Test
    fun test_insert_seller() {
        purchaseWithSeller.purchase.seller = maxixeDatabase.contactDao().insert(purchaseWithSeller.seller!!)
        val id = maxixeDatabase.purchaseDao().insert(purchaseWithSeller.purchase)

        val storedPurchase = maxixeDatabase.purchaseDao().select(id)

        Assert.assertEquals(id, storedPurchase.purchase.id)
        Assert.assertEquals(purchaseWithSeller.purchase.desc, storedPurchase.purchase.desc)
        Assert.assertEquals(purchaseWithSeller.purchase.cost, storedPurchase.purchase.cost, 0.0)
        Assert.assertEquals(purchaseWithSeller.purchase.tip, storedPurchase.purchase.tip, 0.0)
        Assert.assertEquals(purchaseWithSeller.purchase.total, storedPurchase.purchase.total, 0.0)
        Assert.assertEquals(purchaseWithSeller.purchase.date, storedPurchase.purchase.date)
        Assert.assertEquals(purchaseWithSeller.purchase.seller, storedPurchase.purchase.seller)
        Assert.assertEquals(purchaseWithSeller.purchase.event, storedPurchase.purchase.event)

        Assert.assertEquals(purchaseWithSeller.seller!!.name, storedPurchase.seller!!.name)
        Assert.assertEquals(purchaseWithSeller.seller!!.desc, storedPurchase.seller!!.desc)
        Assert.assertEquals(purchaseWithSeller.seller!!.pronouns, storedPurchase.seller!!.pronouns)
        Assert.assertEquals(purchaseWithSeller.seller!!.isUser, storedPurchase.seller!!.isUser)
        purchaseWithSeller.purchase.seller = 0
    }

    @Test
    fun test_insert_character() {
        val purchId = maxixeDatabase.purchaseDao().insert(basicPurchase)
        val charId = maxixeDatabase.characterDao().insert(basicCharacter)

        maxixeDatabase.purchaseDao().addCharacters(PurchaseCharacter(purchId, charId))

        val storedPurchase = maxixeDatabase.purchaseDao().select(purchId)

        Assert.assertEquals(purchId, storedPurchase.purchase.id)
        Assert.assertEquals(basicPurchase.desc, storedPurchase.purchase.desc)
        Assert.assertEquals(basicPurchase.cost, storedPurchase.purchase.cost, 0.0)
        Assert.assertEquals(basicPurchase.tip, storedPurchase.purchase.tip, 0.0)
        Assert.assertEquals(basicPurchase.total, storedPurchase.purchase.total, 0.0)
        Assert.assertEquals(basicPurchase.date, storedPurchase.purchase.date)
        Assert.assertEquals(basicPurchase.seller, storedPurchase.purchase.seller)
        Assert.assertEquals(basicPurchase.event, storedPurchase.purchase.event)

        Assert.assertEquals(1, storedPurchase.characters.size)
        Assert.assertEquals(basicCharacter.name, storedPurchase.characters[0].name)
        Assert.assertEquals(basicCharacter.pronouns, storedPurchase.characters[0].pronouns)
        Assert.assertEquals(basicCharacter.desc, storedPurchase.characters[0].desc)
        Assert.assertEquals(basicCharacter.link, storedPurchase.characters[0].link)
        Assert.assertEquals(basicCharacter.isUserOwned, storedPurchase.characters[0].isUserOwned)
        Assert.assertEquals(basicCharacter.owner, storedPurchase.characters[0].owner)

    }

    @Test
    fun test_insert_event() {
        basicPurchase.event = maxixeDatabase.eventDao().insert(basicEvent)
        val id = maxixeDatabase.purchaseDao().insert(basicPurchase)

        val storedPurchase = maxixeDatabase.purchaseDao().select(id)

        Assert.assertEquals(id, storedPurchase.purchase.id)
        Assert.assertEquals(basicPurchase.desc, storedPurchase.purchase.desc)
        Assert.assertEquals(basicPurchase.cost, storedPurchase.purchase.cost, 0.0)
        Assert.assertEquals(basicPurchase.tip, storedPurchase.purchase.tip, 0.0)
        Assert.assertEquals(basicPurchase.total, storedPurchase.purchase.total, 0.0)
        Assert.assertEquals(basicPurchase.date, storedPurchase.purchase.date)
        Assert.assertEquals(basicPurchase.seller, storedPurchase.purchase.seller)
        Assert.assertEquals(basicPurchase.event, storedPurchase.purchase.event)

        Assert.assertNotNull(storedPurchase.event)
        Assert.assertEquals(basicEvent.name, storedPurchase.event!!.name)
        Assert.assertEquals(basicEvent.start, storedPurchase.event!!.start)
        Assert.assertEquals(basicEvent.end, storedPurchase.event!!.end)
        basicPurchase.event = null
    }

    @Test
    fun test_insert_tag() {
        val purchaseId = maxixeDatabase.purchaseDao().insert(basicPurchase)
        val tagId = maxixeDatabase.tagDao().insert(basicTag)

        maxixeDatabase.purchaseDao().addTags(PurchaseTag(purchaseId, tagId))

        val storedPurchase = maxixeDatabase.purchaseDao().select(purchaseId)

        Assert.assertEquals(purchaseId, storedPurchase.purchase.id)
        Assert.assertEquals(basicPurchase.desc, storedPurchase.purchase.desc)
        Assert.assertEquals(basicPurchase.cost, storedPurchase.purchase.cost, 0.0)
        Assert.assertEquals(basicPurchase.tip, storedPurchase.purchase.tip, 0.0)
        Assert.assertEquals(basicPurchase.total, storedPurchase.purchase.total, 0.0)
        Assert.assertEquals(basicPurchase.date, storedPurchase.purchase.date)
        Assert.assertEquals(basicPurchase.seller, storedPurchase.purchase.seller)
        Assert.assertEquals(basicPurchase.event, storedPurchase.purchase.event)

        Assert.assertEquals(1, storedPurchase.tags.size)
        Assert.assertEquals(basicTag.name, storedPurchase.tags[0].name)
        Assert.assertEquals(basicTag.type, storedPurchase.tags[0].type)
    }

    @Test
    fun test_selectAll(){
        purchaseWithSeller.purchase.seller = maxixeDatabase.contactDao().insert(purchaseWithSeller.seller!!)
        val purchId = maxixeDatabase.purchaseDao().insert(purchaseWithSeller.purchase)

        val storedPurchases = maxixeDatabase.purchaseDao().selectAll()
        Assert.assertEquals(purchId, storedPurchases[0].purchase.id)
        Assert.assertEquals(purchaseWithSeller.purchase.desc, storedPurchases[0].purchase.desc)
        Assert.assertEquals(purchaseWithSeller.purchase.cost, storedPurchases[0].purchase.cost, 0.0)
        Assert.assertEquals(purchaseWithSeller.purchase.tip, storedPurchases[0].purchase.tip, 0.0)
        Assert.assertEquals(purchaseWithSeller.purchase.total, storedPurchases[0].purchase.total, 0.0)
        Assert.assertEquals(purchaseWithSeller.purchase.date, storedPurchases[0].purchase.date)
        Assert.assertEquals(purchaseWithSeller.purchase.seller, storedPurchases[0].purchase.seller)
        Assert.assertEquals(purchaseWithSeller.purchase.event, storedPurchases[0].purchase.event)
        Assert.assertEquals(purchaseWithSeller.seller!!.name, storedPurchases[0].seller!!.name)
        Assert.assertEquals(purchaseWithSeller.seller!!.desc, storedPurchases[0].seller!!.desc)
        Assert.assertEquals(purchaseWithSeller.seller!!.pronouns, storedPurchases[0].seller!!.pronouns)
        Assert.assertEquals(purchaseWithSeller.seller!!.isUser, storedPurchases[0].seller!!.isUser)
        purchaseWithSeller.purchase.seller = 0
    }

    @Test
    fun test_selectAllByCharacter() {
        basicPurchase.event = null
        val purchId = maxixeDatabase.purchaseDao().insert(basicPurchase)
        val charId = maxixeDatabase.characterDao().insert(basicCharacter)

        maxixeDatabase.purchaseDao().addCharacters(PurchaseCharacter(purchId, charId))

        val storedPurchases = maxixeDatabase.purchaseDao().selectAllByCharacter(charId)

        Assert.assertEquals( 1, storedPurchases.size)
        Assert.assertEquals(purchId, storedPurchases[0].id)
        Assert.assertEquals(basicPurchase.desc, storedPurchases[0].desc)
        Assert.assertEquals(basicPurchase.cost, storedPurchases[0].cost, 0.0)
        Assert.assertEquals(basicPurchase.tip, storedPurchases[0].tip, 0.0)
        Assert.assertEquals(basicPurchase.total, storedPurchases[0].total, 0.0)
        Assert.assertEquals(basicPurchase.date, storedPurchases[0].date)
        Assert.assertEquals(basicPurchase.seller, storedPurchases[0].seller)
        Assert.assertEquals(basicPurchase.event, storedPurchases[0].event)
    }

    @Test
    fun test_selectAllByEvent() {
        basicPurchase.event = maxixeDatabase.eventDao().insert(basicEvent)
        val purchId = maxixeDatabase.purchaseDao().insert(basicPurchase)

        val storedPurchases = maxixeDatabase.purchaseDao().selectAllByEvent(basicPurchase.event!!)

        Assert.assertEquals( 1, storedPurchases.size)
        Assert.assertEquals(purchId, storedPurchases[0].id)
        Assert.assertEquals(basicPurchase.desc, storedPurchases[0].desc)
        Assert.assertEquals(basicPurchase.cost, storedPurchases[0].cost, 0.0)
        Assert.assertEquals(basicPurchase.tip, storedPurchases[0].tip, 0.0)
        Assert.assertEquals(basicPurchase.total, storedPurchases[0].total, 0.0)
        Assert.assertEquals(basicPurchase.date, storedPurchases[0].date)
        Assert.assertEquals(basicPurchase.seller, storedPurchases[0].seller)
        Assert.assertEquals(basicPurchase.event, storedPurchases[0].event)
        basicPurchase.event = null
    }

    @Test
    fun test_selectAllBySeller() {
        purchaseWithSeller.purchase.seller = maxixeDatabase.contactDao().insert(purchaseWithSeller.seller!!)
        val purchId = maxixeDatabase.purchaseDao().insert(purchaseWithSeller.purchase)

        val storedPurchases = maxixeDatabase.purchaseDao().selectAllBySeller(purchaseWithSeller.purchase.seller!!)

        Assert.assertEquals( 1, storedPurchases.size)
        Assert.assertEquals(purchId, storedPurchases[0].id)
        Assert.assertEquals(purchaseWithSeller.purchase.desc, storedPurchases[0].desc)
        Assert.assertEquals(purchaseWithSeller.purchase.cost, storedPurchases[0].cost, 0.0)
        Assert.assertEquals(purchaseWithSeller.purchase.tip, storedPurchases[0].tip, 0.0)
        Assert.assertEquals(purchaseWithSeller.purchase.total, storedPurchases[0].total, 0.0)
        Assert.assertEquals(purchaseWithSeller.purchase.date, storedPurchases[0].date)
        Assert.assertEquals(purchaseWithSeller.purchase.seller, storedPurchases[0].seller)
        Assert.assertEquals(purchaseWithSeller.purchase.event, storedPurchases[0].event)
        purchaseWithSeller.purchase.seller = 0
    }

    @Test
    fun test_selectAllByTag() {
        val purchId = maxixeDatabase.purchaseDao().insert(basicPurchase)
        val tagId = maxixeDatabase.tagDao().insert(basicTag)

        maxixeDatabase.purchaseDao().addTags(PurchaseTag(purchId, tagId))

        val storedPurchases = maxixeDatabase.purchaseDao().selectAllByTag(tagId)

        Assert.assertEquals( 1, storedPurchases.size)
        Assert.assertEquals(purchId, storedPurchases[0].id)
        Assert.assertEquals(basicPurchase.desc, storedPurchases[0].desc)
        Assert.assertEquals(basicPurchase.cost, storedPurchases[0].cost, 0.0)
        Assert.assertEquals(basicPurchase.tip, storedPurchases[0].tip, 0.0)
        Assert.assertEquals(basicPurchase.total, storedPurchases[0].total, 0.0)
        Assert.assertEquals(basicPurchase.date, storedPurchases[0].date)
        Assert.assertEquals(basicPurchase.seller, storedPurchases[0].seller)
        Assert.assertEquals(basicPurchase.event, storedPurchases[0].event)
    }

}