package com.tonghannteng.noteapp.presentation.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author: Tonghann Teng
 * @since: 1/15/24
 */
@HiltViewModel
class NoteDetailEditViewModel @Inject constructor(
    private val repository: IRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val TAG = "NoteDetailViewModel"
    }

    private val noteId: String? = savedStateHandle[Argument.NOTE_ID]

    private var _noteDetailResult = MutableStateFlow<NoteUIState<Note>>(NoteUIState.Loading())
    var noteDetailResult = _noteDetailResult.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    var eventFlow = _eventFlow.asSharedFlow()

    private val _state = mutableStateOf(Note())
    val state: State<Note> = _state

    init {
        if (noteId == "-1") {
            getNewNote()
        } else {
            getNoteDetail()
        }
    }

    private fun getNewNote() {
        viewModelScope.launch(Dispatchers.Main) {
            repository.generateNoteId()
                .flowOn(Dispatchers.IO)
                .collect {
                    val note = Note(
                        id = it,
                        title = "Enter title...",
                        description = "Enter description..."
                    )
                    _state.value = note
                    _noteDetailResult.value = NoteUIState.Success(note)
                }
        }
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
                                _state.value = noteDetail.copy()
                            }
                        }

                        is RepositoryState.Failure -> {
                            _noteDetailResult.value = NoteUIState.Error(result.exception.toString())
                        }

                    }

                }
        }
    }

    private fun updateNote() {
        viewModelScope.launch(Dispatchers.Main) {
            val updateNote = _state.value
            repository.updateTodoNote(note = updateNote)
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    when (result) {
                        is RepositoryState.Success -> {
                            _eventFlow.emit(UiEvent.SaveNote)
                        }

                        is RepositoryState.Failure -> {
                            // TODO: implement error case
                        }
                    }
                }
        }
    }

    fun onEvent(event: NoteDetailEditEvent) {
        when (event) {
            is NoteDetailEditEvent.SaveNote -> {
                updateNote()
            }

            is NoteDetailEditEvent.EnterTitle -> {
                _state.value = _state.value.copy(
                    title = event.value
                )
            }

            is NoteDetailEditEvent.EnterDescription -> {
                _state.value = _state.value.copy(
                    description = event.value
                )
            }
        }
    }

    sealed class UiEvent {
        data object SaveNote : UiEvent()
    }
}
