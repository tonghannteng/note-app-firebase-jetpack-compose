package com.tonghannteng.noteapp.data.remote

import java.lang.Exception

/**
 * @author: Tonghann Teng
 * @since: 1/4/23
 */
sealed class RealtimeDatabaseResult<T> {
    data class Success<T>(val data: T) : RealtimeDatabaseResult<T>()
    data class Failure<T>(val exception: Exception) : RealtimeDatabaseResult<T>()
}
