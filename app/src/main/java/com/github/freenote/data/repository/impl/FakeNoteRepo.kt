package com.github.freenote.data.repository.impl

import com.github.freenote.data.repository.NoteRepo
import com.github.freenote.domain.NoteDbEntity
import com.github.freenote.ui.utils.NotesColors
import java.util.*
import kotlin.random.Random

class FakeNoteRepo : NoteRepo {
    override fun getNotes(): List<NoteDbEntity> {
        val notes: MutableList<NoteDbEntity> = mutableListOf()

        repeat(15) {
            notes.add(
                NoteDbEntity(
                    id = UUID.randomUUID().toString(),
                    title = "fake title",
                    text = "fake text",
                    date = System.currentTimeMillis(),
                    color = NotesColors.values()[Random.nextInt(NotesColors.values().size)],
                    number = "0"
                )
            )
        }

        return notes.toList()
    }

    override fun put(note: NoteDbEntity) {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun delete(note: NoteDbEntity) {
        TODO("Not yet implemented")
    }
}