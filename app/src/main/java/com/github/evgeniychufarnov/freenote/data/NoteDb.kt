package com.github.evgeniychufarnov.freenote.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.evgeniychufarnov.freenote.domain.NoteDbEntity

@Database(
    entities = [NoteDbEntity::class],
    version = 1
)

abstract class NoteDb : RoomDatabase() {
    abstract fun noteDao() : NoteDao
}