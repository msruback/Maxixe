package com.msruback.maxixe.ui.composables.appbar

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.msruback.maxixe.R
import com.msruback.maxixe.ui.ui.theme.MaxixeTheme

@Composable
fun AddActionButton(contentDesc: String, onClick: () -> Unit) {
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
@Preview
@Composable
fun AddActionButtonPreview(){
    MaxixeTheme {
            AddActionButton(""){}
    }
}

@Composable
fun EditActionButton(contentDesc: String, onClick: () -> Unit) {
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

@Preview
@Composable
fun EditActionButtonPreview(){
    MaxixeTheme {
        EditActionButton(""){}
    }
}

@Composable
fun DoneActionButton(contentDesc: String, onClick: () -> Unit) {
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

@Preview
@Composable
fun DoneActionButtonPreview(){
    MaxixeTheme {
        DoneActionButton(""){}
    }
}

@Composable
fun MenuButton(onClick: () -> Unit) {
    IconButton(onClick = onClick, Modifier.testTag("menu-bar")) {
        Icon(Icons.Filled.Menu, contentDescription = "Menu", tint = MaterialTheme.colorScheme.onPrimary)
    }
}

@Preview
@Composable
fun MenuButtonPreview(){
    MaxixeTheme {
        Surface(color = MaterialTheme.colorScheme.primary) {
            MenuButton{}
        }
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

@Preview
@Composable
fun FilterButtonPreview(){
    MaxixeTheme {
        Surface(color = MaterialTheme.colorScheme.primary) {
            FilterButton(""){}
        }
    }
}

@Composable
fun MoreButton(contentDesc: String, onClick: () -> Unit) {
    return IconButton(onClick = onClick) {
        Icon(Icons.Filled.MoreVert, contentDescription = contentDesc, tint = MaterialTheme.colorScheme.onPrimary)
    }
}

@Preview
@Composable
fun MoreButtonPreview(){
    MaxixeTheme {
        Surface(color = MaterialTheme.colorScheme.primary) {
            MoreButton(""){}
        }
    }
}
