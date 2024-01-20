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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tonghannteng.noteapp.navigation.Route

/**
 * @author: Tonghann Teng
 * @since: 1/15/24
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NoteItemScreen(
    navController: NavController,
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

        else -> {}
    }
}
