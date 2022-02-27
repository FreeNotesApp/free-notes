package com.github.freenote.ui.noteslist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.freenote.R
import com.github.freenote.databinding.FragmentNotesListBinding
import com.github.freenote.domain.NoteDbEntity
import com.github.freenote.ui.ScreenState
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotesListFragment : Fragment(R.layout.fragment_notes_list) {

    private val binding: FragmentNotesListBinding by viewBinding(FragmentNotesListBinding::bind)
    private val vm: NotesListViewModel by viewModel()

    private lateinit var adapter: NotesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NotesAdapter(vm::onNoteClicked)
        binding.fragNotesListRvNotes.adapter = adapter

        vm.notes.observe(viewLifecycleOwner) {
            renderData(it)
        }

        vm.noteClickedEvent.observe(viewLifecycleOwner) {
            if (it != null) {
                // todo open node screen
                vm.onNoteClickedFinished()
            }
        }
    }

    private fun renderData(data: ScreenState<List<NoteDbEntity>>) {
        when (data) {
            is ScreenState.Success -> {
                adapter.submitList(data.value)
            }
            is ScreenState.Loading -> {
                // todo loading state
            }
            is ScreenState.Error -> {
                // todo error state
            }
        }
    }
}