package com.decode.composenotes.notes.domain.use_case

import com.decode.composenotes.core.domain.model.Note
import com.decode.composenotes.notes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNotes @Inject constructor(private val noteRepository: NoteRepository) {
    operator fun invoke(): Flow<List<Note>> {
       return noteRepository.getNotes()
    }
}