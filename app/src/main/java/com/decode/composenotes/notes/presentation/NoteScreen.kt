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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.decode.composenotes.core.presentation.EmptyAndErrorScreen
import com.decode.composenotes.core.presentation.helper_viewmodel.BaseUIEvent
import com.decode.composenotes.core.presentation.helper_viewmodel.UIState
import com.decode.composenotes.notes.presentation.component.CustomExpandableFAB
import com.decode.composenotes.notes.presentation.component.NoteTopBar
import com.decode.composenotes.core.presentation.component.NoteList
import com.decode.composenotes.core.presentation.navigation.Screens
import kotlin.text.isNotEmpty

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NoteScreen(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    noteViewModel: NoteViewModel = hiltViewModel(),
    isDarkMode: Boolean,
    onNavigate: (Screens) -> Unit,
) {
    val noteState by noteViewModel.uiState.collectAsStateWithLifecycle()
    val searchState by noteViewModel.searchState.collectAsStateWithLifecycle()
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
                    noteViewModel.onEvent(BaseUIEvent.SearchQuery(it))
                },
                onClearSearch = {
                    noteViewModel.onEvent(BaseUIEvent.ClearSearch)
                },
                isDarkMode = isDarkMode,
                toggleClick = {
                    noteViewModel.onEvent(NotesUIEvent.DarkModeActive(it))
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
                        onNavigate(Screens.Note.AddEditNoteScreen())
                    }
                }
            }
        }
    ) { innerPadding ->
        AnimatedContent(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            targetState = if (noteViewModel.searchQuery.isNotEmpty()) searchState else noteState,
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
            when (state) {
                is UIState.Content -> {
                    val notes = when {
                        noteViewModel.searchQuery.isNotEmpty() -> state.searchNotes
                        else -> state.notes
                    }
                    //NOTES BOŞ OLUCA EMPTYLİSTCSREEN GELİYOR MU ------>
                    NoteList(
                        animatedVisibilityScope = animatedVisibilityScope,
                        sharedTransitionScope = sharedTransitionScope,
                        noteList = notes,
                        onEditNoteClick = {
                            onNavigate(Screens.Note.AddEditNoteScreen(it))
                        },
                        onDeleteClick = { note ->
                           noteViewModel.onEvent(
                                BaseUIEvent.DeleteItem(
                                    note
                                )
                            )
                        },
                        onFavoriteClick = { note ->
                            noteViewModel.onEvent(
                                BaseUIEvent.ToggleFavorite(
                                    note
                                )
                            )
                        }
                    )

                }

                is UIState.Error -> {
                    EmptyAndErrorScreen(message = state.message)
                }

                UIState.Loading -> {
                    EmptyAndErrorScreen(message = "Loading")
                }
            }
        }

    }
}

