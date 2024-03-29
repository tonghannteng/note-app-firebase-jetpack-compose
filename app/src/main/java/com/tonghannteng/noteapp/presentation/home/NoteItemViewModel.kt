package com.tonghannteng.noteapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tonghannteng.noteapp.data.model.Note
import com.tonghannteng.noteapp.data.repository.IRepository
import com.tonghannteng.noteapp.data.repository.RepositoryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author: Tonghann Teng
 * @since: 1/6/24
 */
@HiltViewModel
class NoteItemViewModel @Inject constructor(
    private val repository: IRepository
) : ViewModel() {

    companion object {
        private const val TAG = "NoteViewModel"
    }

    private var _noteItemResult = MutableStateFlow<NoteUIState<List<Note>>>(NoteUIState.Loading())
    var noteItemResult = _noteItemResult.asStateFlow()

    private var _deleteNoteResult = MutableStateFlow<NoteUIState<String>>(NoteUIState.Loading())
    var deleteNoteResult = _deleteNoteResult.asStateFlow()

    init {
        getTodoNote()
    }

    private fun getTodoNote() {
        viewModelScope.launch(Dispatchers.Main) {
            repository.getTodoNote()
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    when (result) {
                        is RepositoryState.Success -> {
                            _noteItemResult.value = NoteUIState.Success(result.data)
                        }

                        is RepositoryState.Failure -> {
                            _noteItemResult.value = NoteUIState.Error(result.exception.toString())
                        }
                    }

                }
        }
    }

    fun onItemDelete(id: String) {
        viewModelScope.launch(Dispatchers.Main) {
            repository.deleteTodoNoteById(noteId = id)
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    when (result) {
                        is RepositoryState.Success -> {
                            _deleteNoteResult.emit(NoteUIState.Success(result.data))
                        }
                        is RepositoryState.Failure -> {
                            _deleteNoteResult.emit(NoteUIState.Error(result.exception.toString()))
                        }
                    }
                }
        }
    }

}
