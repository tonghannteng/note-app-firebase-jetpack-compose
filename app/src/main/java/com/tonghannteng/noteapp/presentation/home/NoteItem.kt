package com.tonghannteng.noteapp.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * @author: Tonghann Teng
 * @since: 1/15/24
 */
@Composable
fun NoteItemScreen(
    onItemClicked: (noteId: Int) -> Unit
) {

    val noteItemViewModel = hiltViewModel<NoteItemViewModel>()
    val noteItems = noteItemViewModel.noteItemResult.collectAsState().value

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
                    .padding(2.dp)
            ) {
                items(noteItems.data.size) {
                    NoteItemCard(
                        note = noteItems.data[it],
                        modifier = Modifier,
                        onItemClicked = onItemClicked
                    )
                }
            }
        }
    }
}
