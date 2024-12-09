package com.decode.composenotes.core.presentation.navigation.nav_graph

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.decode.composenotes.add_edit_note.presentation.AddEditNoteScreen
import com.decode.composenotes.notes.presentation.NoteScreen
import com.decode.composenotes.core.presentation.navigation.Screens

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.notes(
    navController: NavController,
    sharedTransitionScope: SharedTransitionScope,
    isDarkMode: Boolean = false,
) {
    navigation<Screens.Note>(startDestination = Screens.Note.NoteScreen) {
        composable<Screens.Note.NoteScreen> {
            NoteScreen(
                animatedVisibilityScope = this,
                sharedTransitionScope = sharedTransitionScope,
                onNavigate = {
                    navController.navigate(it)
                },
                isDarkMode = isDarkMode
            )
        }
        composable<Screens.Note.AddEditNoteScreen> {
            val argument = it.toRoute<Screens.Note.AddEditNoteScreen>()
            AddEditNoteScreen(
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = this,
                noteId = argument.noteId,
                navigateUp = {
                    navController.navigateUp()
                }
            )

        }
    }
}

