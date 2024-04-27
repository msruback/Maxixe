package com.msruback.maxixe.ui.composables.screens.events

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.msruback.maxixe.database.entities.Event
import com.msruback.maxixe.database.exampledata.basicEvent
import com.msruback.maxixe.ui.ui.theme.MaxixeTheme
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@Composable
fun EventItem(event: Event, navigateToDetail: (Long) -> Unit){
    Row(horizontalArrangement = Arrangement.Start, modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp, 5.dp, 0.dp, 5.dp)
        .clickable { navigateToDetail(event.id) }){
        Text(text = event.name, color = MaterialTheme.colorScheme.onBackground, fontSize = 20.sp)
        Column(verticalArrangement = Arrangement.Center){
            // need check if start date & end date are same, show hour alt layout
            val dateFormat = DateTimeFormatter.ofPattern("MM/dd/yy")
            val startDate = dateFormat.format(
                LocalDateTime.ofEpochSecond(
                    event.start,
                    0,
                    ZoneOffset.UTC
                )
            )
            val endDate = dateFormat.format(
                LocalDateTime.ofEpochSecond(
                    event.end,
                    0,
                    ZoneOffset.UTC
                )
            )
            Text(text = startDate, color = MaterialTheme.colorScheme.onBackground, fontSize = 15.sp)
            Text(text = endDate, color = MaterialTheme.colorScheme.onBackground, fontSize = 15.sp)
        }
    }
}

@Composable
@Preview
private fun LightModePreview() {
    MaxixeTheme {
        Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            EventItem(basicEvent) {}
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun NightModePreview() {
    MaxixeTheme {
        Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            EventItem(basicEvent) {}
        }
    }
}