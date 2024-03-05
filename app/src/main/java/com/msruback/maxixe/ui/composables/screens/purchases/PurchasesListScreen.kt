package com.msruback.maxixe.ui.composables.screens.purchases

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.msruback.maxixe.ui.composables.AddActionButton
import com.msruback.maxixe.ui.composables.FilterButton
import com.msruback.maxixe.ui.composables.MaxixeAppBar
import com.msruback.maxixe.ui.composables.MenuButton
import com.msruback.maxixe.ui.ui.theme.MaxixeTheme

@Composable
fun PurchasesListScreen(openDrawer: () -> Unit) {
    Scaffold(
        bottomBar = {
            MaxixeAppBar {
                MenuButton(openDrawer)
                Spacer(Modifier.weight(1f, true))
                FilterButton(onClick = { /*TODO*/ }, contentDesc = "Filters")
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            AddActionButton(onClick = { /*TODO*/ }, contentDesc = "Add Purchase")
        },
        backgroundColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {

        }

    }
}

@Composable
@Preview
private fun Preview() {
    MaxixeTheme {
        //PurchasesListScreen()
    }
}
