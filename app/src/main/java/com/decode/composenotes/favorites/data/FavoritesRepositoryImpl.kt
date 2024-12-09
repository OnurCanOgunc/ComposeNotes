package com.decode.composenotes.favorites.data

import com.decode.composenotes.core.data.data_source.NoteDao
import com.decode.composenotes.core.domain.model.Note
import com.decode.composenotes.favorites.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(private val noteDao: NoteDao) :
    FavoritesRepository {
    override fun getFavorites(): Flow<List<Note>> {
        return noteDao.getFavorites()
    }
}