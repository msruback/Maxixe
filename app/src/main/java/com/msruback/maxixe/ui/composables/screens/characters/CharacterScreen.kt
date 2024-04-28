package com.msruback.maxixe.ui.composables.screens.characters

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.FabPosition
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.msruback.maxixe.R
import com.msruback.maxixe.ui.composables.appbar.EditActionButton
import com.msruback.maxixe.ui.composables.appbar.MenuButton
import com.msruback.maxixe.ui.composables.screens.Screen
import com.msruback.maxixe.ui.composables.screens.ScreenInfo

val CharacterScreenInfo = object : Screen(){
    override fun routeMatch(route: String): Boolean = route.contains("character/\\d")
    override fun getInfo(navigate: (String) -> Unit, toggleDrawer: () -> Unit): ScreenInfo {
        return object : ScreenInfo() {
            override val hasFab: Boolean = true
            override val fabPosition: FabPosition = FabPosition.End
            override val fab: @Composable() (() -> Unit) = { EditActionButton(stringResource(R.string.edit_character)){} }
            override val buttons: @Composable() (RowScope.() -> Unit) = {
                MenuButton(toggleDrawer)
            }
        }
    }
}
