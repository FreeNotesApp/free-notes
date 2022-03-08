package com.github.freenote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import com.github.freenote.databinding.ActivityMainBinding
import com.github.freenote.ui.base.BaseFragment

class MainActivity : AppCompatActivity(), BaseFragment.Contract {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBarLayout)
        setBottomNavigation()
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
}