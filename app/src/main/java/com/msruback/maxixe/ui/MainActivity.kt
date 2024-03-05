package com.msruback.maxixe.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.BottomDrawerValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.msruback.maxixe.ui.composables.MaxixeBottomDrawer
import com.msruback.maxixe.ui.composables.screens.purchases.PurchaseScreen
import com.msruback.maxixe.ui.composables.screens.purchases.PurchasesListScreen
import com.msruback.maxixe.ui.ui.theme.MaxixeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaxixeTheme {
                val navController = rememberNavController()
                val drawerState = rememberBottomDrawerState(initialValue = BottomDrawerValue.Closed)
                val scope = rememberCoroutineScope()
                val openDrawer: () -> Unit = {
                    scope.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                }
                MaxixeBottomDrawer(drawerState) {
                    NavHost(navController = navController, startDestination = "purchases") {
                        composable("purchases") { PurchasesListScreen(openDrawer = openDrawer) }
                        composable("purchase/{purchaseId}",
                            arguments = listOf(navArgument("purchaseId") { type = NavType.LongType }))
                        { PurchaseScreen(openDrawer = openDrawer) }
                        // Add more destinations similarly.
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun ActivityPreview() {
    MaxixeTheme {
        val drawerState = rememberBottomDrawerState(initialValue = BottomDrawerValue.Closed)
        MaxixeBottomDrawer(drawerState) {
        }
    }
}


