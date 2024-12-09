package com.decode.composenotes.core.presentation.helper_viewmodel

sealed interface BaseUIEvent: BasedUIEvent {
    object ClearSearch : BaseUIEvent
    data class SearchQuery(val query: String) : BaseUIEvent
    data class DeleteItem<T>(val item: T) : BaseUIEvent
    data class ToggleFavorite<T>(val item: T) : BaseUIEvent
}