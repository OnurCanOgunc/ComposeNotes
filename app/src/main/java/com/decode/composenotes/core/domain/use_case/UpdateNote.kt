package com.decode.composenotes.core.domain.use_case

import com.decode.composenotes.core.domain.model.Note
import com.decode.composenotes.core.domain.repository.Repository
import javax.inject.Inject

class UpdateNote @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(note: Note) {
        repository.updateNote(note)
    }
}