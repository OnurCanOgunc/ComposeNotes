package com.decode.composenotes.add_edit_note.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decode.composenotes.core.domain.model.Note
import com.decode.composenotes.core.presentation.util.dateFormat
import com.decode.composenotes.add_edit_note.domain.use_case.AddEditNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val addEditNoteUseCase: AddEditNoteUseCase,
) : ViewModel() {

    var note by mutableStateOf(
        Note(id = 0, title = "", description = "", timestamp = "")
    )
        private set

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: AddEditNoteEvent) {
            when (event) {
                is AddEditNoteEvent.EnteredTitle -> {
                    updateNoteTitle(event.value)
                }

                is AddEditNoteEvent.EnteredDescription -> {
                    updateNoteDescription(event.value)
                }

                is AddEditNoteEvent.SaveNote -> {
                    addNote(event.note)
                }
            }

    }

    fun getNote(id: Int) {
        viewModelScope.launch {
            note = addEditNoteUseCase.getNote(id) ?: note
        }
    }

    fun addNote(note: Note) {
        viewModelScope.launch {
            try {
                addEditNoteUseCase.addNote(note)
                _eventFlow.emit(UiEvent.SaveNote)
            } catch (e: Exception) {
                _eventFlow.emit(
                    UiEvent.ShowSnackBar(
                        message = e.message ?: "Couldn't save note"
                    )
                )
            }
        }
    }

    fun updateNoteTitle(newValue: String) {
        note = if (note.title != newValue) {
            note.copy(title = newValue, timestamp = dateFormat(System.currentTimeMillis()))
        } else {
            note.copy(title = newValue)
        }
    }

    fun updateNoteDescription(newValue: String) {
        note = if (note.description != newValue) {
            note.copy(description = newValue, timestamp = dateFormat(System.currentTimeMillis()))
        } else {
            note.copy(description = newValue)
        }
    }
}

sealed class UiEvent {
    data class ShowSnackBar(val message: String) : UiEvent()
    object SaveNote : UiEvent()
}