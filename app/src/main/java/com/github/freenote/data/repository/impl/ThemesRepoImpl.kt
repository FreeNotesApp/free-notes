package com.github.freenote.data.repository.impl

import android.content.Context
import android.content.SharedPreferences
import com.github.freenote.data.repository.AppTheme
import com.github.freenote.data.repository.ThemesRepo

private const val THEME_PREFS = "THEME_PREFS"
private const val APP_THEME_KEY = "APP_THEME_KEY"

class ThemesRepoImpl(
    private val context: Context
) : ThemesRepo {

    override fun getAppTheme() : AppTheme {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(
            THEME_PREFS,
            Context.MODE_PRIVATE
        )
        val position = sharedPreferences.getInt(APP_THEME_KEY, 0)

        return if (position < AppTheme.values().size) {
            AppTheme.values()[position]
        } else {
            AppTheme.LIGHT
        }
    }

    override fun setAppTheme(theme: AppTheme) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(
            THEME_PREFS,
            Context.MODE_PRIVATE
        )
        sharedPreferences.edit().putInt(APP_THEME_KEY, theme.ordinal).apply()
    }
}