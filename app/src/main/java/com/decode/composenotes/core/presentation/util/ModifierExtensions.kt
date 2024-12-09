package com.decode.composenotes.core.presentation.util


import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

fun Modifier.showIf(condition: Boolean): Modifier {
    return if (condition) this.size(20.dp) else Modifier .size(0.dp).graphicsLayer {
        scaleX = 0f
        scaleY = 0f
    }
}