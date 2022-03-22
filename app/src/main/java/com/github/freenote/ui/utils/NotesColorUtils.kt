package com.github.freenote.ui.utils

import com.github.freenote.R

enum class NotesColor {
    DEFAULT, YELLOW, GREEN, RED, PURPLE, YELLOW_BIG, GREEN_SWAMP, BROWN
}

fun getNoteColorId(notesColors: NotesColor): Int {
    return when (notesColors) {
        NotesColor.DEFAULT -> R.color.white
        NotesColor.YELLOW -> R.color.yellow_light
        NotesColor.GREEN -> R.color.green_light
        NotesColor.RED -> R.color.red_light

        NotesColor.PURPLE -> R.color.red_purple
        NotesColor.YELLOW_BIG -> R.color.yellow_big
        NotesColor.GREEN_SWAMP -> R.color.green_swamp
        NotesColor.BROWN -> R.color.brown
    }
}