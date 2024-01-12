package com.tonghannteng.noteapp.presentation

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
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
            Log.e("NoteItemScreen", noteItems.error)
        }

        is NoteUIState.Loading -> {
            CircularProgressIndicator()
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
