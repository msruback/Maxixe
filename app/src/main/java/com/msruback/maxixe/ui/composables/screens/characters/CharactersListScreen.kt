package com.msruback.maxixe.ui.composables.screens.characters

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.FabPosition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.msruback.maxixe.R
import com.msruback.maxixe.ui.composables.appbar.AddActionButton
import com.msruback.maxixe.ui.composables.appbar.FilterButton
import com.msruback.maxixe.ui.composables.appbar.MenuButton
import com.msruback.maxixe.ui.composables.screens.Screen
import com.msruback.maxixe.ui.composables.screens.ScreenInfo


@Composable
fun CharactersListScreen(navigateToDetail: (Long) -> Unit){

}

@Composable
fun CharactersList(){

}

@Composable
@Preview
private fun Preview() {

}


val CharactersListScreenInfo = object : Screen() {
    override fun routeMatch(route: String): Boolean = (route == "characters")
    override fun getInfo(navigate: (String) -> Unit, toggleDrawer: () -> Unit): ScreenInfo {
        return object : ScreenInfo() {
            override val hasFab: Boolean = true
            override val fabPosition: FabPosition = FabPosition.Center
            override val fab: @Composable (() -> Unit) = {
                AddActionButton(stringResource(R.string.add_character)) {
                    navigate("character/add")
                }
            }
            override val buttons: @Composable (RowScope.() -> Unit) = {
                MenuButton(toggleDrawer)
                Spacer(Modifier.weight(1f, true))
                FilterButton(stringResource(R.string.filter_characters)) {}
            }
        }
    }
}