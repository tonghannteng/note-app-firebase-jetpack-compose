package com.tonghannteng.noteapp.presentation.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tonghannteng.noteapp.navigation.Route
import kotlinx.coroutines.launch

/**
 * @author: Tonghann Teng
 * @since: 1/15/24
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun NoteItemScreen(
    navController: NavController,
    onItemClicked: (noteId: String) -> Unit
) {

    val TAG = "NoteItemScreen"
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val noteItemViewModel = hiltViewModel<NoteItemViewModel>()
    val noteItems = noteItemViewModel.noteItemResult.collectAsState().value
    val deleteNote = noteItemViewModel.deleteNoteResult.collectAsState().value

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) {
        when (deleteNote) {
            is NoteUIState.Success -> {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(message = "Note Id ${deleteNote.data} has deleted")
                }

            }

            is NoteUIState.Error -> {
                Log.e(TAG, deleteNote.error)
            }

            is NoteUIState.Loading -> {
                Log.d(TAG, deleteNote.toString())
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        when (noteItems) {
            is NoteUIState.Error -> {
                Log.e(TAG, noteItems.error)
            }

            is NoteUIState.Loading -> {
                CircularProgressIndicator()
            }

            is NoteUIState.Success -> {
                Scaffold(
                    floatingActionButton = {
                        FloatingActionButton(onClick = {
                            navController.navigate("${Route.DETAIL}/-1")
                        }) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
                        }
                    }
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(2.dp)
                    ) {
                        items(noteItems.data.size) {
                            NoteItemCard(
                                note = noteItems.data[it],
                                modifier = Modifier,
                                onItemClicked = onItemClicked,
                                onItemDeleted = {
                                    noteItemViewModel.onItemDelete(noteItems.data[it].id)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
