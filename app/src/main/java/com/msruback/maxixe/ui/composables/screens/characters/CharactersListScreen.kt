package com.msruback.maxixe.ui.composables.screens.characters

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FabPosition
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.msruback.maxixe.R
import com.msruback.maxixe.database.entities.Character
import com.msruback.maxixe.database.exampledata.basicCharacter
import com.msruback.maxixe.ui.composables.appbar.AddActionButton
import com.msruback.maxixe.ui.composables.appbar.FilterButton
import com.msruback.maxixe.ui.composables.appbar.MaxixeScaffold
import com.msruback.maxixe.ui.composables.appbar.MenuButton
import com.msruback.maxixe.ui.composables.screens.Screen
import com.msruback.maxixe.ui.composables.screens.ScreenInfo
import com.msruback.maxixe.ui.ui.theme.MaxixeTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


@Composable
fun CharactersListScreen(navigateToDetail: (Long) -> Unit){

}

@Composable
fun CharactersList(characters: LazyPagingItems<Character>, navigateToDetail: (Long) -> Unit){
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(1f).testTag("characters-list")
    ) {
        items(characters.itemCount) { index ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(.9f)
            ) {
                if (index == 0) {
                    Spacer(modifier = Modifier.height(15.dp))
                }
                CharacterItem(characters[index]!!, navigateToDetail)
                if (index < characters.itemCount - 1) {
                    HorizontalDivider(color = MaterialTheme.colorScheme.onBackground)
                }
            }
        }
    }
}

@Composable
@Preview
private fun LightModePreview() {
    val characters = MutableStateFlow(
        PagingData.from(listOf(
            basicCharacter,
            basicCharacter,
            basicCharacter
    ))).asStateFlow().collectAsLazyPagingItems()
    val screenBarInfo = CharactersListScreenInfo.getInfo({}){}
    MaxixeTheme {
        MaxixeScaffold(
            screenBarInfo.hasFab,
            screenBarInfo.fabPosition,
            { AddActionButton(""){} },
            screenBarInfo.buttons) {
            CharactersList(
                characters = characters
            ) {}
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun NightModePreview() {
    val characters = MutableStateFlow(
        PagingData.from(listOf(
            basicCharacter,
            basicCharacter,
            basicCharacter
        ))).asStateFlow().collectAsLazyPagingItems()
    val screenBarInfo = CharactersListScreenInfo.getInfo({}){}
    MaxixeTheme {
        MaxixeScaffold(
            screenBarInfo.hasFab,
            screenBarInfo.fabPosition,
            { AddActionButton(""){} },
            screenBarInfo.buttons) {
            CharactersList(
                characters = characters
            ) {}
        }
    }
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