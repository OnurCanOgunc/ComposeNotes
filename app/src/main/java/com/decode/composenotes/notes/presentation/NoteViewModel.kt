package com.decode.composenotes.notes.presentation

import androidx.lifecycle.viewModelScope
import com.decode.composenotes.core.domain.use_case.NoteUseCase
import com.decode.composenotes.core.presentation.helper_viewmodel.BasedUIEvent
import com.decode.composenotes.core.presentation.helper_viewmodel.NoteHelperViewModel
import com.decode.composenotes.core.presentation.helper_viewmodel.UIState
import com.decode.composenotes.notes.domain.use_case.GetNotes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.text.isEmpty

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteUseCase: NoteUseCase,
    private val getNotes: GetNotes,
) : NoteHelperViewModel<BasedUIEvent>(noteUseCase) {


    override fun loadData() {
        viewModelScope.launch {
            if (searchQuery.isEmpty()) {
                getNotes().collect {
                    setData(
                        UIState.Content(
                            notes = it
                        )
                    )
                }
            } else {
                searchItems(searchQuery)  // Perform search if query is not empty
            }
        }
    }


}