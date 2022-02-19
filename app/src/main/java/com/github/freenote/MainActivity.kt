package com.github.freenote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.freenote.databinding.ActivityMainBinding
import com.github.freenote.ui.noteslist.NotesListFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .replace(R.id.activity_main, NotesListFragment())
                .commit()
        navigationShits()
    }

    private fun navigationShits() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_view_note -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, NotesListFragment())
                        .commitAllowingStateLoss()
                    true
                }
                R.id.bottom_view_date_note -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, NotesListFragment())
                        .commitAllowingStateLoss()
                    true
                }
                R.id.bottom_view_settings -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, NotesListFragment())
                        .commitAllowingStateLoss()
                    true
                }
                else -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, NotesListFragment())
                        .commitAllowingStateLoss()
                    true
                }
            }
        }
    }
}