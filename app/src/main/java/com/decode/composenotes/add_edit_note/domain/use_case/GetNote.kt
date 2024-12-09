package com.decode.composenotes.add_edit_note.domain.use_case


import com.decode.composenotes.add_edit_note.domain.repository.AddEditNoteRepository
import com.decode.composenotes.core.domain.model.Note
import javax.inject.Inject

class GetNote @Inject constructor(private val addEditNoteRepository: AddEditNoteRepository) {
    suspend operator fun invoke(id: Int): Note? {
        return addEditNoteRepository.getNoteById(id)
    }
}