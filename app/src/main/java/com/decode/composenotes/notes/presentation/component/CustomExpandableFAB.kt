package com.decode.composenotes.notes.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import kotlin.collections.forEach

data class FABItem(
    val icon: ImageVector,
    val text: String,
)

val itemList = listOf(
    FABItem(icon = Icons.Rounded.Create, text = "Create"),
    FABItem(icon = Icons.Rounded.Favorite, text = "Favorite"),
)

@Composable
fun CustomExpandableFAB(
    modifier: Modifier = Modifier,
    items: List<FABItem> = itemList,
    fabButton: FABItem = FABItem(icon = Icons.Default.Add, text = "Expanded"),
    onItemClick: (FABItem) -> Unit
) {
    var buttonClicked by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    Card(
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(4.dp),
    ) {
        Column {
            AnimatedVisibility(
                visible = buttonClicked,
                enter = expandVertically(tween(1500)) + fadeIn(),
                exit = shrinkVertically(tween(1300)) + fadeOut(animationSpec = tween(1200))
            ) {
                Column(
                    modifier = Modifier
                        .padding(vertical = 10.dp, horizontal = 30.dp)
                ) {
                    items.forEach { item ->
                        Row(
                            modifier = Modifier
                                .padding(vertical = 10.dp)
                                .clickable(
                                    interactionSource = interactionSource,
                                    indication = null,
                                    onClick = {
                                        onItemClick(item)
                                        buttonClicked = false
                                    }
                                )
                        ) {
                            Icon(imageVector = item.icon, contentDescription = "refresh")

                            Spacer(modifier = Modifier.width(15.dp))

                            Text(text = item.text)
                        }
                    }
                }
            }

            Card(
                modifier = Modifier.clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = {
                        buttonClicked = !buttonClicked
                    }
                ), colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onTertiary)) {
                Row(
                    modifier = Modifier.padding(vertical = 20.dp, horizontal = 20.dp)
                ) {
                    Icon(
                        imageVector = fabButton.icon, contentDescription = "refresh"
                    )
                    AnimatedVisibility(
                        visible = buttonClicked,
                        enter = expandVertically(animationSpec = tween(1400)) + fadeIn(),
                        exit = shrinkVertically(tween(1500)) + fadeOut(tween(1200))
                    ) {
                        Row {
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(text = fabButton.text)
                        }
                    }
                }
            }

        }
    }
}


