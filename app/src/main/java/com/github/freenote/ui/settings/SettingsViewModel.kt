package com.github.freenote.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.freenote.R
import com.github.freenote.data.repository.NoteRepo
import com.github.freenote.domain.NoteDbEntity
import com.github.freenote.ui.base.ScreenState
import com.github.freenote.ui.utils.NotesColor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingsViewModel (
    private val noteRepo: NoteRepo,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _notes: MutableLiveData<ScreenState<NoteDbEntity>> = MutableLiveData(
        ScreenState.Loading
    )
    val notes: LiveData<ScreenState<NoteDbEntity>> = _notes

    private val _noteClickedEvent = MutableLiveData<NoteDbEntity?>()
    val noteClickedEvent: LiveData<NoteDbEntity?> = _noteClickedEvent

    init {
        viewModelScope.launch(ioDispatcher) {
            //_notes.postValue(ScreenState.Success(noteRepo.getNotes()))
        }
    }
}