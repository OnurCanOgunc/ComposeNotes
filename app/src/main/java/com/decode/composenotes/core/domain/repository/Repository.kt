package com.decode.composenotes.core.domain.repository

import com.decode.composenotes.core.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun deleteNote(note: Note)
    fun searchNotes(query: String): Flow<List<Note>>
    suspend fun updateNote(note: Note)
}
