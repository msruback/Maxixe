package com.msruback.maxixe.ui.composables.appbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.msruback.maxixe.ui.composables.screens.Screen
import com.msruback.maxixe.ui.composables.screens.ScreenInfo
import com.msruback.maxixe.ui.composables.screens.purchases.AddEditPurchaseScreenInfo
import com.msruback.maxixe.ui.composables.screens.purchases.PurchasesListScreenInfo
import com.msruback.maxixe.ui.composables.screens.purchases.PurchaseScreenInfo
import com.msruback.maxixe.ui.ui.theme.MaxixeTheme


@Composable
fun MaxixeAppBar(
    navController: NavController,
    toggleDrawer: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDest = navBackStackEntry?.destination?.route
    val screenBarInfo = getScreenBarInfo(currentDest, navController = navController, toggleDrawer)
    if (screenBarInfo != null) {
        MaxixeScaffold(
            screenBarInfo.hasFab,
            screenBarInfo.fabPosition,
            {
                val fabVisible = remember { MutableTransitionState(false).apply{targetState = true}}
                AnimatedVisibility(
                    visibleState = fabVisible,
                    enter = scaleIn()
                ) {
                    screenBarInfo.fab?.let { it() }
                }
            },
            screenBarInfo.buttons,
            content
        )
    }
}

@Composable
fun MaxixeScaffold(
    hasFab: Boolean,
    fabPosition: FabPosition?,
    fab: @Composable (() -> Unit)?,
    buttons: @Composable RowScope.() -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    if (hasFab) {
        Scaffold(
            bottomBar = {
                BottomAppBar(
                    windowInsets = AppBarDefaults.bottomAppBarWindowInsets,
                    cutoutShape = CircleShape,
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    content = buttons
                )
            },
            floatingActionButtonPosition = fabPosition!!,
            floatingActionButton = fab!!,
            isFloatingActionButtonDocked = true,
            backgroundColor = MaterialTheme.colorScheme.background,
            content = content
        )
    } else {
        Scaffold(
            bottomBar = {
                BottomAppBar(
                    windowInsets = AppBarDefaults.bottomAppBarWindowInsets,
                    cutoutShape = CircleShape,
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    content = buttons
                )
            },
            backgroundColor = MaterialTheme.colorScheme.background,
            content = content
        )
    }
}

@Composable
@Preview
private fun DetailPreview() {
    MaxixeTheme {
        val screenBarInfo = PurchaseScreenInfo.getInfo({}){}
        MaxixeScaffold(
            screenBarInfo.hasFab,
            screenBarInfo.fabPosition,
            {
                EditActionButton(contentDesc = "") {}
            },
            screenBarInfo.buttons){}
    }
}
@Composable
@Preview
private fun ListPreview() {
    MaxixeTheme {
        val screenBarInfo = PurchasesListScreenInfo.getInfo({}){}
        MaxixeScaffold(
            screenBarInfo.hasFab,
            screenBarInfo.fabPosition,
            {
                AddActionButton(contentDesc = "") {}
            },
            screenBarInfo.buttons){}
    }
}


private fun getScreenBarInfo(
    currentDest: String?,
    navController: NavController,
    toggleDrawer: () -> Unit
): ScreenInfo? {
    val navigate: (String) -> Unit = { route -> navController.navigate(route) }
    val screens: List<Screen> = listOf(
        PurchasesListScreenInfo,
        PurchaseScreenInfo,
        AddEditPurchaseScreenInfo
    )
    if (currentDest != null) {
        screens.forEach { screen ->
            if (screen.routeMatch(currentDest)) {
                return screen.getInfo(navigate, toggleDrawer)
            }
        }
    }
    return null
}
