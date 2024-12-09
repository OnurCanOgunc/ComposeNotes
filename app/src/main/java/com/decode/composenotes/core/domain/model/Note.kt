package com.decode.composenotes.core.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
//@Fts4
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String?,
    val timestamp: String,
    val isFavorite: Boolean = false
)