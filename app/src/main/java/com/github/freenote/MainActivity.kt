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
import com.github.freenote.ui.SwitchFragment
import com.github.freenote.ui.notelistdate.NoteDateListFragment
import com.github.freenote.ui.noteslist.NotesListFragment
import com.github.freenote.ui.settings.SettingsFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            navigateTo(SwitchFragment())
        }
    }

    private fun navigateTo(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_main, fragment)
            .commit()
    }
}