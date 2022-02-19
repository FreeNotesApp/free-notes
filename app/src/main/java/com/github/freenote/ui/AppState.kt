package com.github.freenote.ui

import com.github.freenote.domain.NoteDbEntity


sealed class AppState {
    data class SuccessListNote(val data: List<NoteDbEntity>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
