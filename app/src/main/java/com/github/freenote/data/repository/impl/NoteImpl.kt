package com.github.freenote.data.repository.impl

import com.github.freenote.data.NoteDao
import com.github.freenote.domain.NoteDbEntity
import com.github.freenote.data.repository.NoteRepo

class NoteImpl(private val noteDao: NoteDao) : NoteRepo {

    override fun getNotes(): List<NoteDbEntity> {
        return noteDao.getNotesDb()
    }

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