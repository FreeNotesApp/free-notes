package com.github.freenote.ui.utils

import com.github.freenote.R

enum class NotesColors {
    DEFAULT, YELLOW, GREEN, RED
}

fun getNoteColorId(notesColors: NotesColors): Int {
    return when (notesColors) {
        NotesColors.DEFAULT -> R.color.white
        NotesColors.YELLOW -> R.color.yellow_light
        NotesColors.GREEN -> R.color.green_light
        NotesColors.RED -> R.color.red_light
    }
}