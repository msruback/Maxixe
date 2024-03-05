package com.msruback.maxixe.ui.composables

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.msruback.maxixe.R


@Composable
fun MaxixeAppBar(buttons: @Composable RowScope.() -> Unit) {
    BottomAppBar(
        windowInsets = AppBarDefaults.bottomAppBarWindowInsets,
        cutoutShape = CircleShape,
        backgroundColor = MaterialTheme.colorScheme.primary,
        content = buttons
    )
}
@Composable
@Preview
private fun Preview(){
    MaxixeAppBar {}
}


@Composable
fun AddActionButton(onClick: () -> Unit, contentDesc: String) {
    FloatingActionButton(onClick = onClick, backgroundColor = MaterialTheme.colorScheme.secondary) {
        Icon(Icons.Default.Add, contentDescription = contentDesc, tint = MaterialTheme.colorScheme.onSecondary)
    }
}

@Composable
fun EditActionButton(onClick: () -> Unit, contentDesc: String) {
    FloatingActionButton(onClick = onClick, backgroundColor = MaterialTheme.colorScheme.secondary) {
        Icon(Icons.Default.Edit, contentDescription = contentDesc, tint = MaterialTheme.colorScheme.onSecondary)
    }
}

@Composable
fun DoneActionButton(onClick: () -> Unit, contentDesc: String) {
    FloatingActionButton(onClick = onClick, backgroundColor = MaterialTheme.colorScheme.secondary) {
        Icon(Icons.Default.Done, contentDescription = contentDesc, tint = MaterialTheme.colorScheme.onSecondary)
    }
}

@Composable
fun MenuButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(Icons.Filled.Menu, contentDescription = "Menu", tint = MaterialTheme.colorScheme.onPrimary)
    }
}

@Composable
fun FilterButton(onClick: () -> Unit, contentDesc: String) {
    IconButton(onClick = onClick) {
        Icon(
            painterResource(R.drawable.filter_alt),
            contentDescription = contentDesc,
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun MoreButton(onClick: () -> Unit, contentDesc: String) {
    return IconButton(onClick = onClick) {
        Icon(Icons.Filled.MoreVert, contentDescription = contentDesc, tint = MaterialTheme.colorScheme.onPrimary)
    }
}
