package com.msruback.maxixe.ui.composables.screens.purchases

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.msruback.maxixe.R
import com.msruback.maxixe.database.queries.PurchaseSellerEvent
import com.msruback.maxixe.ui.composables.appbar.AddActionButton
import com.msruback.maxixe.ui.composables.appbar.FilterButton
import com.msruback.maxixe.ui.composables.appbar.MaxixeScaffold
import com.msruback.maxixe.ui.composables.appbar.MenuButton
import com.msruback.maxixe.database.exampledata.briefPurchase
import com.msruback.maxixe.database.exampledata.ellipsePurchase
import com.msruback.maxixe.database.exampledata.longPurchase
import com.msruback.maxixe.ui.composables.screens.Screen
import com.msruback.maxixe.ui.composables.screens.ScreenInfo
import com.msruback.maxixe.ui.ui.theme.MaxixeTheme
import com.msruback.maxixe.viewmodels.PurchasesViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun PurchasesListScreen(navigateToDetail: (Long) -> Unit) {
    val viewModel = viewModel<PurchasesViewModel>()
    val response = viewModel.purchases.collectAsLazyPagingItems()
    PurchasesList(response, navigateToDetail)
}

@Composable
fun PurchasesList(purchases: LazyPagingItems<PurchaseSellerEvent>, navigateToDetail: (Long) -> Unit) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(1f).testTag("purchases-list")
    ) {
        items(purchases.itemCount){index ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(.9f)
            ) {
                if (index == 0) {
                    Spacer(modifier = Modifier.height(15.dp))
                }
                PurchaseItem(purchases[index]!!, navigateToDetail)
                if (index < purchases.itemCount - 1) {
                    HorizontalDivider(color = MaterialTheme.colorScheme.onBackground)
                }
            }
        }
        purchases.apply {
            when{
                loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading -> {
                }
            }
        }
    }
}

@Composable
@Preview
private fun LightModePreview() {
    val purchases = MutableStateFlow(PagingData.from(listOf(
        briefPurchase,
        longPurchase,
        ellipsePurchase
    ))).asStateFlow().collectAsLazyPagingItems()
    val screenBarInfo = PurchasesListScreenInfo.getInfo({}){}
    MaxixeTheme {
        MaxixeScaffold(
            screenBarInfo.hasFab,
            screenBarInfo.fabPosition,
            { AddActionButton(""){} },
            screenBarInfo.buttons) {
            PurchasesList(
                purchases = purchases
            ) {}
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun NightModePreview() {
    val purchases = MutableStateFlow(PagingData.from(listOf(
        briefPurchase,
        longPurchase,
        ellipsePurchase
    ))).asStateFlow().collectAsLazyPagingItems()
    val screenBarInfo = PurchasesListScreenInfo.getInfo({}){}
    MaxixeTheme {
        MaxixeScaffold(
            screenBarInfo.hasFab,
            screenBarInfo.fabPosition,
            { AddActionButton(""){} },
            screenBarInfo.buttons) {
            PurchasesList(
                purchases = purchases
            ) {}
        }
    }
}

val PurchasesListScreenInfo = object : Screen() {
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
