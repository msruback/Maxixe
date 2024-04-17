package com.msruback.maxixe.ui.composables.screens.purchases

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msruback.maxixe.database.queries.PurchaseWithSeller
import com.msruback.maxixe.ui.composables.briefPurchase
import com.msruback.maxixe.ui.ui.theme.MaxixeTheme
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@Composable
fun PurchaseItem(purchase: PurchaseWithSeller, navigateToDetail: (Long) -> Unit){
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier
        .fillMaxWidth()
        .padding(0.dp, 5.dp, 0.dp, 5.dp)
        .clickable { navigateToDetail(purchase.purchase.id) }){
        Text(text = purchase.purchase.desc, color = MaterialTheme.colorScheme.onBackground, overflow = TextOverflow.Ellipsis, modifier = Modifier
            .fillMaxWidth(.7f)
            .height(70.dp)
            .padding(0.dp, 0.dp, 10.dp, 0.dp))
        Column(modifier = Modifier.fillMaxWidth(.7f)){
            val dateFormat = DateTimeFormatter.ofPattern("MM/dd/yy")
            val date = dateFormat.format(LocalDateTime.ofEpochSecond(purchase.purchase.date,0, ZoneOffset.UTC))
            Text(text = date, color = MaterialTheme.colorScheme.onBackground, textAlign = TextAlign.End, modifier = Modifier.fillMaxWidth())
            Text(text = String.format("$%.2f",purchase.purchase.total), color = MaterialTheme.colorScheme.onBackground, textAlign = TextAlign.End, modifier = Modifier.fillMaxWidth())
            Text(text = purchase.seller.name, color = MaterialTheme.colorScheme.onBackground, textAlign = TextAlign.End, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
@Preview
private fun LightModePreview() {
    MaxixeTheme {
        Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            PurchaseItem(purchase = briefPurchase) {}
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun NightModePreview() {
    MaxixeTheme {
        Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            PurchaseItem(purchase = briefPurchase) {}
        }
    }
}
