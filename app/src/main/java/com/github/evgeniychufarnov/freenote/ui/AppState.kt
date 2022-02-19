package com.github.evgeniychufarnov.freenote.ui

import com.github.evgeniychufarnov.freenote.domain.NoteDbEntity


sealed class AppState {
    data class SuccessListNote(val data: List<NoteDbEntity>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
