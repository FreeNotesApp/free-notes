package com.github.freenote

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.github.freenote.databinding.ActivityMainBinding
import com.github.freenote.ui.base.BaseFragment

class MainActivity : AppCompatActivity(), BaseFragment.Contract {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

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

    private fun setBottomNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(
            binding.fragmentContainerView.id
        ) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)
    }

    override fun setToolbarTitle(resId: Int) {
        binding.toolBarLayout.title = resources.getString(resId)
    }

    override fun setToolbarVisibility(isVisible: Boolean) {
        binding.appBarLayout.isVisible = isVisible
    }

    override fun setBottomNavVisibility(isVisible: Boolean) {
        binding.bottomNavigationView.isVisible = isVisible
    }

    private fun setBottomNavigation() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_view_note -> {
                    navigateTo(R.id.notes_list_fragment)
                    true
                }
                R.id.bottom_view_date_note -> {
                    navigateTo(R.id.note_date_list_fragment)
                    true
                }
                R.id.bottom_view_settings -> {
                    navigateTo(R.id.settings_fragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun navigateTo(fragmentId: Int) {
        findNavController(binding.fragmentContainerView.id).navigate(fragmentId)
    }

    companion object {
        const val APP_THEME = "APP_THEME"
        const val NAME_THEME = "THEME"
    }
}