package com.msruback.maxixe.ui

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.msruback.maxixe.database.exampledata.basicTag
import com.msruback.maxixe.ui.composables.screens.tags.TagsList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.junit.Rule
import org.junit.Test

class TagsListScreenTests {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_display_tags_provided() {
        composeTestRule.setContent {
            val tags = MutableStateFlow(
                PagingData.from(
                    listOf(
                        basicTag,
                        basicTag,
                        basicTag
                    )
                )
            ).asStateFlow().collectAsLazyPagingItems()
            TagsList(tags = tags, navigateToDetail = {})
        }
        composeTestRule.onNodeWithTag("tags-list").onChildren().assertCountEquals(3).onFirst()
            .assertTextEquals(basicTag.name)
    }

//    @Test
//    fun test_display_tags_stored(){
//        composeTestRule.setContent {
//            val viewModel = viewModel<TagsViewModel>()
//            viewModel.insertTag(basicTag)
//            TagsListScreen{}
//        }
//        composeTestRule.onNodeWithTag("tags-list").onChildren().assertCountEquals(1).onFirst()
//            .assertTextEquals(basicTag.name)
//    }
}