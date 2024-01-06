package com.tonghannteng.noteapp.data.repository

import com.tonghannteng.noteapp.data.Note
import com.tonghannteng.noteapp.data.remote.IRealtimeDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * @author: Tonghann Teng
 * @since: 1/6/24
 */
class RepositoryImpl @Inject constructor(
    private val realtimeDatabase: IRealtimeDatabase
) : IRepository {

    override fun getTodoNote(): Flow<List<Note>> {
        return flow {
            realtimeDatabase.getTodoNote().collect {
                try {
                    emit(it)
                } catch (e: Exception) {
                    // implement error
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}
