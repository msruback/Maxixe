package com.msruback.maxixe.ui.composables.screens.purchases

import android.content.res.Configuration
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FabPosition
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.msruback.maxixe.R
import com.msruback.maxixe.database.queries.EverythingPurchase
import com.msruback.maxixe.ui.composables.appbar.EditActionButton
import com.msruback.maxixe.ui.composables.appbar.MaxixeScaffold
import com.msruback.maxixe.ui.composables.appbar.MenuButton
import com.msruback.maxixe.database.exampledata.detailedPurchase
import com.msruback.maxixe.ui.composables.screens.Screen
import com.msruback.maxixe.ui.composables.screens.ScreenInfo
import com.msruback.maxixe.ui.ui.theme.MaxixeTheme

@Composable
fun PurchaseScreen(){
    val id:Long = 0
    LazyColumn{

    }
}

@Composable
fun PurchaseDetail(purchase: EverythingPurchase){
    LazyColumn{
        item {
            Text(text = purchase.purchase.desc)
        }
    }
}

@Composable
@Preview
private fun LightModePreview(){
    val screenBarInfo = PurchaseScreenInfo.getInfo({}){}
    MaxixeTheme {
        MaxixeScaffold(
            screenBarInfo.hasFab,
            screenBarInfo.fabPosition,
            { EditActionButton(""){} },
            screenBarInfo.buttons) {
            PurchaseDetail(detailedPurchase)
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, device = "id:pixel_5")
private fun NightModePreview(){
    val screenBarInfo = PurchaseScreenInfo.getInfo({}){}
    MaxixeTheme {
        MaxixeScaffold(
            screenBarInfo.hasFab,
            screenBarInfo.fabPosition,
            { EditActionButton(""){} },
            screenBarInfo.buttons) {
            PurchaseDetail(detailedPurchase)
        }
    }
}

val PurchaseScreenInfo = object : Screen(){
    override fun routeMatch(route: String): Boolean = route.contains("purchase/\\d")
    override fun getInfo(navigate: (String) -> Unit, toggleDrawer: () -> Unit): ScreenInfo {
        return object : ScreenInfo() {
            override val hasFab: Boolean = true
            override val fabPosition: FabPosition = FabPosition.End
            override val fab: @Composable() (() -> Unit) = { EditActionButton(stringResource(R.string.edit_purchase)){} }
            override val buttons: @Composable() (RowScope.() -> Unit) = {
                MenuButton(toggleDrawer)
            }
        }
    }
}
