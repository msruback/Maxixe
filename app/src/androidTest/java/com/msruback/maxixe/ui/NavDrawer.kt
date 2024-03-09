package com.msruback.maxixe.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.junit.Rule
import org.junit.Test

class NavDrawer {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_open_drawer(){
        composeTestRule.setContent {
            val navController = rememberNavController()
            MaxixeScreen(navController) {
                NavHost(navController = navController, startDestination = "purchases") {
                    composable("purchases") {}
                }
            }
        }

        composeTestRule.onNodeWithTag("bottom-drawer").assertIsNotDisplayed()
        composeTestRule.onNodeWithTag("menu-bar").performClick()
        composeTestRule.onNodeWithTag("bottom-drawer").assertIsDisplayed()
        composeTestRule.onNodeWithTag("purchases-drawer-item").assertIsDisplayed()
        composeTestRule.onNodeWithTag("characters-drawer-item").assertIsDisplayed()
        composeTestRule.onNodeWithTag("contacts-drawer-item").assertIsDisplayed()
        composeTestRule.onNodeWithTag("events-drawer-item").assertIsDisplayed()
    }
}