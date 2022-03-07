package com.github.freenote.ui.noteslist

import androidx.lifecycle.*
import com.github.freenote.R
import com.github.freenote.data.repository.NoteRepo
import com.github.freenote.domain.NoteDbEntity
import com.github.freenote.ui.base.ScreenState
import kotlinx.coroutines.*
import org.koin.java.KoinJavaComponent.inject
import java.util.*

class NotesListViewModel(
    private val noteRepo: NoteRepo,
) : ViewModel() {

    val notes: LiveData<List<NoteDbEntity>> = noteRepo.getNotes()

    private val _noteClickedEvent = MutableLiveData<NoteDbEntity?>()
    val noteClickedEvent: LiveData<NoteDbEntity?> = _noteClickedEvent

    fun onNoteClicked(note: NoteDbEntity) {
        _noteClickedEvent.value = note
    }

    fun onNoteClickedFinished() {
        _noteClickedEvent.value = null
    }
}