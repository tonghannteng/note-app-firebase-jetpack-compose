package com.tonghannteng.noteapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tonghannteng.noteapp.data.repository.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author: Tonghann Teng
 * @since: 1/6/24
 */
@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: IRepository
) : ViewModel() {

    companion object {
        private const val TAG = "NoteViewModel"
    }

    init {
        getTodoNote()
    }

    private fun getTodoNote() {
        viewModelScope.launch(Dispatchers.Main) {
            repository.getTodoNote()
                .flowOn(Dispatchers.IO)
                .collect {
                    Log.d(TAG, it.toString())
                }
        }
    }
}
