package com.msruback.maxixe.ui

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.msruback.maxixe.database.exampledata.basicCharacter
import com.msruback.maxixe.ui.composables.screens.characters.CharactersList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.junit.Rule
import org.junit.Test

class CharactersListScreenTests {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_display_characters_provided() {
        composeTestRule.setContent {
            val characters = MutableStateFlow(
                PagingData.from(
                    listOf(
                        basicCharacter,
                        basicCharacter,
                        basicCharacter
                    )
                )
            ).asStateFlow().collectAsLazyPagingItems()
            CharactersList(characters = characters, navigateToDetail = {})
        }
        composeTestRule.onNodeWithTag("characters-list").onChildren().assertCountEquals(3).onFirst()
            .assertTextEquals(basicCharacter.name, basicCharacter.pronouns, basicCharacter.byline)
    }

//    @Test
//    fun test_display_characters_stored(){
//        composeTestRule.setContent {
//            val viewModel = viewModel<CharactersViewModel>()
//            viewModel.insertCharacter(basicCharacter)
//            CharactersListScreen{}
//        }
//        composeTestRule.onNodeWithTag("characters-list").onChildren().assertCountEquals(1).onFirst()
//            .assertTextEquals(basicCharacter.name, basicCharacter.pronouns, basicCharacter.byline)
//    }
}