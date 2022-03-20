package com.github.freenote.ui.utils

import com.github.freenote.R

enum class NotesColor {
    DEFAULT, YELLOW, GREEN, RED
}

fun getNoteColorId(notesColors: NotesColor): Int {
    return when (notesColors) {
        NotesColor.DEFAULT -> R.color.white
        NotesColor.YELLOW -> R.color.yellow_light
        NotesColor.GREEN -> R.color.green_light
        NotesColor.RED -> R.color.red_light
    }
}