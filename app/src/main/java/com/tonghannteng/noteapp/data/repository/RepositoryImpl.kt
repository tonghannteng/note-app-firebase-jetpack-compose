package com.tonghannteng.noteapp.data.repository

import com.tonghannteng.noteapp.data.model.Note
import com.tonghannteng.noteapp.data.remote.IRealtimeDatabase
import com.tonghannteng.noteapp.data.remote.RealtimeDatabaseState
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

    override fun getTodoNote(): Flow<RepositoryState<List<Note>>> {
        return flow {
            realtimeDatabase.getTodoNote().collect { result ->
                try {
                    when (result) {
                        is RealtimeDatabaseState.Success -> {
                            emit(RepositoryState.Success(result.data))
                        }

                        is RealtimeDatabaseState.Failure -> {
                            emit(RepositoryState.Failure(result.exception))
                        }
                    }

                } catch (e: Exception) {
                    emit(RepositoryState.Failure(e))
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}
