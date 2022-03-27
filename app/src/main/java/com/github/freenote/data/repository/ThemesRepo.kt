package com.github.freenote.data.repository

enum class AppTheme {
    LIGHT, DARKER, DARK
}

interface ThemesRepo {
    fun getAppTheme() : AppTheme
    fun setAppTheme(theme: AppTheme)
}