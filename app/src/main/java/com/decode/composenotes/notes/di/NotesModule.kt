package com.decode.composenotes.notes.di


import com.decode.composenotes.core.data.data_source.NoteDatabase
import com.decode.composenotes.notes.data.NoteRepositoryImpl
import com.decode.composenotes.notes.domain.repository.NoteRepository
import com.decode.composenotes.notes.domain.use_case.GetNotes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotesModule {

    @Provides
    @Singleton
    fun provideGetNotesUseCase(noteRepository: NoteRepository): GetNotes {
        return GetNotes(
            noteRepository = noteRepository
        )
    }

    @Provides
    @Singleton
    fun provideNotesRepository(noteDatabase: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(noteDao = noteDatabase.getNoteDao())
    }
}