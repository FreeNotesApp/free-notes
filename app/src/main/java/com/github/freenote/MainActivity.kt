package com.github.freenote

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.freenote.databinding.ActivityMainBinding
import com.github.freenote.ui.base.BaseFragment

class MainActivity : AppCompatActivity(), BaseFragment.Contract {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(getAppTheme(R.style.Theme_FreeNote))
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBarLayout)
        setBottomNavigation()
    }

    private  fun getAppTheme(code: Int): Int {
        return getCodeStyle(code)
    }

    private fun getCodeStyle(codeStyle: Int): Int {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences(NAME_THEME, Context.MODE_PRIVATE)
        return sharedPreferences.getInt(APP_THEME, codeStyle)
    }

    override fun setToolbarTitle(resId: Int) {
        binding.toolBarLayout.title = resources.getString(resId)
    }

    companion object {
        const val APP_THEME = "APP_THEME"
        const val NAME_THEME = "THEME"
    }
}