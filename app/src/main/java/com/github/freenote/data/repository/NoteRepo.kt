package com.github.freenote.data.repository

import com.github.freenote.domain.NoteDbEntity

interface NoteRepo {
    fun getNotes(): List<NoteDbEntity>
    fun put(note: NoteDbEntity)
    fun clear()
    fun delete(note: NoteDbEntity)
}