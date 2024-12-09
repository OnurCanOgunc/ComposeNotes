package com.decode.composenotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.decode.composenotes.core.presentation.navigation.AppNavigation
import com.decode.composenotes.ui.theme.ComposeNotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeNotesTheme {
                AppNavigation(isDarkMode = false)
            }
        }
    }
}

