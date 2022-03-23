package com.github.freenote.ui.utils

import com.github.freenote.R
import com.github.freenote.ThemeColor

enum class NotesColor {
    COLOR_1, COLOR_2, COLOR_3, COLOR_4
}

fun getNoteColorId(notesColors: NotesColor): Int {
    if (ThemeColor.themeColor == R.style.Theme_FreeNote) {
        return when (notesColors) {
            NotesColor.COLOR_1 -> R.color.white
            NotesColor.COLOR_2 -> R.color.yellow_light
            NotesColor.COLOR_3 -> R.color.green_light
            NotesColor.COLOR_4 -> R.color.red_light
        }
    }
    if (ThemeColor.themeColor == R.style.Theme_FreeNote_Dark_A_Little) {
        return when (notesColors) {
            NotesColor.COLOR_1 -> R.color.red_purple
            NotesColor.COLOR_2 -> R.color.yellow_big
            NotesColor.COLOR_3 -> R.color.green_swamp
            NotesColor.COLOR_4 -> R.color.brown
        }
    }
    if (ThemeColor.themeColor == R.style.Theme_FreeNote_Dark) {
        return when (notesColors) {
            NotesColor.COLOR_1 -> R.color.gray_1
            NotesColor.COLOR_2 -> R.color.gray_2
            NotesColor.COLOR_3 -> R.color.gray_3
            NotesColor.COLOR_4 -> R.color.gray_4
        }
    }
    return 0
}