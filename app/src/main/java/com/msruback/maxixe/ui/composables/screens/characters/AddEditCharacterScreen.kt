package com.msruback.maxixe.ui.composables.screens.characters

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.FabPosition
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.msruback.maxixe.R
import com.msruback.maxixe.ui.composables.appbar.DoneActionButton
import com.msruback.maxixe.ui.composables.appbar.MenuButton
import com.msruback.maxixe.ui.composables.screens.Screen
import com.msruback.maxixe.ui.composables.screens.ScreenInfo

val AddEditCharacterScreenInfo = object : Screen(){
    override fun routeMatch(route: String): Boolean = (route == "character/add" || route.contains("character/edit/\\d"))
    override fun getInfo(navigate: (String) -> Unit, toggleDrawer: () -> Unit): ScreenInfo {
        return object : ScreenInfo() {
            override val hasFab: Boolean = true
            override val fabPosition: FabPosition = FabPosition.End
            override val fab: @Composable() (() -> Unit) = {
                DoneActionButton(stringResource(R.string.save_character)) {
                    navigate("characters")
                }
            }
            override val buttons: @Composable() (RowScope.() -> Unit) = {
                MenuButton(toggleDrawer)
            }
        }
    }
}
