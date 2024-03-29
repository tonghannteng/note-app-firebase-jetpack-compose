package com.tonghannteng.noteapp.data.repository

import com.tonghannteng.noteapp.data.model.Note
import kotlinx.coroutines.flow.Flow

/**
 * @author: Tonghann Teng
 * @since: 1/6/24
 */
interface IRepository {
    fun getTodoNote(): Flow<RepositoryState<List<Note>>>
    fun updateTodoNote(note: Note): Flow<RepositoryState<Boolean>>
    fun deleteTodoNoteById(noteId: String): Flow<RepositoryState<String>>
    fun generateNoteId(): Flow<String>
}
