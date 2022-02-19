package com.github.evgeniychufarnov.freenote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.evgeniychufarnov.freenote.databinding.ActivityMainBinding
import com.github.evgeniychufarnov.freenote.ui.note.NoteFragment
import com.github.evgeniychufarnov.freenote.ui.open.OpenNoteFragment
import com.github.evgeniychufarnov.freenote.ui.settings.SettingsFragment

class MainActivity : AppCompatActivity() {

    lateinit var biding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(biding.root)

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .replace(R.id.activity_main, NoteFragment())
                .commit()
        navigationShits()
    }

    private fun navigationShits() {
        biding.bottomNavigationView
            .setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_view_note -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, NoteFragment())
                        .commitAllowingStateLoss()
                    true
                }
                R.id.bottom_view_date_note -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, NoteFragment())
                        .commitAllowingStateLoss()
                    true
                }
                R.id.bottom_view_settings -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, SettingsFragment())
                        .commitAllowingStateLoss()
                    true
                }
                else -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, NoteFragment())
                        .commitAllowingStateLoss()
                    true
                }
            }
        }
    }
}