package com.github.freenote.ui.utils

import com.github.freenote.R
import com.github.freenote.data.repository.AppTheme

enum class NotesColor {
    COLOR_1, COLOR_2, COLOR_3, COLOR_4
}

fun getNoteColorId(color: NotesColor, theme: AppTheme): Int {
    return when (theme) {
        AppTheme.LIGHT -> {
            when (color) {
                NotesColor.COLOR_1 -> R.color.white
                NotesColor.COLOR_2 -> R.color.yellow_light
                NotesColor.COLOR_3 -> R.color.green_light
                NotesColor.COLOR_4 -> R.color.red_light
            }
        }
        AppTheme.DARKER -> {
            when (color) {
                NotesColor.COLOR_1 -> R.color.red_purple
                NotesColor.COLOR_2 -> R.color.yellow_big
                NotesColor.COLOR_3 -> R.color.green_swamp
                NotesColor.COLOR_4 -> R.color.brown
            }
        }
        AppTheme.DARK -> {
            when (color) {
                NotesColor.COLOR_1 -> R.color.gray_1
                NotesColor.COLOR_2 -> R.color.gray_2
                NotesColor.COLOR_3 -> R.color.gray_3
                NotesColor.COLOR_4 -> R.color.gray_4
            }
        }
    }
}