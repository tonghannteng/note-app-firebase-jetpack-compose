package com.tonghannteng.noteapp.presentation.detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.tonghannteng.noteapp.data.model.Note
import com.tonghannteng.noteapp.presentation.compenents.TransparentHintTextField
import com.tonghannteng.noteapp.presentation.home.NoteUIState

/**
 * @author: Tonghann Teng
 * @since: 1/15/24
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun NoteDetailScreen(
    viewModel: NoteDetailEditViewModel,
    note: Note
) {

    val state = viewModel.state.value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(NoteDetailEditEvent.SaveNote)
            }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Save note")
            }
        }
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
                Spacer(modifier = Modifier.height(16.dp))
                TransparentHintTextField(
                    text = note.title,
                    hint = "",
                    onValueChange = {},
                    onFocusChange = {},
                    singleLine = true,
                    textStyle = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                TransparentHintTextField(
                    text = state.description,
                    hint = "",
                    onValueChange = {
                        viewModel.onEvent(NoteDetailEditEvent.EnterDescription(it))
                    },
                    onFocusChange = {},
                    singleLine = true,
                    textStyle = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}
