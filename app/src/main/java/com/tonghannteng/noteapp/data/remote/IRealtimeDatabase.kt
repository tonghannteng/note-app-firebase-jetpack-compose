package com.tonghannteng.noteapp.data.remote

import com.tonghannteng.noteapp.data.model.Note
import kotlinx.coroutines.flow.Flow

/**
 * @author: Tonghann Teng
 * @since: 1/4/24
 */
interface IRealtimeDatabase {
    suspend fun getTodoNote(): Flow<RealtimeDatabaseResult<List<Note>>>
}
