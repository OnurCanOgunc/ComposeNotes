package com.decode.composenotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.decode.composenotes.core.presentation.navigation.AppNavigation
import com.decode.composenotes.notes.presentation.NoteViewModel
import com.decode.composenotes.ui.theme.ComposeNotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: NoteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val isDarkMode by viewModel.isDarkModeActive.collectAsState(initial = false)
            ComposeNotesTheme(darkTheme = isDarkMode) {
                AppNavigation(isDarkMode = isDarkMode)
            }
        }
    }
}

