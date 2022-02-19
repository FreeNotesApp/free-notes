package com.github.evgeniychufarnov.freenote.ui.open

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.evgeniychufarnov.freenote.data.repository.NoteRepo
import com.github.evgeniychufarnov.freenote.ui.AppState
import com.github.evgeniychufarnov.freenote.domain.NoteDbEntity
import kotlinx.coroutines.*
import org.koin.java.KoinJavaComponent

class OpenNoteViewModel
    : ViewModel(), LifecycleObserver, CoroutineScope by MainScope() {

    private val noteRepo: NoteRepo by KoinJavaComponent.inject(NoteRepo::class.java)
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    fun getData() = liveDataToObserve

    fun getNote(noteDbEntity: NoteDbEntity) = getDataNote(noteDbEntity)

    private fun getDataNote(noteDbEntity: NoteDbEntity) {
        liveDataToObserve.value = AppState.Loading
    }
}