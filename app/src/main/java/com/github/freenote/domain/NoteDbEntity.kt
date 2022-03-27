package com.github.freenote.domain

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.freenote.ui.utils.NotesColor
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "note")
data class NoteDbEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "text") var text: String,
    @ColumnInfo(name = "date") var date: Long,
    @ColumnInfo(name = "color") var color: NotesColor,
) : Parcelable