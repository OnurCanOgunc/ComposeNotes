package com.decode.composenotes.notes.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

data class Preferences(val isDarkMode: Boolean)

private const val PREFERENCES_NAME = "preferences"

private val Context.dataStore by preferencesDataStore(
    name = PREFERENCES_NAME
)

class PreferencesRepository @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private val dataStore = context.dataStore

    private object PreferencesKeys {
        val IS_DARK_MODE = booleanPreferencesKey("is_dark_mode")
    }

    val userPreferencesFlow: Flow<Preferences> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val isDarkMode = preferences[PreferencesKeys.IS_DARK_MODE] == true // ?: false
            Preferences(isDarkMode)
        }

    suspend fun updateIsDarkMode(isDarkMode: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_DARK_MODE] = isDarkMode
        }
    }

}