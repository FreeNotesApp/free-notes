package com.github.freenote.ui.noteslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.freenote.data.repository.NoteRepo
import com.github.freenote.domain.NoteDbEntity
import com.github.freenote.ui.ScreenState
import kotlinx.coroutines.*

class NotesListViewModel(
    private val noteRepo: NoteRepo,
    ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _notes: MutableLiveData<ScreenState<List<NoteDbEntity>>> = MutableLiveData(ScreenState.Loading)
    val notes: LiveData<ScreenState<List<NoteDbEntity>>> = _notes

    init {
        viewModelScope.launch(ioDispatcher) {
            _notes.postValue(ScreenState.Success(noteRepo.getNotes()))
        }
    }
}