package com.github.freenote.data


import androidx.lifecycle.LiveData
import androidx.room.*
import com.github.freenote.domain.NoteDbEntity

@Dao
interface NoteDao {
    @Query("SELECT * FROM note ORDER by date DESC")
    fun getNotesDb() : LiveData<List<NoteDbEntity>>

    @Query("DELETE FROM note")
    fun clear()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun put(note: NoteDbEntity)

    @Query("DELETE FROM note WHERE id = (:id)")
    fun delete(id: String)
}