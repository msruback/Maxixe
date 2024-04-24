package com.msruback.maxixe.ui

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.msruback.maxixe.database.exampledata.basicPurchase
import com.msruback.maxixe.database.exampledata.briefPurchase
import com.msruback.maxixe.database.exampledata.longPurchase
import com.msruback.maxixe.database.exampledata.ellipsePurchase
import com.msruback.maxixe.ui.composables.screens.purchases.PurchasesList
import com.msruback.maxixe.ui.composables.screens.purchases.PurchasesListScreen
import com.msruback.maxixe.viewmodels.PurchasesViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.junit.Rule
import org.junit.Test
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class PurchaseListScreen {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_display_purchases_provided() {
        composeTestRule.setContent {
            val purchases = MutableStateFlow(
                PagingData.from(
                    listOf(
                        briefPurchase,
                        longPurchase,
                        ellipsePurchase
                    )
                )
            ).asStateFlow().collectAsLazyPagingItems()
            PurchasesList(purchases = purchases, navigateToDetail = {})
        }

        val dateFormat = DateTimeFormatter.ofPattern("MM/dd/yy")
        val date = dateFormat.format(
            LocalDateTime.ofEpochSecond(
                briefPurchase.purchase.date,
                0,
                ZoneOffset.UTC
            )
        )
        composeTestRule.onNodeWithTag("purchases-list").onChildren().assertCountEquals(3).onFirst()
            .assertTextEquals(briefPurchase.purchase.desc,date,String.format("$%.2f", briefPurchase.purchase.total), briefPurchase.seller!!.name)
    }

    @Test
    fun test_display_purchases_stored(){
        composeTestRule.setContent {
            val viewModel = viewModel<PurchasesViewModel>()
            val insert = viewModel.insertPurchase(basicPurchase)
            PurchasesListScreen{}
        }
        val dateFormat = DateTimeFormatter.ofPattern("MM/dd/yy")
        val date = dateFormat.format(
            LocalDateTime.ofEpochSecond(
                basicPurchase.date,
                0,
                ZoneOffset.UTC
            )
        )
        composeTestRule.onNodeWithTag("purchases-list").onChildren().assertCountEquals(1).onFirst()
            .assertTextEquals(basicPurchase.desc,date,String.format("$%.2f", basicPurchase.total))
    }
}