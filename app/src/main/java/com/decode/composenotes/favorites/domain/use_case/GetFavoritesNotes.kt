package com.decode.composenotes.favorites.domain.use_case

import com.decode.composenotes.core.domain.model.Note
import com.decode.composenotes.favorites.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoritesNotes @Inject constructor(private val favoritesRepository: FavoritesRepository) {
    operator fun invoke(): Flow<List<Note>>  {
        return favoritesRepository.getFavorites()
    }
}