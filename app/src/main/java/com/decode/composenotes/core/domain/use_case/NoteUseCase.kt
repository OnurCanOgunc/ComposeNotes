package com.decode.composenotes.core.domain.use_case


data class NoteUseCase(
    val deleteNote: DeleteNote,
    val searchNotes: SearchNotes,
    val updateNote: UpdateNote,
)
