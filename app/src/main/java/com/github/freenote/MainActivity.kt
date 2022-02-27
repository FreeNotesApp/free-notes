package com.github.freenote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.freenote.databinding.ActivityMainBinding
import com.github.freenote.ui.noteslist.NotesListFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            navigateTo(NotesListFragment())
        }

        setBottomNavigation()
    }

    private fun setBottomNavigation() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_view_note -> {
                    navigateTo(NotesListFragment())
                    true
                }
                R.id.bottom_view_date_note -> {
                    navigateTo(NotesListFragment())
                    true
                }
                R.id.bottom_view_settings -> {
                    navigateTo(NotesListFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun navigateTo(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainerView.id, fragment)
            .commit()
    }
}