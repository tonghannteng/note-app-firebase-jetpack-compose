package com.tonghannteng.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.tonghannteng.noteapp.presentation.NoteItemScreen
import com.tonghannteng.noteapp.ui.theme.NoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author: Tonghann Teng
 * @since: 1/6/24
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
                val noteViewModel = hiltViewModel<NoteViewModel>()
                val noteItems = noteViewModel.noteItemResult.collectAsState().value
                NoteItemScreen(
                    noteItems = noteItems
                )
            }
        }
    }
}
