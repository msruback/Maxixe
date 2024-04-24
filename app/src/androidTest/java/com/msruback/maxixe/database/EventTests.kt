package com.msruback.maxixe.database

import com.msruback.maxixe.database.entities.EventTag
import com.msruback.maxixe.database.exampledata.basicEvent
import com.msruback.maxixe.database.exampledata.basicTag
import org.junit.Assert
import org.junit.Test

class EventTests : DatabaseTest() {

    @Test
    fun test_insert(){
        val id = maxixeDatabase.eventDao().insert(basicEvent)

        val storedEvent = maxixeDatabase.eventDao().select(id)

        Assert.assertEquals(id, storedEvent.id)
        Assert.assertEquals(basicEvent.name, storedEvent.name)
        Assert.assertEquals(basicEvent.location, storedEvent.location)
        Assert.assertEquals(basicEvent.start, storedEvent.start)
        Assert.assertEquals(basicEvent.end, storedEvent.end)
    }

    @Test
    fun test_selectAllByTag(){
        val eventId = maxixeDatabase.eventDao().insert(basicEvent)
        val tagId = maxixeDatabase.tagDao().insert(basicTag)
        maxixeDatabase.eventDao().addTags(EventTag(eventId, tagId))

        val storedEvents = maxixeDatabase.eventDao().selectAllByTag(tagId)

        Assert.assertEquals(1, storedEvents.size)
        Assert.assertEquals( eventId, storedEvents[0].id)
        Assert.assertEquals(basicEvent.name, storedEvents[0].name)
        Assert.assertEquals(basicEvent.location, storedEvents[0].location)
        Assert.assertEquals(basicEvent.start, storedEvents[0].start)
        Assert.assertEquals(basicEvent.end, storedEvents[0].end)
    }
}