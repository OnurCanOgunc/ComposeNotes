package com.decode.composenotes.core.presentation.component

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.decode.composenotes.core.domain.model.Note

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NoteList(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    noteList: List<Note>,
    onEditNoteClick: (Int) -> Unit,
    onDeleteClick: (Note) -> Unit,
    onFavoriteClick: (Note) -> Unit
) {

    LazyVerticalStaggeredGrid(
        modifier = modifier.fillMaxSize(),
        columns = StaggeredGridCells.Adaptive(200.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(noteList, key = { it.id }
        ) { note ->
            NoteItem(
                animatedVisibilityScope = animatedVisibilityScope,
                sharedTransitionScope = sharedTransitionScope,
                note = note,
                onEditNoteClick = onEditNoteClick,
                onDeleteClick = onDeleteClick,
                onFavoriteClick = onFavoriteClick
            )
        }

    }
}