package com.tonghannteng.noteapp.data.model

/**
 * @author: Tonghann Teng
 * @since: 1/4/24
 */
data class Note(
    val title: String = "",
    val description: String = "",
    val timestamp: Long = 0,
    val completed: Boolean = false,
    val archived: Boolean = false,
    val id: Int = 0
)
