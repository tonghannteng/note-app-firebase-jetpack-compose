package com.tonghannteng.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.tonghannteng.noteapp.data.model.Note
import com.tonghannteng.noteapp.navigation.NavigationAppHost
import com.tonghannteng.noteapp.presentation.home.NoteViewModel
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
                NoteApp()
            }
        }
    }
}

@Composable
private fun NoteApp() {
    val navController = rememberNavController()
    NavigationAppHost(navController = navController)
}
