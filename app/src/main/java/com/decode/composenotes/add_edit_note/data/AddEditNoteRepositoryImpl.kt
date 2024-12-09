package com.decode.composenotes.add_edit_note.data

import com.decode.composenotes.add_edit_note.domain.repository.AddEditNoteRepository
import com.decode.composenotes.core.data.data_source.NoteDao
import com.decode.composenotes.core.domain.model.Note
import javax.inject.Inject

class AddEditNoteRepositoryImpl @Inject constructor(private val noteDao: NoteDao) : AddEditNoteRepository {
    override suspend fun insertNote(note: Note) {
        noteDao.insertNote(note = note)
    }

    override suspend fun getNoteById(id: Int): Note? {
        return noteDao.getNoteById(id)
    }
}