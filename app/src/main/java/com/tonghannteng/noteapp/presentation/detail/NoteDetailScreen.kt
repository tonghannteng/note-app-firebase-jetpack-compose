package com.tonghannteng.noteapp.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tonghannteng.noteapp.data.model.Note

/**
 * @author: Tonghann Teng
 * @since: 1/15/24
 */
@Composable
fun NoteDetailScreen(
    note: Note
) {

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "ID: ${note.id}",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "Title: ${note.title}",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "Description: ${note.description}",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
