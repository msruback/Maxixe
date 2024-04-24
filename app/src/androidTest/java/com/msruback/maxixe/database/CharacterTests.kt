package com.msruback.maxixe.database

import com.msruback.maxixe.database.entities.CharacterTag
import com.msruback.maxixe.database.entities.PurchaseCharacter
import com.msruback.maxixe.database.exampledata.basicCharacter
import com.msruback.maxixe.database.exampledata.basicContact
import com.msruback.maxixe.database.exampledata.basicPurchase
import com.msruback.maxixe.database.exampledata.basicTag
import org.junit.Assert
import org.junit.Test

class CharacterTests : DatabaseTest() {

    @Test
    fun test_insert() {
        val charId = maxixeDatabase.characterDao().insert(basicCharacter)

        val storedCharacter = maxixeDatabase.characterDao().select(charId)

        Assert.assertEquals(charId, storedCharacter.id)
        Assert.assertEquals(basicCharacter.name, storedCharacter.name)
        Assert.assertEquals(basicCharacter.pronouns, storedCharacter.pronouns)
        Assert.assertEquals(basicCharacter.desc, storedCharacter.desc)
        Assert.assertEquals(basicCharacter.link, storedCharacter.link)
        Assert.assertEquals(basicCharacter.owner, storedCharacter.owner)
        Assert.assertEquals(basicCharacter.isUserOwned, storedCharacter.isUserOwned)
    }
    @Test
    fun test_insert_owned() {
        // Insert owner
        basicCharacter.owner = maxixeDatabase.contactDao().insert(basicContact)
        val charId = maxixeDatabase.characterDao().insert(basicCharacter)

        val storedCharacter = maxixeDatabase.characterDao().select(charId)

        Assert.assertEquals(charId, storedCharacter.id)
        Assert.assertEquals(basicCharacter.name, storedCharacter.name)
        Assert.assertEquals(basicCharacter.pronouns, storedCharacter.pronouns)
        Assert.assertEquals(basicCharacter.desc, storedCharacter.desc)
        Assert.assertEquals(basicCharacter.link, storedCharacter.link)
        Assert.assertEquals(basicCharacter.owner, storedCharacter.owner)
        Assert.assertEquals(basicCharacter.isUserOwned, storedCharacter.isUserOwned)
        basicCharacter.owner = null
    }

    @Test
    fun test_selectAllByOwner() {
        basicCharacter.owner = maxixeDatabase.contactDao().insert(basicContact)
        val charId = maxixeDatabase.characterDao().insert(basicCharacter)

        val storedCharacters = maxixeDatabase.characterDao().selectAllByOwner(basicCharacter.owner!!)

        Assert.assertEquals( 1, storedCharacters.size)
        Assert.assertEquals( charId, storedCharacters[0].id)
        Assert.assertEquals(basicCharacter.name, storedCharacters[0].name)
        Assert.assertEquals(basicCharacter.pronouns, storedCharacters[0].pronouns)
        Assert.assertEquals(basicCharacter.desc, storedCharacters[0].desc)
        Assert.assertEquals(basicCharacter.link, storedCharacters[0].link)
        Assert.assertEquals(basicCharacter.owner, storedCharacters[0].owner)
        Assert.assertEquals(basicCharacter.isUserOwned, storedCharacters[0].isUserOwned)
        basicCharacter.owner = null
    }

    @Test
    fun test_selectAllByPurchase(){
        val purchId = maxixeDatabase.purchaseDao().insert(basicPurchase)
        val charId = maxixeDatabase.characterDao().insert(basicCharacter)

        maxixeDatabase.purchaseDao().addCharacters(PurchaseCharacter(purchId, charId))

        val storedCharacters = maxixeDatabase.characterDao().selectAllByPurchase(purchId)

        Assert.assertEquals( 1, storedCharacters.size)
        Assert.assertEquals( charId, storedCharacters[0].id)
        Assert.assertEquals(basicCharacter.name, storedCharacters[0].name)
        Assert.assertEquals(basicCharacter.pronouns, storedCharacters[0].pronouns)
        Assert.assertEquals(basicCharacter.desc, storedCharacters[0].desc)
        Assert.assertEquals(basicCharacter.link, storedCharacters[0].link)
        Assert.assertEquals(basicCharacter.owner, storedCharacters[0].owner)
        Assert.assertEquals(basicCharacter.isUserOwned, storedCharacters[0].isUserOwned)
    }

    @Test
    fun test_selectAllByTag(){
        val charId = maxixeDatabase.characterDao().insert(basicCharacter)
        val tagId = maxixeDatabase.tagDao().insert(basicTag)
        maxixeDatabase.characterDao().addTags(CharacterTag(charId, tagId))

        val storedCharacters = maxixeDatabase.characterDao().selectAllByTag(tagId)

        Assert.assertEquals( 1, storedCharacters.size)
        Assert.assertEquals(charId, storedCharacters[0].id)
        Assert.assertEquals(basicCharacter.name, storedCharacters[0].name)
        Assert.assertEquals(basicCharacter.pronouns, storedCharacters[0].pronouns)
        Assert.assertEquals(basicCharacter.desc, storedCharacters[0].desc)
        Assert.assertEquals(basicCharacter.link, storedCharacters[0].link)
        Assert.assertEquals(basicCharacter.owner, storedCharacters[0].owner)
        Assert.assertEquals(basicCharacter.isUserOwned, storedCharacters[0].isUserOwned)
    }
}