package com.tonghannteng.noteapp.presentation

sealed class NoteUIState<T> {
    data class Success<T>(val data: T) : NoteUIState<T>()
    data class Error<T>(val error: String) : NoteUIState<T>()
    class Loading<T> : NoteUIState<T>()
}
