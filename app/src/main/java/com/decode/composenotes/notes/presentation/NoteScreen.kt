package com.decode.composenotes.notes.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.decode.composenotes.core.presentation.EmptyAndErrorScreen
import com.decode.composenotes.core.presentation.helper_viewmodel.BaseUIEvent
import com.decode.composenotes.core.presentation.helper_viewmodel.UIState
import com.decode.composenotes.notes.presentation.component.CustomExpandableFAB
import com.decode.composenotes.notes.presentation.component.NoteTopBar
import com.decode.composenotes.core.presentation.component.NoteList
import com.decode.composenotes.core.presentation.helper_viewmodel.BasedUIEvent
import com.decode.composenotes.core.presentation.navigation.Screens
import kotlin.text.isNotEmpty

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NoteScreen(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    noteState: UIState,
    searchState: UIState,
    onUIEvent: (BasedUIEvent) -> Unit,
    searchQuery: String,
    isDarkMode: Boolean,
    onNavigate: (Screens) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    Scaffold(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .clickable(
                onClick = {
                    focusManager.clearFocus()
                },
                interactionSource = interactionSource,
                indication = null
            ),
        topBar = {
            NoteTopBar(
                onSearch = {
                    onUIEvent(BaseUIEvent.SearchQuery(it))
                },
                onClearSearch = {
                    onUIEvent(BaseUIEvent.ClearSearch)
                },
                isDarkMode = isDarkMode,
                toggleClick = {
                    onUIEvent(NotesUIEvent.DarkModeActive(it))
                }
            )
        },
        floatingActionButton = {
            CustomExpandableFAB() {
                when (it.text) {
                    "Create" -> {
                        onNavigate(Screens.Note.AddEditNoteScreen())
                    }

                    "Favorite" -> {
                        onNavigate(Screens.Favorite.FavoriteScreen)
                    }
                }
            }
        }
    ) { innerPadding ->
        AnimatedContent(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            targetState = if (searchQuery.isNotEmpty()) searchState else noteState,
            label = "",
            contentKey = {
                when (it) {
                    is UIState.Content -> it.notes.hashCode()
                    else -> 0
                }
            },
            transitionSpec = {
                fadeIn(animationSpec = tween(300, easing = LinearEasing)) togetherWith fadeOut(
                    animationSpec = tween(300, easing = LinearEasing)
                )
            }
        ) { state ->
            RenderState(
                state = state,
                onNavigate = onNavigate,
                onUIEvent = onUIEvent,
                animatedVisibilityScope = animatedVisibilityScope,
                sharedTransitionScope = sharedTransitionScope
            )
        }

    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun RenderState(
    state: UIState,
    onNavigate: (Screens) -> Unit,
    onUIEvent: (BasedUIEvent) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
) {
    when (state) {
        is UIState.Content -> {
            val notes = if (state.searchNotes.isNotEmpty()) state.searchNotes else state.notes
            if (notes.isEmpty()) {
                EmptyAndErrorScreen()
            } else {
                NoteList(
                    animatedVisibilityScope = animatedVisibilityScope,
                    sharedTransitionScope = sharedTransitionScope,
                    noteList = notes,
                    onEditNoteClick = {
                        onNavigate(Screens.Note.AddEditNoteScreen(it))
                    },
                    onDeleteClick = { note ->
                        onUIEvent(BaseUIEvent.DeleteItem(note))
                    },
                    onFavoriteClick = { note ->
                        onUIEvent(BaseUIEvent.ToggleFavorite(note))
                    }
                )
            }
        }

        is UIState.Error -> EmptyAndErrorScreen(message = state.message)
        UIState.Loading -> EmptyAndErrorScreen()
    }
}