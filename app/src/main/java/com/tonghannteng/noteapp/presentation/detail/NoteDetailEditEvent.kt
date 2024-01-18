package com.tonghannteng.noteapp.presentation.detail

/**
 * @author: Tonghann Teng
 * @since: 1/17/24
 */
sealed class NoteDetailEditEvent {
    data class EnterDescription(val value: String): NoteDetailEditEvent()
    data object SaveNote: NoteDetailEditEvent()
}
