package com.msruback.maxixe.ui.composables.screens.tags

import com.msruback.maxixe.database.entities.Tag
import com.msruback.maxixe.database.exampledata.basicTag

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.msruback.maxixe.R
import com.msruback.maxixe.ui.ui.theme.MaxixeTheme

@Composable
fun TagItem(tag: Tag, navigateToDetail: (Long) -> Unit){
    Row(horizontalArrangement = Arrangement.Start, modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp, 5.dp, 0.dp, 5.dp)
        .clickable { navigateToDetail(tag.id) }){
        val icon: Painter = when(tag.type){
            1 -> painterResource(R.drawable.purchases)
            2 -> painterResource(R.drawable.characters)
            3 -> painterResource(R.drawable.contacts)
            4 -> painterResource(R.drawable.events)
            else -> painterResource(R.drawable.label)
        }
        Icon(painter = icon, contentDescription = null, tint = MaterialTheme.colorScheme.onBackground, modifier = Modifier
            .width(50.dp)
            .height(50.dp))
        Column(verticalArrangement = Arrangement.Center, modifier = Modifier.height(50.dp)){
            Text(text = tag.name, color = MaterialTheme.colorScheme.onBackground, fontSize = 30.sp)
        }
    }
}

@Composable
@Preview
private fun LightModePreview() {
    MaxixeTheme {
        Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            TagItem(basicTag) {}
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun NightModePreview() {
    MaxixeTheme {
        Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            TagItem(basicTag) {}
        }
    }
}