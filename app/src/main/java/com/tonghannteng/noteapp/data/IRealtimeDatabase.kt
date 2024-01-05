package com.tonghannteng.noteapp.data

import kotlinx.coroutines.flow.Flow

/**
 * @author: Tonghann Teng
 * @since: 1/4/24
 */
interface IRealtimeDatabase {

    suspend fun getTodoNote(): Flow<List<Note>>
}