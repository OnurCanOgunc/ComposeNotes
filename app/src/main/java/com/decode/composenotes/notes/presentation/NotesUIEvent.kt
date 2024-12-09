package com.decode.composenotes.notes.presentation

import com.decode.composenotes.core.presentation.helper_viewmodel.BasedUIEvent

sealed class NotesUIEvent : BasedUIEvent {
    data class DarkModeActive(val isDarkMode: Boolean) : NotesUIEvent()
}