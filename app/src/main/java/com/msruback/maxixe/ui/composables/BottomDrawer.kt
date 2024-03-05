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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msruback.maxixe.R
import com.msruback.maxixe.ui.ui.theme.MaxixeTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MaxixeBottomDrawer(
    drawerState: BottomDrawerState,
    mainContent: @Composable () -> Unit
) {
    val itemModifier: (() -> Unit) -> Modifier = { onClick ->
        Modifier
            .height(48.dp)
            .clip(
                RoundedCornerShape(20)
            )
            .clickable(onClick = onClick)
    }

    BottomDrawer(
        drawerState = drawerState,
        drawerBackgroundColor = MaterialTheme.colorScheme.primary,
        drawerShape = RoundedCornerShape(10,10,0,0),
        drawerContent = {
            Column(
                Modifier
                    .padding(10.dp)
                    .fillMaxWidth()) {
                Row(itemModifier {
                    //navigate
                    //select
                    //close drawer
                }.background(MaterialTheme.colorScheme.secondary)) {
                    ListItem(
                        text = {
                            Text(
                                "Purchases",
                                color = MaterialTheme.colorScheme.onSecondary
                            )
                        },
                        icon = {
                            Icon(
                                painterResource(R.drawable.purchases),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSecondary
                            )
                        }
                    )
                }
                Row(modifier = itemModifier {

                }) {
                    ListItem(
                        text = {
                            Text(
                                "Characters",
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        },
                        icon = {
                            Icon(
                                painterResource(R.drawable.characters),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    )
                }
                Row(modifier = itemModifier {

                }) {
                    ListItem(
                        text = {
                            Text(
                                "Contacts",
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        },
                        icon = {
                            Icon(
                                painterResource(R.drawable.contacts),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    )
                }
                Row(modifier = itemModifier {

                }) {
                    ListItem(
                        text = {
                            Text(
                                "Events",
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        },
                        icon = {
                            Icon(
                                painterResource(R.drawable.events),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    )
                }
            }

        },
        content = mainContent
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview
private fun Preview() {
    MaxixeTheme {
        MaxixeBottomDrawer(rememberBottomDrawerState(BottomDrawerValue.Open)) {}
    }
}