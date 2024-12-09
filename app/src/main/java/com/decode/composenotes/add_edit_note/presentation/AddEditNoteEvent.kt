package com.decode.composenotes.add_edit_note.presentation

import com.decode.composenotes.core.domain.model.Note


sealed interface AddEditNoteEvent {
    data class EnteredTitle(val value: String) : AddEditNoteEvent
    data class EnteredDescription(val value: String) : AddEditNoteEvent
    data class SaveNote(val note: Note) : AddEditNoteEvent
}