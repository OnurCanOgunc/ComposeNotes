package com.decode.composenotes.notes.domain.repository

import com.decode.composenotes.core.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotes(): Flow<List<Note>>
}