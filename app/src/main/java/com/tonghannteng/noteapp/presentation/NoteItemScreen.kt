package com.tonghannteng.noteapp.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tonghannteng.noteapp.data.model.Note

@Composable
fun NoteItemScreen(
    noteItems: NoteUIState<List<Note>>
) {

    when (noteItems) {
        is NoteUIState.Error -> {

        }

        is NoteUIState.Loading -> {

        }

        is NoteUIState.Success -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                items(noteItems.data.size) {
                    NoteItemCard(
                        note = noteItems.data[it],
                        modifier = Modifier
                    )
                }
            }
        }
    }
}
