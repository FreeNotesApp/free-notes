package com.github.freenote.ui.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.freenote.data.repository.NoteRepo
import com.github.freenote.domain.NoteDbEntity
import com.github.freenote.ui.utils.NotesColor
import kotlinx.coroutines.*
import java.util.*
import kotlin.concurrent.thread

class NoteViewModel (
    private val noteRepo: NoteRepo,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private var uuid: String? = null
    private var date: Long? = null
    private var isDeleting: Boolean = false

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    private val _color = MutableLiveData<NotesColor>()
    val color: LiveData<NotesColor> = _color

    private val _navigateBackEvent = MutableLiveData<Boolean?>()
    val navigateBackEvent: LiveData<Boolean?> = _navigateBackEvent

    private val _colorPanelState = MutableLiveData(false)
    val colorPanelState: LiveData<Boolean> = _colorPanelState

    fun setNote(note: NoteDbEntity?) {
        note?.let { setFields(it) }
    }

    fun onTitleChanged(newTitle: String) {
        _title.value = newTitle
    }

    fun onTextChanged(newText: String) {
        if (text.value != newText) {
            _text.value = newText
        }
    }

    fun onColorChanged(color: NotesColor) {
        _color.value = color
    }

    fun onNoteSave(defaultTitle: String) {
        if (validateNote()) {
            saveNote(defaultTitle)
        }
    }

    private fun validateNote(): Boolean {
        return !text.value.isNullOrBlank() && !isDeleting
    }

    private fun saveNote(defaultTitle: String) {
        val note = createNote(defaultTitle)
        setFields(note)

        viewModelScope.launch(ioDispatcher) {
            noteRepo.put(note)
        }
    }

    private fun setFields(note: NoteDbEntity) {
        uuid = note.id
        date = note.date
        _title.value = note.title
        _text.value = note.text
        _color.value = note.color
    }

    private fun createNote(defaultTitle: String) : NoteDbEntity {
        return NoteDbEntity(
            id = uuid ?: UUID.randomUUID().toString(),
            title = title.value ?: defaultTitle,
            text = text.value.orEmpty(),
            date = date ?: System.currentTimeMillis(),
            color = color.value ?: NotesColor.DEFAULT,
            number = "0"
        )
    }

    fun onNoteDelete() {
        isDeleting = true

        uuid?.let {
            viewModelScope.launch(Dispatchers.IO) {
                noteRepo.delete(it)
            }
        }

        _navigateBackEvent.value = true
    }

    fun onChangeColorPanelState() {
        colorPanelState.value?.let {
            _colorPanelState.value = !it
        }
    }

    fun onNavigateBackFinished() {
        _navigateBackEvent.value = null
    }
}