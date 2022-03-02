package com.github.freenote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.github.freenote.databinding.ActivityMainBinding
import com.github.freenote.ui.notelistdate.NoteDateListFragment
import com.github.freenote.ui.noteslist.NotesListFragment
import com.github.freenote.ui.settings.SettingsFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolbar()

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
                    navigateTo(NoteDateListFragment())
                    true
                }
                R.id.bottom_view_settings -> {
                    navigateTo(SettingsFragment())
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

    private fun initToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.tool_bar_layout)
        setSupportActionBar(toolbar)
        toolbar.title = "Free Notes"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_note, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.exit_bottom_view_note -> {
            Toast.makeText(applicationContext, "exit", Toast.LENGTH_SHORT).show()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

}