package com.msruback.maxixe.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomDrawer
import androidx.compose.material.BottomDrawerState
import androidx.compose.material.BottomDrawerValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomDrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.msruback.maxixe.R
import com.msruback.maxixe.ui.ui.theme.MaxixeTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MaxixeBottomDrawer(
    drawerState: BottomDrawerState,
    toggleDrawer: () -> Unit,
    navController: NavController,
    content: @Composable () -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    BottomDrawer(
        drawerState = drawerState,
        drawerBackgroundColor = MaterialTheme.colorScheme.primary,
        drawerShape = RoundedCornerShape(10, 10, 0, 0),
        drawerContent = {
            Column(
                Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .testTag("bottom-drawer")
            ) {
                NavItem(route = currentDestination, dest = "purchase", name = "Purchases", icon = R.drawable.purchases) {
                    navController.navigate("purchases")
                    toggleDrawer()
                }
                NavItem(route = currentDestination, dest = "character", name = "Characters", icon = R.drawable.characters) {
                    navController.navigate("characters")
                    toggleDrawer()
                }
                NavItem(route = currentDestination, dest = "contact", name = "Contacts", icon = R.drawable.contacts) {
                    toggleDrawer()
                }
                NavItem(route = currentDestination, dest = "event", name = "Events", icon = R.drawable.events) {
                    toggleDrawer()
                }
            }

        },
        content = content
    )
}
@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun NavItem(route: String?, dest: String, name: String, icon:Int, onClick:()->Unit){
    val isCurrentDest = (route != null && route.contains(dest))
    Row(modifier = Modifier
        .height(48.dp)
        .clip(
            RoundedCornerShape(20)
        )
        .clickable(onClick = onClick)
        .background(
            if (isCurrentDest)
                MaterialTheme.colorScheme.secondary
            else
                MaterialTheme.colorScheme.primary
        )) {
        ListItem(
            Modifier.testTag(name.lowercase()+"-drawer-item"),
            text = {
                Text(
                    name,
                    color = if (isCurrentDest)
                        MaterialTheme.colorScheme.onSecondary
                    else
                        MaterialTheme.colorScheme.onPrimary
                )
            },
            icon = {
                Icon(
                    painterResource(icon),
                    contentDescription = null,
                    tint = if (isCurrentDest)
                        MaterialTheme.colorScheme.onSecondary
                    else
                        MaterialTheme.colorScheme.onPrimary
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview
private fun Preview() {
    MaxixeTheme {
        val navController = rememberNavController()
        MaxixeBottomDrawer(rememberBottomDrawerState(BottomDrawerValue.Open), {}, navController) {
            NavHost(navController = navController, startDestination = "purchases") {
                composable("purchases") {}
            }
        }
    }
}