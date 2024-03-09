package com.msruback.maxixe.ui.composables.screens.purchases

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.msruback.maxixe.ui.MaxixeScreen

@Composable
fun PurchasesListScreen(navigateToDetail: (Long) -> Unit) {
    LazyColumn {
    }
}

@Composable
@Preview
private fun Preview() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "purchases") {
        composable("purchases") {}
    }
    MaxixeScreen(navController) {
        PurchasesListScreen { }
    }
}
