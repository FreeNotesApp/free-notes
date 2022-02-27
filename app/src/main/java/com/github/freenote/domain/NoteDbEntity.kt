package com.github.freenote.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.freenote.ui.utils.NotesColors

@Entity(tableName = "note")
data class NoteDbEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "date") val date: Long,
    @ColumnInfo(name = "color") val color: NotesColors,
    @ColumnInfo(name = "number") val number: String
)