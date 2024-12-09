package com.decode.composenotes.notes.data

import com.decode.composenotes.core.data.data_source.NoteDao
import com.decode.composenotes.core.domain.model.Note
import com.decode.composenotes.notes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val noteDao: NoteDao) : NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return NoteDao.getNotes()
    }
}