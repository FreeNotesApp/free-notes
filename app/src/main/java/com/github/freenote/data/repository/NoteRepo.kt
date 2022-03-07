package com.github.freenote.data.repository

import androidx.lifecycle.LiveData
import com.github.freenote.domain.NoteDbEntity

interface NoteRepo {
    fun getNotes(): LiveData<List<NoteDbEntity>>
    fun put(note: NoteDbEntity)
    fun clear()
    fun delete(note: NoteDbEntity)
}