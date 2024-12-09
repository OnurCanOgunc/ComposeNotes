package com.decode.composenotes.core.presentation.helper_viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import com.decode.composenotes.core.domain.model.Note
import com.decode.composenotes.core.domain.use_case.NoteUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

abstract class NoteHelperViewModel<E : BasedUIEvent>(
    private val useCase: NoteUseCase,
) : ViewModel() {

    protected val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState = _uiState.onStart {
        loadData()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = UIState.Loading
    )

    private val _searchState = MutableStateFlow<UIState>(UIState.Loading)
    val searchState = _searchState.asStateFlow()

    var searchQuery by mutableStateOf("")
        protected set

    open fun onEvent(event: E) {
        when (event) {
            is BaseUIEvent.DeleteItem<*> -> handleDeleteItem(event.item as Note)
            is BaseUIEvent.SearchQuery -> searchItems(event.query)
            is BaseUIEvent.ClearSearch -> clearSearch()
            is BaseUIEvent.ToggleFavorite<*> -> toggleFavorite(event.item as Note)
        }
    }

    abstract fun loadData()

    fun setData(data: UIState) {
        viewModelScope.launch{
            _uiState.value = data
        }
    }
    fun setSearchData(data: UIState) {
        viewModelScope.launch{
            _searchState.value = data
        }
    }

    private fun handleDeleteItem(item: Note) {
        viewModelScope.launch {
            useCase.deleteNote(item)
        }
    }

    open fun searchItems(query: String) {
        searchQuery = query
        viewModelScope.launch {
            useCase.searchNotes(query)
                .catch {
                    _searchState.value = UIState.Error(it.message.toString())
                }
                .collect {
                    _searchState.value = UIState.Content(searchNotes = it)
                }
        }
    }

    private fun clearSearch() {
        searchQuery = ""
        loadData()
    }

    private fun toggleFavorite(item: Note) {
        viewModelScope.launch {
            useCase.updateNote(item.copy(isFavorite = !item.isFavorite))
        }
    }
}

interface BasedUIEvent