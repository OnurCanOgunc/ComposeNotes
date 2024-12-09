package com.decode.composenotes.favorites.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.decode.composenotes.core.presentation.EmptyAndErrorScreen
import com.decode.composenotes.core.presentation.component.NoteList
import com.decode.composenotes.core.presentation.helper_viewmodel.BaseUIEvent
import com.decode.composenotes.core.presentation.helper_viewmodel.UIState
import com.decode.composenotes.favorites.presentation.component.TopAppBar
import com.decode.notesappcompose.core.presentation.navigation.Screens

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier,
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    onNavigate: (Screens) -> Unit,
    navigateUp: () -> Unit
) {
    val favoriteState by favoriteViewModel.uiState.collectAsStateWithLifecycle()
    val searchState by favoriteViewModel.searchState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    Scaffold(
        modifier = modifier.clickable(
            onClick = {
                focusManager.clearFocus()
            },
            interactionSource = interactionSource,
            indication = null
        ),
        topBar = {
            TopAppBar(
                navigateUp = navigateUp,
                onSearch = { favoriteViewModel.onEvent(BaseUIEvent.SearchQuery(it)) },
                onClearSearch = { favoriteViewModel.onEvent(BaseUIEvent.ClearSearch) }
            )
        }
    ) { innerPadding ->
        AnimatedContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            targetState = if (favoriteViewModel.searchQuery.isNotEmpty()) searchState else favoriteState,
            label = "",
            transitionSpec = {
                fadeIn(animationSpec = tween(300, easing = LinearEasing)) togetherWith fadeOut(
                    animationSpec = tween(300, easing = LinearEasing)
                )
            }
        ) { state ->
            when (state) {
                is UIState.Error -> EmptyAndErrorScreen(message = state.message)
                is UIState.Loading -> EmptyAndErrorScreen()
                is UIState.Content -> {
                    val notes = if (favoriteViewModel.searchQuery.isNotEmpty()) {
                        state.searchNotes
                    } else {
                        state.favorites
                    }
                    if (notes.isEmpty()) {
                        EmptyAndErrorScreen(message = "Create your first favorite note")
                    }
                    NoteList(
                        animatedVisibilityScope = animatedVisibilityScope,
                        sharedTransitionScope = sharedTransitionScope,
                        noteList = notes,
                        onEditNoteClick = { onNavigate(Screens.Note.AddEditNoteScreen(it)) },
                        onDeleteClick = { note -> favoriteViewModel.onEvent(BaseUIEvent.DeleteItem(note)) },
                        onFavoriteClick = { isFavorite ->
                            favoriteViewModel.onEvent(
                                BaseUIEvent.ToggleFavorite(
                                    isFavorite
                                )
                            )
                        }
                    )
                }
            }
        }
    }
}