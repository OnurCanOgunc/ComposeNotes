package com.decode.composenotes.favorites.di

import com.decode.composenotes.core.data.data_source.NoteDatabase
import com.decode.composenotes.favorites.data.FavoritesRepositoryImpl
import com.decode.composenotes.favorites.domain.repository.FavoritesRepository
import com.decode.composenotes.favorites.domain.use_case.GetFavoritesNotes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoritesModule {
    @Provides
    @Singleton
    fun provideGetFavoritesUseCase(favoriteRepository: FavoritesRepository): GetFavoritesNotes {
        return GetFavoritesNotes(
            favoritesRepository = favoriteRepository
        )
    }

    @Provides
    @Singleton
    fun provideFavoritesRepository(noteDatabase: NoteDatabase): FavoritesRepository {
        return FavoritesRepositoryImpl(noteDao = noteDatabase.getNoteDao())
    }

}