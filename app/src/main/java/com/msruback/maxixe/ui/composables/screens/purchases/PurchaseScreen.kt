package com.msruback.maxixe.ui.composables.screens.purchases

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.msruback.maxixe.ui.MaxixeScreen

@Composable
fun PurchaseScreen(){
    val id:Long = 0
    LazyColumn{

    }
}

@Composable
@Preview
private fun Preview(){
    MaxixeScreen {
        PurchaseScreen()
    }
}