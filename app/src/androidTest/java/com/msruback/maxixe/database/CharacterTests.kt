package com.msruback.maxixe.database

import com.msruback.maxixe.database.entities.Character
import com.msruback.maxixe.database.entities.CharacterTag
import com.msruback.maxixe.database.entities.Contact
import com.msruback.maxixe.database.entities.Purchase
import com.msruback.maxixe.database.entities.PurchaseCharacter
import com.msruback.maxixe.database.entities.Socials
import com.msruback.maxixe.database.entities.Tag
import org.junit.Assert
import org.junit.Test

class CharacterTests : DatabaseTest() {
    private val testContact = Contact(
        0,
        "Test",
        "They/Them",
        "A Test User",
        Socials("example.com", "twitter.com", "bsky.app", "tumblr.com", "instagram.com")
    )
    private var testCharacter = Character(
        0,
        "Maxixe",
        "They/Them",
        "Your Fandom Assistant",
        "www.example.com"
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
    private val testTag = Tag(0,"Test",2)

    @Test
    fun test_insert() {
        val charId = maxixeDatabase.characterDao().insert(testCharacter)

        val storedCharacter = maxixeDatabase.characterDao().select(charId)

        Assert.assertEquals(charId, storedCharacter.id)
        Assert.assertEquals(testCharacter.name, storedCharacter.name)
        Assert.assertEquals(testCharacter.pronouns, storedCharacter.pronouns)
        Assert.assertEquals(testCharacter.desc, storedCharacter.desc)
        Assert.assertEquals(testCharacter.link, storedCharacter.link)
        Assert.assertEquals(testCharacter.owner, storedCharacter.owner)
        Assert.assertEquals(testCharacter.isUserOwned, storedCharacter.isUserOwned)
    }
    @Test
    fun test_insert_owned() {
        // Insert owner
        testCharacter.owner = maxixeDatabase.contactDao().insert(testContact)
        val charId = maxixeDatabase.characterDao().insert(testCharacter)

        val storedCharacter = maxixeDatabase.characterDao().select(charId)

        Assert.assertEquals(charId, storedCharacter.id)
        Assert.assertEquals(testCharacter.name, storedCharacter.name)
        Assert.assertEquals(testCharacter.pronouns, storedCharacter.pronouns)
        Assert.assertEquals(testCharacter.desc, storedCharacter.desc)
        Assert.assertEquals(testCharacter.link, storedCharacter.link)
        Assert.assertEquals(testCharacter.owner, storedCharacter.owner)
        Assert.assertEquals(testCharacter.isUserOwned, storedCharacter.isUserOwned)
    }

    @Test
    fun test_selectAllByOwner() {
        testCharacter.owner = maxixeDatabase.contactDao().insert(testContact)
        val charId = maxixeDatabase.characterDao().insert(testCharacter)

        val storedCharacters = maxixeDatabase.characterDao().selectAllByOwner(testCharacter.owner!!)

        Assert.assertEquals( 1, storedCharacters.size)
        Assert.assertEquals( charId, storedCharacters[0].id)
        Assert.assertEquals( testCharacter.name, storedCharacters[0].name)
        Assert.assertEquals(testCharacter.pronouns, storedCharacters[0].pronouns)
        Assert.assertEquals(testCharacter.desc, storedCharacters[0].desc)
        Assert.assertEquals(testCharacter.link, storedCharacters[0].link)
        Assert.assertEquals(testCharacter.owner, storedCharacters[0].owner)
        Assert.assertEquals(testCharacter.isUserOwned, storedCharacters[0].isUserOwned)
    }

    @Test
    fun test_selectAllByPurchase(){
        testPurchase.seller = maxixeDatabase.contactDao().insert(testContact)
        val purchId = maxixeDatabase.purchaseDao().insert(testPurchase)
        val charId = maxixeDatabase.characterDao().insert(testCharacter)

        maxixeDatabase.purchaseDao().addCharacters(PurchaseCharacter(purchId, charId))

        val storedCharacters = maxixeDatabase.characterDao().selectAllByPurchase(purchId)

        Assert.assertEquals( 1, storedCharacters.size)
        Assert.assertEquals( charId, storedCharacters[0].id)
        Assert.assertEquals( testCharacter.name, storedCharacters[0].name)
        Assert.assertEquals(testCharacter.pronouns, storedCharacters[0].pronouns)
        Assert.assertEquals(testCharacter.desc, storedCharacters[0].desc)
        Assert.assertEquals(testCharacter.link, storedCharacters[0].link)
        Assert.assertEquals(testCharacter.owner, storedCharacters[0].owner)
        Assert.assertEquals(testCharacter.isUserOwned, storedCharacters[0].isUserOwned)
    }

    @Test
    fun test_selectAllByTag(){
        testCharacter.owner = maxixeDatabase.contactDao().insert(testContact)
        val charId = maxixeDatabase.characterDao().insert(testCharacter)
        val tagId = maxixeDatabase.tagDao().insert(testTag)
        maxixeDatabase.characterDao().addTags(CharacterTag(charId, tagId))

        val storedCharacters = maxixeDatabase.characterDao().selectAllByTag(tagId)

        Assert.assertEquals( 1, storedCharacters.size)
        Assert.assertEquals( charId, storedCharacters[0].id)
        Assert.assertEquals( testCharacter.name, storedCharacters[0].name)
        Assert.assertEquals(testCharacter.pronouns, storedCharacters[0].pronouns)
        Assert.assertEquals(testCharacter.desc, storedCharacters[0].desc)
        Assert.assertEquals(testCharacter.link, storedCharacters[0].link)
        Assert.assertEquals(testCharacter.owner, storedCharacters[0].owner)
        Assert.assertEquals(testCharacter.isUserOwned, storedCharacters[0].isUserOwned)
    }
}