package com.decode.composenotes.core.data.repository

import com.decode.composenotes.core.data.data_source.NoteDao
import com.decode.composenotes.core.domain.model.Note
import com.decode.composenotes.core.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val noteDao: NoteDao): Repository {
    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note = note)
    }

    override fun searchNotes(query: String): Flow<List<Note>> {
        return noteDao.searchNotes(query)
    }

    override suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }
}