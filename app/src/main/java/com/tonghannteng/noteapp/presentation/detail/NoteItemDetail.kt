package com.tonghannteng.noteapp.presentation.detail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NoteItemDetail(noteId: Int) {

    Text(text = noteId.toString(), modifier = Modifier.fillMaxWidth())
}