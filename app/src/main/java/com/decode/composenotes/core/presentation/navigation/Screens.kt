package com.decode.composenotes.core.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Screens {
    @Serializable
    object Note : Screens {
        @Serializable
        data object NoteScreen : Screens
        @Serializable
        data class AddEditNoteScreen(val noteId: Int = -1) : Screens
    }
    @Serializable
    object Favorite : Screens {
        @Serializable
        data object FavoriteScreen : Screens
    }
}