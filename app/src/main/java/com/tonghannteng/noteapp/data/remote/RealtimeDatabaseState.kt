package com.tonghannteng.noteapp.data.remote

import java.lang.Exception

/**
 * @author: Tonghann Teng
 * @since: 1/4/23
 */
sealed class RealtimeDatabaseState<T> {
    data class Success<T>(val data: T) : RealtimeDatabaseState<T>()
    data class Failure<T>(val exception: Exception) : RealtimeDatabaseState<T>()
}
