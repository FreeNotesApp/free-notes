package com.github.freenote.ui.noteslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.freenote.data.repository.NoteRepo
import com.github.freenote.domain.NoteDbEntity

class NotesListViewModel(noteRepo: NoteRepo, ) : ViewModel() {

    val notes: LiveData<List<NoteDbEntity>> = noteRepo.getNotes()

    private val _noteClickedEvent = MutableLiveData<NoteDbEntity?>()
    val noteClickedEvent: LiveData<NoteDbEntity?> = _noteClickedEvent

    private val _createNoteClickedEvent = MutableLiveData<Boolean?>()
    val createNoteClickedEvent: LiveData<Boolean?> = _createNoteClickedEvent

    private val _settingsClickedEvent = MutableLiveData<Boolean?>()
    val settingsClickedEvent: LiveData<Boolean?> = _settingsClickedEvent

    fun onNoteClicked(note: NoteDbEntity?) {
        _noteClickedEvent.value = note
    }

    fun onNoteClickedFinished() {
        _noteClickedEvent.value = null
    }

    fun onCreateNoteClicked() {
        _createNoteClickedEvent.value = true
    }

    fun onCreateNoteClickedFinished() {
        _createNoteClickedEvent.value = null
    }

    fun onSettingsClicked() {
        _settingsClickedEvent.value = true
    }

    fun onSettingsClickedFinished() {
        _settingsClickedEvent.value = null
    }
}