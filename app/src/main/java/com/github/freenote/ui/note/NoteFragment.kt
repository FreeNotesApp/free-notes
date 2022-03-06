package com.github.freenote.ui.note

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.freenote.R
import com.github.freenote.databinding.FragmentNoteBinding
import com.github.freenote.domain.NoteDbEntity
import com.github.freenote.ui.base.ScreenState
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class NoteFragment : Fragment(R.layout.fragment_note) {

    private val binding: FragmentNoteBinding by viewBinding(FragmentNoteBinding::bind)
    private val vm: NoteViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        vm.notes.observe(viewLifecycleOwner) {
            renderData(it)
        }
    }

    private fun renderData(data: ScreenState<NoteDbEntity>) {
        when (data) {
            is ScreenState.Success -> {

            }
            is ScreenState.Loading -> {
                // todo loading state
            }
            is ScreenState.Error -> {
                // todo error state
            }
        }
    }

    private fun initToolbar() {
        val toolbar = activity?.findViewById<Toolbar>(R.id.tool_bar_layout)
        val appCompatActivity = activity as AppCompatActivity?
        appCompatActivity!!.setSupportActionBar(toolbar)
        toolbar?.title = "Notes"
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_note, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.exit_bottom_view_note -> {
            Toast.makeText(context, "exit", Toast.LENGTH_SHORT).show()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun saveNote(): NoteDbEntity {
        return NoteDbEntity(
            id = UUID.randomUUID().toString(),
            title = "fake title",
            text = binding.textNoteEditText.text.toString(),
            date = System.currentTimeMillis(),
            color = "While",
            number = "0"
        )
    }

    override fun onPause() {
        super.onPause()
        vm.onNoteSave(saveNote())
    }

    override fun onDestroy() {
        super.onDestroy()
        vm.onNoteSave(saveNote())
    }
}