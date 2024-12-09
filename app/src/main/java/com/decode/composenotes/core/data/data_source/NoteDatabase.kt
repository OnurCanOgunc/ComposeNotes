package com.decode.notesappcompose.core.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.decode.composenotes.core.domain.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
}