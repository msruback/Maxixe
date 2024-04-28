package com.msruback.maxixe.ui

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.msruback.maxixe.database.exampledata.basicContact
import com.msruback.maxixe.ui.composables.screens.contacts.ContactsList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.junit.Rule
import org.junit.Test

class ContactsListScreenTests {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_display_contacts_provided() {
        composeTestRule.setContent {
            val contacts = MutableStateFlow(
                PagingData.from(
                    listOf(
                        basicContact,
                        basicContact,
                        basicContact
                    )
                )
            ).asStateFlow().collectAsLazyPagingItems()
            ContactsList(contacts = contacts, navigateToDetail = {})
        }
        composeTestRule.onNodeWithTag("contacts-list").onChildren().assertCountEquals(3).onFirst()
            .assertTextEquals(basicContact.name, basicContact.byline)
    }

//    @Test
//    fun test_display_contacts_stored(){
//        composeTestRule.setContent {
//            val viewModel = viewModel<ContactsViewModel>()
//            viewModel.insertContact(basicContact)
//            ContactsListScreen{}
//        }
//        composeTestRule.onNodeWithTag("contacts-list").onChildren().assertCountEquals(1).onFirst()
//            .assertTextEquals(basicContact.name, basicContact.byline)
//    }
}