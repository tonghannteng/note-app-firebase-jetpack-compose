package com.tonghannteng.noteapp.data.repository

import java.lang.Exception

/**
 * @author: Tonghann Teng
 * @since: 1/7/23
 */
sealed class RepositoryState<out T> {
    data class Success<T>(val data: T) : RepositoryState<T>()
    data class Failure(val exception: Exception) : RepositoryState<Nothing>()
}
