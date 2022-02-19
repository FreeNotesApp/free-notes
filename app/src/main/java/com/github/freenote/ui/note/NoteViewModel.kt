package com.github.freenote.ui.note

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.freenote.ui.AppState
import com.github.freenote.data.repository.NoteRepo
import kotlinx.coroutines.*

class NoteViewModel(private val repo: NoteRepo)
    : ViewModel(), LifecycleObserver, CoroutineScope by MainScope() {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    fun getData() = liveDataToObserve

    fun getNotes() = getDataNotes()

    private fun getDataNotes() {
        liveDataToObserve.value = AppState.Loading
        launch {
            val localStorageJob = async(Dispatchers.IO) {
                repo.notes
            }
            liveDataToObserve.value = AppState.SuccessListNote(localStorageJob.await())
        }
    }
}