package com.tonghannteng.noteapp.presentation.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.tonghannteng.noteapp.presentation.home.NoteUIState

/**
 * @author: Tonghann Teng
 * @since: 1/15/24
 */
@Composable
fun NoteItemDetail() {

    val noteDetailViewModel = hiltViewModel<NoteDetailViewModel>()
    val noteDetail = noteDetailViewModel.noteDetailResult.collectAsState().value

    when (noteDetail) {
        is NoteUIState.Error -> {

        }

        is NoteUIState.Loading -> {

        }

        is NoteUIState.Success -> {
            NoteDetailScreen(note = noteDetail.data)
        }
    }
}
