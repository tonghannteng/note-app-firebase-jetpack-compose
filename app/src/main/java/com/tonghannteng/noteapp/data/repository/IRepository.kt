package com.tonghannteng.noteapp.data.repository

import com.tonghannteng.noteapp.data.Note
import kotlinx.coroutines.flow.Flow

/**
 * @author: Tonghann Teng
 * @since: 1/6/24
 */
interface IRepository {
    fun getTodoNote(): Flow<List<Note>>
}
