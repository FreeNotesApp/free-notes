package com.github.freenote.ui.noteslist

import androidx.lifecycle.*
import com.github.freenote.data.repository.NoteRepo
import com.github.freenote.domain.NoteDbEntity
import com.github.freenote.ui.base.ScreenState
import kotlinx.coroutines.*
import org.koin.java.KoinJavaComponent.inject

class NotesListViewModel(
    private val noteRepo: NoteRepo,
    ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _notes: MutableLiveData<ScreenState<LiveData<List<NoteDbEntity>>>> = MutableLiveData(
        ScreenState.Loading)
    val notes: LiveData<ScreenState<LiveData<List<NoteDbEntity>>>> = _notes

    private val _noteClickedEvent = MutableLiveData<NoteDbEntity?>()
    val noteClickedEvent: LiveData<NoteDbEntity?> = _noteClickedEvent

    init {
        viewModelScope.launch(ioDispatcher) {
            _notes.postValue(ScreenState.Success(noteRepo.getNotes()))
        }
    }

    fun onNoteClicked(note: NoteDbEntity) {
        _noteClickedEvent.value = note
    }

    fun onNoteClickedFinished() {
        _noteClickedEvent.value = null
    }
}