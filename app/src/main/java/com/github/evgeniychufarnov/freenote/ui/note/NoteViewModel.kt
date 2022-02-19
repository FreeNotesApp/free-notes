package com.github.evgeniychufarnov.freenote.ui.note

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.evgeniychufarnov.freenote.ui.AppState
import com.github.evgeniychufarnov.freenote.data.repository.NoteRepo
import kotlinx.coroutines.*
import org.koin.java.KoinJavaComponent.inject

class NoteViewModel
    : ViewModel(), LifecycleObserver, CoroutineScope by MainScope() {

    private val noteRepo: NoteRepo by inject(NoteRepo::class.java)
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    fun getData() = liveDataToObserve

    fun getNotes() = getDataNotes()

    private fun getDataNotes() {
        liveDataToObserve.value = AppState.Loading
        launch {
            val localStorageJob = async(Dispatchers.IO) {
                noteRepo.notes
            }
            liveDataToObserve.value = AppState.SuccessListNote(localStorageJob.await())
        }
    }
}