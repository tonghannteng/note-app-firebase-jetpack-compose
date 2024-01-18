package com.tonghannteng.noteapp.presentation.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tonghannteng.noteapp.presentation.home.NoteUIState
import kotlinx.coroutines.flow.collectLatest

/**
 * @author: Tonghann Teng
 * @since: 1/15/24
 */
@Composable
fun NoteItemDetail(
    navController: NavController,
    viewModel: NoteDetailEditViewModel
) {

    val noteDetail = viewModel.noteDetailResult.collectAsState().value

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is NoteDetailEditViewModel.UiEvent.SaveNote -> {
                    navController.navigateUp()
                }
            }
        }
    }

    when (noteDetail) {
        is NoteUIState.Error -> {

        }

        is NoteUIState.Loading -> {

        }

        is NoteUIState.Success -> {
            NoteDetailScreen(
                viewModel = viewModel,
                note = noteDetail.data
            )
        }
    }
}
