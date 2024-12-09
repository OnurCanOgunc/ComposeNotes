package com.decode.composenotes.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    surface = ThemeColors.Night.surface,
    primary = ThemeColors.Night.primary,
    onPrimary = ThemeColors.Night.onPrimary,
    primaryContainer = ThemeColors.Night.primaryContainer,
    onPrimaryContainer = ThemeColors.Night.onPrimaryContainer,
    secondary = ThemeColors.Night.secondary,
    onSecondary = ThemeColors.Night.onSecondary,
    secondaryContainer = ThemeColors.Night.secondaryContainer,
    onSecondaryContainer = ThemeColors.Night.onSecondaryContainer,
    background = Color(0xFF212121),
    onBackground = ThemeColors.Night.onSurface,
    surfaceVariant = ThemeColors.Night.surfaceVariant,
    onSurfaceVariant = ThemeColors.Night.onSurfaceVariant,
    onSurface = ThemeColors.Night.onSurface,
    tertiary = ThemeColors.Night.favButton,
    onTertiary = ThemeColors.Night.addButton,
)

private val LightColorScheme = lightColorScheme(
    surface = ThemeColors.Day.surface,
    primary = ThemeColors.Day.primary,
    onPrimary = ThemeColors.Day.onPrimary,
    primaryContainer = ThemeColors.Day.primaryContainer,
    onPrimaryContainer = ThemeColors.Day.onPrimaryContainer,
    secondary = ThemeColors.Day.secondary,
    onSecondary = ThemeColors.Day.onSecondary,
    secondaryContainer = ThemeColors.Day.secondaryContainer,
    onSecondaryContainer = ThemeColors.Day.onSecondaryContainer,
    background = Color(0xFFFFFFFF),
    onBackground = ThemeColors.Day.onSurface,
    surfaceVariant = ThemeColors.Day.surfaceVariant,
    onSurfaceVariant = ThemeColors.Day.onSurfaceVariant,
    onSurface = ThemeColors.Day.onSurface,
    tertiary = ThemeColors.Day.favButton,
    onTertiary = ThemeColors.Day.addButton,
)

@Composable
fun ComposeNotesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    val window = (view.context as Activity).window
    val windowInsetsController = WindowCompat.getInsetsController(window, view)

    windowInsetsController.isAppearanceLightStatusBars = !darkTheme

    val colors = if (darkTheme) {
       DarkColorScheme
    } else {
       LightColorScheme
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}