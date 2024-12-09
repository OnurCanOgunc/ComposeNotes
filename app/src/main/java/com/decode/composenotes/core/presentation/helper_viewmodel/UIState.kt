package com.decode.composenotes.core.presentation.helper_viewmodel

import com.decode.composenotes.core.domain.model.Note

sealed class UIState {
    object Loading : UIState()
    data class Content(
        val notes: List<Note> = emptyList(),
        val searchNotes: List<Note> = emptyList(),
        val favorites: List<Note> = emptyList()
    ) : UIState()
    data class Error(val message: String) : UIState()
}