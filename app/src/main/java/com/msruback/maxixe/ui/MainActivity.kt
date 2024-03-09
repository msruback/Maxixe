package com.msruback.maxixe.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.BottomDrawerValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.msruback.maxixe.ui.composables.MaxixeAppBar
import com.msruback.maxixe.ui.composables.MaxixeBottomDrawer
import com.msruback.maxixe.ui.composables.screens.purchases.AddEditPurchase
import com.msruback.maxixe.ui.composables.screens.purchases.PurchaseScreen
import com.msruback.maxixe.ui.composables.screens.purchases.PurchasesListScreen
import com.msruback.maxixe.ui.ui.theme.MaxixeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MaxixeScreen(navController) {
                NavHost(navController = navController, startDestination = "purchases") {
                    composable("purchases") {
                        PurchasesListScreen { purchaseId -> navController.navigate("purchase/$purchaseId") }
                    }
                    composable("purchase/{purchaseId}",
                        arguments = listOf(navArgument("purchaseId") { type = NavType.LongType }))
                    { PurchaseScreen() }
                    composable("purchase/add"){
                        AddEditPurchase()
                    }
                    composable("purchase/edit/{purchaseId}"){
                        AddEditPurchase()
                    }
                    composable("characters"){
                        PurchasesListScreen{purchaseId -> navController.navigate("purchase/$purchaseId") }
                    }
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MaxixeScreen(navController: NavController = rememberNavController(), content:@Composable (PaddingValues) -> Unit){
    val drawerState = rememberBottomDrawerState(initialValue = BottomDrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val toggleDrawer: () -> Unit = {
        scope.launch {
            drawerState.apply {
                if (isClosed) open() else close()
            }
        }
    }
    MaxixeTheme{
        MaxixeBottomDrawer(drawerState, toggleDrawer, navController){
            MaxixeAppBar(navController, toggleDrawer, content)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
private fun Preview() {
    val navController = rememberNavController()
    MaxixeScreen(navController) {
        PurchasesListScreen{}
    }
}


