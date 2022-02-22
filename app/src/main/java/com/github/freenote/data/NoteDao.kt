package com.github.freenote.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.github.freenote.domain.NoteDbEntity

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getNotesDb() : List<NoteDbEntity>

    @Query("DELETE FROM note")
    fun clear()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun put(note: NoteDbEntity)

    @Delete
    fun delete(note: NoteDbEntity)
}