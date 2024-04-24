package com.msruback.maxixe.ui.composables.screens.purchases

import android.content.res.Configuration
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FabPosition
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.msruback.maxixe.R
import com.msruback.maxixe.ui.composables.appbar.DoneActionButton
import com.msruback.maxixe.ui.composables.appbar.MaxixeScaffold
import com.msruback.maxixe.ui.composables.appbar.MenuButton
import com.msruback.maxixe.ui.composables.screens.Screen
import com.msruback.maxixe.ui.composables.screens.ScreenInfo
import com.msruback.maxixe.ui.ui.theme.MaxixeTheme
import com.msruback.maxixe.viewmodels.PurchasesViewModel

@Composable
fun AddEditPurchaseScreen(){
    LazyColumn {

    }
}

@Composable
@Preview
private fun LightModePreview(){
    val screenBarInfo = AddEditPurchaseScreenInfo.getInfo({}){}
    MaxixeTheme {
        MaxixeScaffold(
            screenBarInfo.hasFab,
            screenBarInfo.fabPosition,
            { DoneActionButton(""){} },
            screenBarInfo.buttons) {
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun NightModePreview(){
    val screenBarInfo = AddEditPurchaseScreenInfo.getInfo({}){}
    MaxixeTheme {
        MaxixeScaffold(
            screenBarInfo.hasFab,
            screenBarInfo.fabPosition,
            { DoneActionButton(""){} },
            screenBarInfo.buttons) {
        }
    }
}

val AddEditPurchaseScreenInfo = object : Screen(){
    override fun routeMatch(route: String): Boolean = (route == "purchase/add" || route.contains("purchase/edit/\\d"))
    override fun getInfo(navigate: (String) -> Unit, toggleDrawer: () -> Unit): ScreenInfo {
        return object : ScreenInfo() {
            override val hasFab: Boolean = true
            override val fabPosition: FabPosition = FabPosition.End
            override val fab: @Composable() (() -> Unit) = {
                val viewModel = viewModel<PurchasesViewModel>()
                DoneActionButton(stringResource(R.string.save_purchase)) {
                    navigate("purchases")
                }
            }
            override val buttons: @Composable() (RowScope.() -> Unit) = {
                MenuButton(toggleDrawer)
            }
        }
    }
}
