package com.decode.composenotes.favorites.presentation


import android.util.Log
import androidx.lifecycle.viewModelScope
import com.decode.composenotes.core.domain.use_case.NoteUseCase
import com.decode.composenotes.core.presentation.helper_viewmodel.BaseUIEvent
import com.decode.composenotes.core.presentation.helper_viewmodel.NoteHelperViewModel
import com.decode.composenotes.core.presentation.helper_viewmodel.UIState
import com.decode.composenotes.favorites.domain.use_case.GetFavoritesNotes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val noteUseCase: NoteUseCase,
    private val getFavoritesNotes: GetFavoritesNotes,
) : NoteHelperViewModel<BaseUIEvent>(noteUseCase) {

    override fun loadData() {
        viewModelScope.launch {
            getFavoritesNotes()
                .catch {
                    setData(UIState.Error(message = it.message.toString()))
                }.collect {
                    setData(UIState.Content(favorites = it))
                }
        }
    }

    override fun searchItems(query: String) {
        searchQuery = query
        viewModelScope.launch {
            noteUseCase.searchNotes(query)
                .catch {
                    setSearchData(UIState.Error(message = it.message.toString()))
                }.collect {
                     setSearchData(UIState.Content(searchNotes = it.filter { it.isFavorite }))
                    Log.d("FavoriteViewModel", "searchNotes: ${it}")
                }
        }
    }

}