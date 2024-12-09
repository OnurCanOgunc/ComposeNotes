package com.decode.composenotes.add_edit_note.domain.use_case

import com.decode.composenotes.add_edit_note.domain.repository.AddEditNoteRepository
import com.decode.composenotes.core.domain.model.Note
import javax.inject.Inject

class AddNote @Inject constructor(private val addEditNoteRepository: AddEditNoteRepository) {

    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw Exception("The title of the note can't be empty.")
        }
        addEditNoteRepository.insertNote(note)
    }
}