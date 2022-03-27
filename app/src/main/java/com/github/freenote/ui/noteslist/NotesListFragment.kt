package com.github.freenote.ui.noteslist

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.freenote.R
import com.github.freenote.databinding.FragmentNotesListBinding
import com.github.freenote.domain.NoteDbEntity
import com.github.freenote.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

const val NOTE_EXTRA_KEY = "note"

class NotesListFragment : BaseFragment(R.layout.fragment_notes_list) {

    private val binding: FragmentNotesListBinding by viewBinding(FragmentNotesListBinding::bind)
    private val vm: NotesListViewModel by viewModel()

    private lateinit var adapter: NotesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NotesAdapter(vm::onNoteClicked, vm.getTheme())
        binding.fragNotesListRvNotes.adapter = adapter

        binding.fragNotesListFabAdd.setOnClickListener {
            vm.onCreateNoteClicked()
        }

        setToolbarTitle(R.string.notes_list_title)
        initObservers()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    private fun initObservers() {
        vm.noteClickedEvent.observe(viewLifecycleOwner) {
            if (it != null) {
                navigateToNote(it)
                vm.onNoteClickedFinished()
            }
        }

        vm.notes.observe(viewLifecycleOwner) {
            adapter.submitListWithScroll(it) {
                val manager = binding.fragNotesListRvNotes.layoutManager as LinearLayoutManager
                manager.scrollToPosition(0)
            }
        }

        vm.createNoteClickedEvent.observe(viewLifecycleOwner) {
            if (it != null) {
                navigateToNote(null)
                vm.onCreateNoteClickedFinished()
            }
        }

        vm.settingsClickedEvent.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().navigate(R.id.settings_fragment)
                vm.onSettingsClickedFinished()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_settings, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.settings -> {
            vm.onSettingsClicked()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun navigateToNote(note: NoteDbEntity?) {
        val bundle = bundleOf(NOTE_EXTRA_KEY to note)
        findNavController().navigate(R.id.note_fragment, bundle)
    }
}