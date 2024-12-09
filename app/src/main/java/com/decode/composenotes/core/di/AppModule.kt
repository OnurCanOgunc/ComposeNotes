package com.decode.composenotes.core.di

import android.app.Application
import androidx.room.Room
import com.decode.composenotes.core.data.data_source.NoteDatabase
import com.decode.composenotes.core.data.repository.RepositoryImpl
import com.decode.composenotes.core.domain.repository.Repository
import com.decode.composenotes.core.domain.use_case.DeleteNote
import com.decode.composenotes.core.domain.use_case.NoteUseCase
import com.decode.composenotes.core.domain.use_case.SearchNotes
import com.decode.composenotes.core.domain.use_case.UpdateNote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRoomDb(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            context = app,
            klass = NoteDatabase::class.java,
            name = "notes_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): Repository {
        return RepositoryImpl(db.getNoteDao())
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: Repository): NoteUseCase {
        return NoteUseCase(
            deleteNote = DeleteNote(repository = repository),
            searchNotes = SearchNotes(repository = repository),
            updateNote = UpdateNote(repository = repository)
        )
    }
}