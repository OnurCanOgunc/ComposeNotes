package com.decode.composenotes.favorites.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.decode.notesappcompose.core.presentation.component.SearchBar

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    onSearch: (String) -> Unit,
    onClearSearch: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(top = 35.dp, start = 10.dp, end = 10.dp, bottom = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = navigateUp) {
            Icon(
                modifier = Modifier.size(34.dp),
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }
        SearchBar(
            onSearch = onSearch,
            onClearSearch = onClearSearch
        )
    }
}