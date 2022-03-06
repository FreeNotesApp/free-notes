package com.github.freenote

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import com.github.freenote.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolbar()

        setBottomNavigation()
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