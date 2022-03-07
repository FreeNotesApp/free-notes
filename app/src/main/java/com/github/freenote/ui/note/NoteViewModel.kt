package com.github.freenote.ui.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.freenote.R
import com.github.freenote.data.repository.NoteRepo
import com.github.freenote.domain.NoteDbEntity
import com.github.freenote.ui.base.ScreenState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class NoteViewModel (
    private val noteRepo: NoteRepo,
    ioDispatcher: CoroutineDispatcher = Dispatchers.IO
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

    fun onNoteSave(text: String, boxStrokeColor: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepo.put(NoteDbEntity(
                id = UUID.randomUUID().toString(),
                title = "Заметка",
                text = text,
                date = System.currentTimeMillis(),
                color = boxStrokeColor.toString(),
                number = "0"
            ))
        }
    }

    fun onNoteReplace(note: NoteDbEntity?) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepo.put(note)
        }
    }

    fun onNoteDelete(note: NoteDbEntity?) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepo.delete(note)
        }
    }
}