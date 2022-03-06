package com.github.freenote.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.freenote.R
import com.github.freenote.databinding.FragmentNoteBinding
import com.github.freenote.databinding.FragmentSwitchBinding
import com.github.freenote.ui.notelistdate.NoteDateListFragment
import com.github.freenote.ui.noteslist.NotesListFragment
import com.github.freenote.ui.settings.SettingsFragment

class SwitchFragment : Fragment(R.layout.fragment_switch) {

    private val binding: FragmentSwitchBinding by viewBinding(FragmentSwitchBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(binding.fragmentContainerView.id, fragment)
            ?.commit()
    }
}