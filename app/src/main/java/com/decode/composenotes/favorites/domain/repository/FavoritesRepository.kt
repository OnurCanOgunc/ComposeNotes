package com.decode.composenotes.favorites.domain.repository

import com.decode.composenotes.core.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun getFavorites(): Flow<List<Note>>
}