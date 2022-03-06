package com.github.freenote.domain

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.freenote.ui.utils.NotesColors
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "note")
data class NoteDbEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "date") val date: Long,
    @ColumnInfo(name = "color") val color: String,
    @ColumnInfo(name = "number") val number: String
) : Parcelable