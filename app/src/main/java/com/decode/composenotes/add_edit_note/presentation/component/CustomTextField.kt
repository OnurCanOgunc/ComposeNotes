package com.decode.composenotes.add_edit_note.presentation.component


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.decode.composenotes.ui.theme.onSurfaceVariantDark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    hint: String,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean = false,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = modifier.padding(4.dp),
        value = value,
        placeholder = {
            Text(
                text = hint,
                color = onSurfaceVariantDark
            )
        },
        onValueChange = onValueChange,
        singleLine = singleLine,
        textStyle = textStyle,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.Companion.Transparent,
            focusedBorderColor = Color.Companion.Transparent
        )
    )
}