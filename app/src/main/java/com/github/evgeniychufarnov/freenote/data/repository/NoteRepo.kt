package com.github.evgeniychufarnov.freenote.data.repository

import com.github.evgeniychufarnov.freenote.domain.NoteDbEntity

interface NoteRepo {
    val notes: List<NoteDbEntity>
    fun put(note: NoteDbEntity)
    fun clear()
    fun delete(note: NoteDbEntity)
}