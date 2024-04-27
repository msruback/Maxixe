package com.msruback.maxixe.ui.composables.screens.contacts

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.msruback.maxixe.database.entities.Contact
import com.msruback.maxixe.database.exampledata.basicContact
import com.msruback.maxixe.ui.ui.theme.MaxixeTheme

@Composable
fun ContactItem(contact: Contact, navigateToDetail: (Long) -> Unit){
    Row(horizontalArrangement = Arrangement.Start, modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp, 5.dp, 0.dp, 5.dp)
        .clickable { navigateToDetail(contact.id) }){
        Column(verticalArrangement = Arrangement.Center, modifier = Modifier.height(100.dp)){
            Text(text = contact.name, color = MaterialTheme.colorScheme.onBackground, fontSize = 30.sp)
            Text(text = contact.pronouns, color = MaterialTheme.colorScheme.onBackground, fontSize = 20.sp)
        }
    }
}

@Composable
@Preview
private fun LightModePreview() {
    MaxixeTheme {
        Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            ContactItem(basicContact) {}
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun NightModePreview() {
    MaxixeTheme {
        Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            ContactItem(basicContact) {}
        }
    }
}