package com.github.freenote.data.repository.impl

import com.github.freenote.data.NoteDao
import com.github.freenote.domain.NoteDbEntity
import com.github.freenote.data.repository.NoteRepo

class NoteImpl(private val noteDao: NoteDao) : NoteRepo {

    override val notes: List<NoteDbEntity> =
        noteDao.getNotesDb()

    override fun put(note: NoteDbEntity) {
        noteDao.put(note)
    }

    override fun clear() {
        noteDao.clear()
    }

    override fun delete(note: NoteDbEntity) {
        noteDao.delete(note)
    }
}