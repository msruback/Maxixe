package com.msruback.maxixe.ui.composables.screens

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.FabPosition
import androidx.compose.runtime.Composable

abstract class ScreenInfo{

    abstract val hasFab:Boolean
    abstract val fabPosition: FabPosition?
    abstract val fab: @Composable (() -> Unit)?
    abstract val buttons: @Composable RowScope.() -> Unit
}

abstract class Screen{
    abstract fun routeMatch(route: String): Boolean
    abstract fun getInfo(navigate:(String) -> Unit, toggleDrawer: ()-> Unit): ScreenInfo
}