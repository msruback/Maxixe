package com.msruback.maxixe.ui.composables.screens.purchases

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.FabPosition
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.msruback.maxixe.R
import com.msruback.maxixe.database.queries.PurchaseWithSeller
import com.msruback.maxixe.ui.MaxixeScreen
import com.msruback.maxixe.ui.composables.appbar.AddActionButton
import com.msruback.maxixe.ui.composables.appbar.FilterButton
import com.msruback.maxixe.ui.composables.appbar.MenuButton
import com.msruback.maxixe.ui.composables.briefPurchase
import com.msruback.maxixe.ui.composables.ellipsePurchase
import com.msruback.maxixe.ui.composables.longPurchase
import com.msruback.maxixe.ui.composables.screens.Screen
import com.msruback.maxixe.ui.composables.screens.ScreenInfo
import com.msruback.maxixe.viewmodels.PurchasesViewModel

@Composable
fun PurchasesListScreen(navigateToDetail: (Long) -> Unit) {
    val viewModel = viewModel<PurchasesViewModel>()
    PurchasesList(viewModel.purchases, navigateToDetail)
}

@Composable
fun PurchasesList(purchases: List<PurchaseWithSeller>, navigateToDetail: (Long) -> Unit) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(1f)
    ) {
        itemsIndexed(purchases) { index, purchase ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(.9f)
            ) {
                if (index == 0) {
                    Spacer(modifier = Modifier.height(15.dp))
                }
                PurchaseItem(purchase, navigateToDetail)
                if (index < purchases.size - 1) {
                    Divider(color = MaterialTheme.colorScheme.onBackground)
                }
            }
        }
    }
}

@Composable
@Preview
private fun LightModePreview() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "purchases") {
        composable("purchases") {}
    }
    MaxixeScreen(navController) {
        PurchasesList(
            purchases = listOf(
                briefPurchase,
                longPurchase,
                ellipsePurchase
            )
        ) {}
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun NightModePreview() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "purchases") {
        composable("purchases") {}
    }
    MaxixeScreen(navController) {
        PurchasesList(
            purchases = listOf(
                briefPurchase,
                longPurchase,
                ellipsePurchase
            )
        ) {}
    }
}

val PurchaseListScreenInfo = object : Screen() {
    override fun routeMatch(route: String): Boolean = (route == "purchases")
    override fun getInfo(navigate: (String) -> Unit, toggleDrawer: () -> Unit): ScreenInfo {
        return object : ScreenInfo() {
            override val hasFab: Boolean = true
            override val fabPosition: FabPosition = FabPosition.Center
            override val fab: @Composable (() -> Unit) = {
                AddActionButton(stringResource(R.string.add_purchase)) {
                    navigate("purchase/add")
                }
            }
            override val buttons: @Composable (RowScope.() -> Unit) = {
                MenuButton(toggleDrawer)
                Spacer(Modifier.weight(1f, true))
                FilterButton(stringResource(R.string.filter_purchases)) {}
            }
        }
    }
}
