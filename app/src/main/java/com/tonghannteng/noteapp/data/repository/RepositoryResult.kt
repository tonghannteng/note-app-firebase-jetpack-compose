package com.tonghannteng.noteapp.data.repository

import java.lang.Exception

/**
 * @author: Tonghann Teng
 * @since: 1/7/23
 */
sealed class RepositoryResult<out T> {
    data class Success<T>(val data: T) : RepositoryResult<T>()
    data class Failure(val exception: Exception) : RepositoryResult<Nothing>()
}
