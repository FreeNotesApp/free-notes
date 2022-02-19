package com.github.evgeniychufarnov.freenote.data

import androidx.room.*
import com.github.evgeniychufarnov.freenote.domain.NoteDbEntity
import java.util.*

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getNotesDb() : List<NoteDbEntity>

    @Query("DELETE FROM note")
    fun clear()

    @Insert
    fun put(note: NoteDbEntity)

    @Delete
    fun delete(note: NoteDbEntity)
}