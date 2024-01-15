package com.tonghannteng.noteapp.presentation.detail

import androidx.lifecycle.ViewModel
import com.tonghannteng.noteapp.data.repository.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val repository: IRepository
) : ViewModel() {

}