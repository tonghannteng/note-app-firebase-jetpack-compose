package com.tonghannteng.noteapp.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tonghannteng.noteapp.data.model.Note
import com.tonghannteng.noteapp.data.repository.IRepository
import com.tonghannteng.noteapp.data.repository.RepositoryState
import com.tonghannteng.noteapp.navigation.Argument
import com.tonghannteng.noteapp.presentation.home.NoteUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author: Tonghann Teng
 * @since: 1/15/24
 */
@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val repository: IRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val TAG = "NoteDetailViewModel"
    }

    private val noteId: Int? = savedStateHandle[Argument.NOTE_ID]

    private var _noteDetailResult = MutableStateFlow<NoteUIState<Note>>(NoteUIState.Loading())
    var noteDetailResult = _noteDetailResult.asStateFlow()

    init {
        getNoteDetail()
    }

    private fun getNoteDetail() {
        viewModelScope.launch(Dispatchers.Main) {
            repository.getTodoNote()
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    when (result) {
                        is RepositoryState.Success -> {
                            val noteDetail = result.data.find { it.id == noteId }
                            if (noteDetail == null) {
                                _noteDetailResult.value = NoteUIState.Error("Note ID Note Found")
                            } else {
                                _noteDetailResult.value = NoteUIState.Success(noteDetail)
                            }
                        }

                        is RepositoryState.Failure -> {
                            _noteDetailResult.value = NoteUIState.Error(result.exception.toString())
                        }
                    }

                }
        }
    }
}
