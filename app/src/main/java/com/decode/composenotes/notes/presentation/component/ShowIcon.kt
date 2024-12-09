package com.decode.composenotes.notes.presentation.component

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun ShowIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    icon: ImageVector,
    contentDescription: String,
    tint: Color
) {
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(imageVector = icon, contentDescription = contentDescription, tint = tint)
    }
}