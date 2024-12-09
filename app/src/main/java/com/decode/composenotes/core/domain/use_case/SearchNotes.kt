package com.decode.composenotes.core.domain.use_case

import com.decode.composenotes.core.domain.model.Note
import com.decode.composenotes.core.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class SearchNotes(private val repository: Repository) {
    operator fun invoke(query: String): Flow<List<Note>> {
        return repository.searchNotes(query)
    }
}