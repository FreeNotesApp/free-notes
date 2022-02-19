package com.github.evgeniychufarnov.freenote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.evgeniychufarnov.freenote.ui.note.NoteFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .replace(R.id.activity_main, NoteFragment())
                .commit()
        navigationShits()
    }

    private fun navigationShits() {
//        bottom_navigation_view.setOnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.bottom_view_note -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.fragment_container_view, NoteFragment())
//                        .commitAllowingStateLoss()
//                    true
//                }
//                R.id.bottom_view_date_note -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.fragment_container_view, OpenNoteFragment())
//                        .commitAllowingStateLoss()
//                    true
//                }
//                R.id.bottom_view_settings -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.fragment_container_view, SettingsFragment())
//                        .commitAllowingStateLoss()
//                    true
//                }
//                else -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.fragment_container_view, NoteFragment())
//                        .commitAllowingStateLoss()
//                    true
//                }
//            }
//        }
    }
}