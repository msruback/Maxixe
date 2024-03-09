package com.msruback.maxixe.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.msruback.maxixe.R
import com.msruback.maxixe.ui.ui.theme.MaxixeTheme


@Composable
fun MaxixeAppBar(navController: NavController, toggleDrawer: () -> Unit, content: @Composable (PaddingValues) -> Unit) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDest = navBackStackEntry?.destination?.route
    val screenBarInfo = getScreenBarInfo(currentDest,navController = navController, toggleDrawer)
    MaxixeScaffold(content, screenBarInfo.hasFab, screenBarInfo.fabPosition, screenBarInfo.fab, screenBarInfo.buttons)
}
@Composable
private fun MaxixeScaffold(content: @Composable (PaddingValues) -> Unit, hasFab: Boolean, fabPosition: FabPosition?, fab:@Composable (() -> Unit)?, buttons: @Composable RowScope.() -> Unit){
    if(hasFab){
        Scaffold(
            bottomBar = {
                BottomAppBar(
                    windowInsets = AppBarDefaults.bottomAppBarWindowInsets,
                    cutoutShape = CircleShape,
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    content = buttons
                )
            },
            floatingActionButtonPosition = fabPosition!!,
            floatingActionButton = fab!!,
            isFloatingActionButtonDocked = true,
            backgroundColor = MaterialTheme.colorScheme.background,
            content = content
        )
    } else {
        Scaffold(
            bottomBar = {
                BottomAppBar(
                    windowInsets = AppBarDefaults.bottomAppBarWindowInsets,
                    cutoutShape = CircleShape,
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    content = buttons
                )
            },
            backgroundColor = MaterialTheme.colorScheme.background,
            content = content
        )
    }
}

@Composable
@Preview
private fun Preview(){
    MaxixeTheme {
        val navController = rememberNavController()
        MaxixeAppBar(navController, {}){
            NavHost(navController = navController, startDestination = "purchases") {
                composable("purchases") {}
            }
        }
    }
}

private data class ScreenBarInfo(val hasFab:Boolean, val fabPosition: FabPosition? = null, val fab: @Composable (() -> Unit)? = null, val buttons: @Composable RowScope.() -> Unit)


private fun getScreenBarInfo(currentDest:String?, navController: NavController, toggleDrawer: () -> Unit): ScreenBarInfo{
    if(currentDest!=null){
        return when{
            currentDest == "purchases" -> ScreenBarInfo(true, FabPosition.Center, {AddActionButton(""){
                navController.navigate("purchase/add")
            }}){
                MenuButton(toggleDrawer)
                Spacer(Modifier.weight(1f, true))
                FilterButton(contentDesc = ""){}
            }
            currentDest == "purchase/add" -> ScreenBarInfo(true, FabPosition.End, {DoneActionButton(""){
                navController.navigate("purchases")
            }}){
                MenuButton(toggleDrawer)
            }
            currentDest.contains("purchase/\\d")-> ScreenBarInfo(true, FabPosition.End, {EditActionButton(""){}}){}
            currentDest == "characters" -> ScreenBarInfo(true, FabPosition.Center, {AddActionButton(""){
                navController.navigate("purchase/add")
            }}){
                MenuButton(toggleDrawer)
                Spacer(Modifier.weight(1f, true))
                FilterButton(contentDesc = ""){}
            }
            else -> {ScreenBarInfo(false){}}
        }
    }
    return ScreenBarInfo(false){}
}

@Composable
fun AddActionButton(contentDesc: String, onClick: () -> Unit ) {
    val fabVisible = remember {MutableTransitionState(false).apply{targetState = true}}
    AnimatedVisibility(
        visibleState = fabVisible,
        enter = scaleIn(),
    ) {
        FloatingActionButton(
            onClick = onClick,
            backgroundColor = MaterialTheme.colorScheme.secondary
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = contentDesc,
                tint = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}


@Composable
fun EditActionButton(contentDesc: String, onClick: () -> Unit) {
    val fabVisible = remember {MutableTransitionState(false).apply{targetState = true}}
    AnimatedVisibility(
        visibleState = fabVisible,
        enter = scaleIn(),
    ) {
        FloatingActionButton(
            onClick = onClick,
            backgroundColor = MaterialTheme.colorScheme.secondary
        ) {
            Icon(
                Icons.Default.Edit,
                contentDescription = contentDesc,
                tint = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}

@Composable
fun DoneActionButton(contentDesc: String, onClick: () -> Unit) {
    val fabVisible = remember {MutableTransitionState(false).apply{targetState = true}}
    AnimatedVisibility(
        visibleState = fabVisible,
        enter = scaleIn(),
    ) {
        FloatingActionButton(
            onClick = onClick,
            backgroundColor = MaterialTheme.colorScheme.secondary
        ) {
            Icon(
                Icons.Default.Done,
                contentDescription = contentDesc,
                tint = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}

@Composable
fun MenuButton(onClick: () -> Unit) {
    IconButton(onClick = onClick, Modifier.testTag("menu-bar")) {
        Icon(Icons.Filled.Menu, contentDescription = "Menu", tint = MaterialTheme.colorScheme.onPrimary)
    }
}

@Composable
fun FilterButton(contentDesc: String, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            painterResource(R.drawable.filter_alt),
            contentDescription = contentDesc,
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun MoreButton(contentDesc: String, onClick: () -> Unit) {
    return IconButton(onClick = onClick) {
        Icon(Icons.Filled.MoreVert, contentDescription = contentDesc, tint = MaterialTheme.colorScheme.onPrimary)
    }
}
