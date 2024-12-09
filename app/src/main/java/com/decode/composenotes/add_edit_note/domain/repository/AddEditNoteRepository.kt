package com.decode.composenotes.add_edit_note.domain.repository

import com.decode.composenotes.core.domain.model.Note


interface AddEditNoteRepository {
    suspend fun insertNote(note: Note)
    suspend fun getNoteById(id: Int): Note?
}