package com.msruback.maxixe.database

import com.msruback.maxixe.database.entities.Event
import com.msruback.maxixe.database.entities.EventTag
import com.msruback.maxixe.database.entities.Tag
import org.junit.Assert
import org.junit.Test

class EventTests : DatabaseTest() {
    private val testEvent = Event(0, "TestCon", "Test", 795416400000, 795416400000)
    private val testTag = Tag(0,"Test",4)

    @Test
    fun test_insert(){
        val id = maxixeDatabase.eventDao().insert(testEvent)

        val storedEvent = maxixeDatabase.eventDao().select(id)

        Assert.assertEquals(id, storedEvent.id)
        Assert.assertEquals(testEvent.name, storedEvent.name)
        Assert.assertEquals(testEvent.location, storedEvent.location)
        Assert.assertEquals(testEvent.start, storedEvent.start)
        Assert.assertEquals(testEvent.end, storedEvent.end)
    }

    @Test
    fun test_selectAllByTag(){
        val eventId = maxixeDatabase.eventDao().insert(testEvent)
        val tagId = maxixeDatabase.tagDao().insert(testTag)
        maxixeDatabase.eventDao().addTags(EventTag(eventId, tagId))

        val storedEvents = maxixeDatabase.eventDao().selectAllByTag(tagId)

        Assert.assertEquals(1, storedEvents.size)
        Assert.assertEquals( eventId, storedEvents[0].id)
        Assert.assertEquals(testEvent.name, storedEvents[0].name)
        Assert.assertEquals(testEvent.location, storedEvents[0].location)
        Assert.assertEquals(testEvent.start, storedEvents[0].start)
        Assert.assertEquals(testEvent.end, storedEvents[0].end)
    }
}