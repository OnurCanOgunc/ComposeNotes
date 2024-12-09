package com.decode.composenotes.notes.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.Light
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.decode.notesappcompose.core.presentation.component.SearchBar

@Composable
fun NoteTopBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit,
    onClearSearch: () -> Unit,
    isDarkMode: Boolean,
    toggleClick: (Boolean) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(vertical = 20.dp, horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Companion.CenterHorizontally
    ) {
        Row(
            modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Companion.CenterVertically
        ) {
            Text(text = "Notes", fontSize = 32.sp, fontWeight = FontWeight.Companion.Bold)
            IconToggleButton(
                checked = isDarkMode,
                onCheckedChange = toggleClick
            ) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = if (isDarkMode) Icons.Rounded.DarkMode else Icons.Rounded.Light,
                    contentDescription = "Light Mode"
                )
            }
        }
        SearchBar(
            onSearch = onSearch,
            onClearSearch = onClearSearch,
        )
    }
}
