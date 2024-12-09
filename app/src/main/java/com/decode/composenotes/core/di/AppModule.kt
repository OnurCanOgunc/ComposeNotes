package com.decode.composenotes.core.di

import android.app.Application
import androidx.room.Room
import com.decode.notesappcompose.core.data.data_source.NoteDatabase
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
}