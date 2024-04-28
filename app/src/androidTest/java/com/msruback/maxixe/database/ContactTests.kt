package com.msruback.maxixe.database

import com.msruback.maxixe.database.entities.ContactTag
import com.msruback.maxixe.database.exampledata.basicContact
import com.msruback.maxixe.database.exampledata.basicTag
import org.junit.Assert
import org.junit.Test

class ContactTests : DatabaseTest() {

    @Test
    fun test_insert() {
        val id = maxixeDatabase.contactDao().insert(basicContact)

        val storedContact = maxixeDatabase.contactDao().select(id)

        Assert.assertEquals(id, storedContact.id)
        Assert.assertEquals(basicContact.name, storedContact.name)
        Assert.assertEquals(basicContact.desc, storedContact.desc)
        Assert.assertEquals(basicContact.pronouns, storedContact.pronouns)
        Assert.assertEquals(basicContact.isUser, storedContact.isUser)
    }


    @Test
    fun test_selectAllByTag(){
        val conId = maxixeDatabase.contactDao().insert(basicContact)
        val tagId = maxixeDatabase.tagDao().insert(basicTag)
        maxixeDatabase.contactDao().addTags(ContactTag(conId, tagId))

        val storedContacts = maxixeDatabase.contactDao().selectAllByTag(tagId)

        Assert.assertEquals( 1, storedContacts.size)
        Assert.assertEquals( conId, storedContacts[0].id)
        Assert.assertEquals(basicContact.name, storedContacts[0].name)
        Assert.assertEquals(basicContact.desc, storedContacts[0].desc)
        Assert.assertEquals(basicContact.pronouns, storedContacts[0].pronouns)
        Assert.assertEquals(basicContact.isUser, storedContacts[0].isUser)
    }
}