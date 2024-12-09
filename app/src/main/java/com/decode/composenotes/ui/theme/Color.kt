package com.decode.composenotes.ui.theme

import androidx.compose.ui.graphics.Color

val primaryLight = Color(0xFFDCDADA)
val onPrimaryLight = Color(0xFFFFFFFF)
val primaryContainerLight = Color(0xFF505050)
val onPrimaryContainerLight = Color(0xFFF0F0F0)
val secondaryLight = Color(0xFF4A5B61)
val onSecondaryLight = Color(0xFFFFFFFF)
val secondaryContainerLight = Color(0xFF647379)
val onSecondaryContainerLight = Color(0xFFE5E9EA)
val surfaceLight = Color(0xFFF8F8F8)
val onSurfaceLight = Color(0xFF212121)
val surfaceVariantLight = Color(0xFFE2E2E2)
val onSurfaceVariantLight = Color(0xFF4E4E4E)
val addButtonLight = Color(0xFFF5B44A)
val favButtonLight = Color(0xFF0074D9)

val primaryDark = Color(0xFF1F1F1F)
val onPrimaryDark = Color(0xFF212121)
val primaryContainerDark = Color(0xFF383838)
val onPrimaryContainerDark = Color(0xFF505050)
val secondaryDark = Color(0xFF849298)
val onSecondaryDark = Color(0xFFE8EBEC)
val secondaryContainerDark = Color(0xFF3B4B52)
val onSecondaryContainerDark = Color(0xFFC9D1D5)
val surfaceDark = Color(0xFF121212)
val onSurfaceDark = Color(0xFFE0E0E0)
val surfaceVariantDark = Color(0xFF2D3538)
val onSurfaceVariantDark = Color(0xFF9A9A9A)
val addButtonDark = Color(0xFFFCAE2C)
val favButtonDark = Color(0xFF00B8D9)

sealed class ThemeColors(
    val surface: Color,
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
    val secondary: Color,
    val onSecondary: Color,
    val secondaryContainer: Color,
    val onSecondaryContainer: Color,
    val text: Color,
    val onSurface: Color,
    val surfaceVariant: Color,
    val onSurfaceVariant: Color,
    val addButton: Color,
    val favButton: Color
) {

    object Night : ThemeColors( // Background color for dark mode
        surface = surfaceLight,
        primary = primaryLight,
        onPrimary = onPrimaryDark,
            primaryContainer = primaryContainerDark,
        onPrimaryContainer = onPrimaryContainerDark,
        secondary = secondaryDark,
        onSecondary = onSecondaryDark,
        secondaryContainer = secondaryContainerDark,
        onSecondaryContainer = onSecondaryContainerDark,
        text = onSurfaceDark,
        onSurface = onSurfaceDark,
        surfaceVariant = surfaceVariantDark,
        onSurfaceVariant = onSurfaceVariantDark,
        addButton = addButtonDark,
        favButton = favButtonDark
    )

    object Day : ThemeColors(
        surface = surfaceDark,
        primary = primaryDark,
        onPrimary = onPrimaryLight,
        primaryContainer = primaryContainerLight,
        onPrimaryContainer = onPrimaryContainerLight,
        secondary = secondaryLight,
        onSecondary = onSecondaryLight,
        secondaryContainer = secondaryContainerLight,
        onSecondaryContainer = onSecondaryContainerLight,
        text = onSurfaceLight,
        onSurface = onSurfaceLight,
        surfaceVariant = surfaceVariantLight,
        onSurfaceVariant = onSurfaceVariantLight,
        addButton = addButtonLight,
        favButton = favButtonLight
    )
}