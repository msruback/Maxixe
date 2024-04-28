package com.msruback.maxixe.ui.composables.screens.events

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FabPosition
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.msruback.maxixe.R
import com.msruback.maxixe.database.entities.Event
import com.msruback.maxixe.database.exampledata.basicEvent
import com.msruback.maxixe.ui.composables.appbar.AddActionButton
import com.msruback.maxixe.ui.composables.appbar.FilterButton
import com.msruback.maxixe.ui.composables.appbar.MaxixeScaffold
import com.msruback.maxixe.ui.composables.appbar.MenuButton
import com.msruback.maxixe.ui.composables.screens.Screen
import com.msruback.maxixe.ui.composables.screens.ScreenInfo
import com.msruback.maxixe.ui.ui.theme.MaxixeTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun EventsListScreen(navigateToDetail: (Long) -> Unit){
    
}

@Composable
fun EventsList(events: LazyPagingItems<Event>, navigateToDetail: (Long) -> Unit){
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(1f).testTag("events-list")
    ) {
        items(events.itemCount) { index ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(.9f)
            ) {
                if (index == 0) {
                    Spacer(modifier = Modifier.height(15.dp))
                }
                EventItem(events[index]!!, navigateToDetail)
                if (index < events.itemCount - 1) {
                    HorizontalDivider(color = MaterialTheme.colorScheme.onBackground)
                }
            }
        }
    }
}

@Composable
@Preview
private fun LightModePreview() {
    val events = MutableStateFlow(
        PagingData.from(listOf(
            basicEvent,
            basicEvent,
            basicEvent
        ))).asStateFlow().collectAsLazyPagingItems()
    val screenBarInfo = EventsListScreenInfo.getInfo({}){}
    MaxixeTheme {
        MaxixeScaffold(
            screenBarInfo.hasFab,
            screenBarInfo.fabPosition,
            { AddActionButton(""){} },
            screenBarInfo.buttons) {
            EventsList(
                events = events
            ) {}
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun NightModePreview() {
    val events = MutableStateFlow(
        PagingData.from(listOf(
            basicEvent,
            basicEvent,
            basicEvent
        ))).asStateFlow().collectAsLazyPagingItems()
    val screenBarInfo = EventsListScreenInfo.getInfo({}){}
    MaxixeTheme {
        MaxixeScaffold(
            screenBarInfo.hasFab,
            screenBarInfo.fabPosition,
            { AddActionButton(""){} },
            screenBarInfo.buttons) {
            EventsList(
                events = events
            ) {}
        }
    }
}

val EventsListScreenInfo = object : Screen() {
    override fun routeMatch(route: String): Boolean = (route == "events")
    override fun getInfo(navigate: (String) -> Unit, toggleDrawer: () -> Unit): ScreenInfo {
        return object : ScreenInfo() {
            override val hasFab: Boolean = true
            override val fabPosition: FabPosition = FabPosition.Center
            override val fab: @Composable (() -> Unit) = {
                AddActionButton(stringResource(R.string.add_event)) {
                    navigate("event/add")
                }
            }
            override val buttons: @Composable (RowScope.() -> Unit) = {
                MenuButton(toggleDrawer)
                Spacer(Modifier.weight(1f, true))
                FilterButton(stringResource(R.string.filter_events)) {}
            }
        }
    }
}
