package com.msruback.maxixe.ui

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.msruback.maxixe.database.exampledata.basicEvent
import com.msruback.maxixe.ui.composables.screens.events.EventsList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.junit.Rule
import org.junit.Test
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class EventsListScreenTests {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_display_events_provided() {
        composeTestRule.setContent {
            val events = MutableStateFlow(
                PagingData.from(
                    listOf(
                        basicEvent,
                        basicEvent,
                        basicEvent
                    )
                )
            ).asStateFlow().collectAsLazyPagingItems()
            EventsList(events = events, navigateToDetail = {})
        }
        val dateFormat = DateTimeFormatter.ofPattern("MM/dd")
        val startDate = dateFormat.format(
            LocalDateTime.ofEpochSecond(
                basicEvent.start,
                0,
                ZoneOffset.UTC
            )
        )
        val endDate = dateFormat.format(
            LocalDateTime.ofEpochSecond(
                basicEvent.end,
                0,
                ZoneOffset.UTC
            )
        )
        composeTestRule.onNodeWithTag("events-list").onChildren().assertCountEquals(3).onFirst()
            .assertTextEquals(basicEvent.name, basicEvent.byline, startDate, " - ", endDate)
    }

//    @Test
//    fun test_display_events_stored(){
//        composeTestRule.setContent {
//            val viewModel = viewModel<EventsViewModel>()
//            viewModel.insertEvent(basicEvent)
//            EventsListScreen{}
//        }
//        val dateFormat = DateTimeFormatter.ofPattern("MM/dd")
//        val startDate = dateFormat.format(
//            LocalDateTime.ofEpochSecond(
//                basicEvent.start,
//                0,
//                ZoneOffset.UTC
//            )
//        )
//        val endDate = dateFormat.format(
//            LocalDateTime.ofEpochSecond(
//                basicEvent.end,
//                0,
//                ZoneOffset.UTC
//            )
//        )
//        composeTestRule.onNodeWithTag("events-list").onChildren().assertCountEquals(1).onFirst()
//            .assertTextEquals(basicEvent.name, basicEvent.byline, startDate, " - ", endDate)
//    }
}