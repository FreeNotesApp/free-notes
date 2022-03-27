package com.github.freenote.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.freenote.R
import com.github.freenote.data.repository.AppTheme
import com.github.freenote.data.repository.impl.APP_THEME_KEY
import com.github.freenote.data.repository.impl.THEME_PREFS
import com.github.freenote.databinding.ActivityMainBinding
import com.github.freenote.ui.base.BaseFragment

class MainActivity : AppCompatActivity(), BaseFragment.Contract {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(getThemeFromReferences())
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBarLayout)
    }

    override fun setToolbarTitle(resId: Int) {
        binding.toolBarLayout.title = resources.getString(resId)
    }

    private fun getThemeFromReferences(): Int {
        val sharedPreferences: SharedPreferences = getSharedPreferences(
            THEME_PREFS,
            Context.MODE_PRIVATE
        )
        val position = sharedPreferences.getInt(APP_THEME_KEY, 0)

        val theme = if (position < AppTheme.values().size) {
            AppTheme.values()[position]
        } else {
            AppTheme.LIGHT
        }

        return when (theme) {
            AppTheme.LIGHT -> R.style.Theme_Light
            AppTheme.DARKER -> R.style.Theme_Darker
            AppTheme.DARK -> R.style.Theme_Dark
        }
    }
}