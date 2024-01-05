package com.tonghannteng.noteapp.data

/**
 * @author: Tonghann Teng
 * @since: 1/4/24
 */
data class Note(
    val title: String,
    val description: String,
    val timestamp: Long,
    val completed: Boolean,
    val archived: Boolean,
    val id: Int
)
