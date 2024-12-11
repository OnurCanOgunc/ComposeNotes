package com.decode.composenotes.add_edit_note.presentation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.decode.composenotes.add_edit_note.presentation.component.CustomTextField
import com.decode.composenotes.core.presentation.util.collectWithLifecyle

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun AddEditNoteScreen(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    noteId: Int,
    viewModel: AddEditNoteViewModel = hiltViewModel(),
    navigateUp: () -> Unit
) {

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.getNote(noteId)
    }

    viewModel.eventFlow.collectWithLifecyle {
        when (it) {
            is UiEvent.ShowSnackBar -> {
                snackbarHostState.showSnackbar(it.message)
            }

            UiEvent.SaveNote -> {
                navigateUp()
            }
        }
    }

    with(sharedTransitionScope) {
        Scaffold(
            modifier = modifier.imePadding(),
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = if (noteId == -1) "Add Note" else "Edit Note")
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    navigationIcon = {
                        IconButton(
                            onClick = navigateUp
                        ) {
                            Icon(
                                modifier = Modifier.size(30.dp),
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                viewModel.onEvent(AddEditNoteEvent.SaveNote(viewModel.note))
                            }
                        ) {
                            Icon(
                                modifier = Modifier.size(30.dp),
                                imageVector = Icons.Outlined.Save,
                                contentDescription = "Save"
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                CustomTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .sharedElement(
                            state = rememberSharedContentState(key = "title/${viewModel.note.title}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                        ),
                    value = viewModel.note.title,
                    hint = "Title",
                    textStyle = MaterialTheme.typography.titleLarge,
                    singleLine = true,
                    onValueChange = {
                        viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it))
                    },
                )
                CustomTextField(
                    modifier = Modifier
                        .fillMaxSize()
                        .sharedElement(
                            state = rememberSharedContentState(key = "description/${viewModel.note.description}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                        ),
                    value = viewModel.note.description.orEmpty(),
                    hint = "You can start writing...",
                    textStyle = MaterialTheme.typography.bodyLarge,
                    onValueChange = {
                        viewModel.onEvent(AddEditNoteEvent.EnteredDescription(it))
                    },
                )
            }
        }
    }
}