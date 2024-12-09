package com.decode.composenotes.core.presentation.component


import android.util.Log
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssistantPhoto
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.graphicsLayer
import com.decode.composenotes.core.domain.model.Note
import com.decode.composenotes.core.presentation.util.showIf
import com.decode.composenotes.notes.presentation.component.ShowIcon


@OptIn(ExperimentalFoundationApi::class, ExperimentalSharedTransitionApi::class)
@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    note: Note,
    onEditNoteClick: (Int) -> Unit,
    onDeleteClick: (Note) -> Unit,
    onFavoriteClick: (Note) -> Unit
) {
    var showDeleteIcon by remember { mutableStateOf(false) }
    val animatedScale by animateFloatAsState(
        targetValue = if (showDeleteIcon) 0.90f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy),
        label = ""
    )

    with(sharedTransitionScope) {
        Card(
            modifier = modifier
                .padding(6.dp)
                .combinedClickable(
                    onClick = {
                        if (!showDeleteIcon) onEditNoteClick(note.id)
                        showDeleteIcon = false
                    },
                    onLongClick = {
                        showDeleteIcon = true
                    },
                )
                .graphicsLayer {
                    scaleX = animatedScale
                    scaleY = animatedScale
                },
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.elevatedCardElevation(4.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimaryContainer),
        ) {

            Column(
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp)

            ) {
                Text(
                    modifier = Modifier.sharedElement(
                        state = rememberSharedContentState(key = "title/${note.title}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                    ),
                    text = note.title,
                    fontSize = 23.sp,
                    color = MaterialTheme.colorScheme.surface,
                    fontWeight = FontWeight.W500,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.sharedElement(
                        state = rememberSharedContentState(key = "description/${note.description}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                    ),
                    text = note.description ?: "",
                    fontSize = 18.sp,
                    maxLines = 4,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.W400,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(
                        text = note.timestamp,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.W400,
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    ShowIcon(
                        modifier = Modifier.size(20.dp),
                        onClick = {
                            Log.d("TAG", "NoteItem: ${note.isFavorite}")
                            onFavoriteClick(note)
                            Log.d("TAG", "NoteItem: ${note.isFavorite}")
                        },
                        icon = Icons.Default.AssistantPhoto,
                        contentDescription = "Favorite",
                        tint = if (note.isFavorite) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.weight(0.1f))
                    ShowIcon(
                        modifier = Modifier.showIf(showDeleteIcon),
                        onClick = {
                            showDeleteIcon = false
                            onDeleteClick(note)
                        },
                        icon = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = MaterialTheme.colorScheme.surface
                    )

                }

            }

        }

    }
}
