package com.github.freenote.ui.open

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.freenote.ui.AppState
import com.github.freenote.domain.NoteDbEntity
import com.github.freenote.data.repository.NoteRepo
import kotlinx.coroutines.*
import org.koin.java.KoinJavaComponent.inject

class OpenNoteViewModel
    : ViewModel(), LifecycleObserver, CoroutineScope by MainScope() {

    private val noteRepo: NoteRepo by inject(NoteRepo::class.java)
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    fun getData() = liveDataToObserve

    fun getNote(noteDbEntity: NoteDbEntity) = getDataNote(noteDbEntity)

    private fun getDataNote(noteDbEntity: NoteDbEntity) {
        liveDataToObserve.value = AppState.Loading
    }
}