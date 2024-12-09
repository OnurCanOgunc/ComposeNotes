package com.decode.composenotes.add_edit_note.di

import com.decode.composenotes.add_edit_note.data.AddEditNoteRepositoryImpl
import com.decode.composenotes.add_edit_note.domain.repository.AddEditNoteRepository
import com.decode.composenotes.add_edit_note.domain.use_case.AddNote
import com.decode.composenotes.core.data.data_source.NoteDatabase
import com.decode.composenotes.add_edit_note.domain.use_case.AddEditNoteUseCase
import com.decode.composenotes.add_edit_note.domain.use_case.GetNote

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AddEditNoteModule {

    @Provides
    @Singleton
    fun provideAddEdNoteUseCase(addEditNoteRepository: AddEditNoteRepository): AddEditNoteUseCase {
        return AddEditNoteUseCase(
            getNote = GetNote(
                addEditNoteRepository = addEditNoteRepository
            ),
            addNote = AddNote(addEditNoteRepository = addEditNoteRepository)
        )
    }

    @Provides
    @Singleton
    fun provideAddEditNoteRepository(noteDatabase: NoteDatabase): AddEditNoteRepository {
        return AddEditNoteRepositoryImpl(noteDao = noteDatabase.getNoteDao())
    }
}