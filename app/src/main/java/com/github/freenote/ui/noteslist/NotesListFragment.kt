package com.github.freenote.ui.noteslist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.freenote.databinding.FragmentNotesListBinding
import com.github.freenote.domain.NoteDbEntity
import com.github.freenote.ui.ScreenState
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotesListFragment : Fragment() {

    private val binding: FragmentNotesListBinding by viewBinding(FragmentNotesListBinding::bind)
    private val vm: NotesListViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.notes.observe(viewLifecycleOwner) {
            renderData(it)
        }
    }

    private fun renderData(data: ScreenState<List<NoteDbEntity>>) {
        when (data) {
            is ScreenState.Success -> {
                //showNotesList
            }
            is ScreenState.Loading -> {
                //showLoading()
            }
            is ScreenState.Error -> {
                //showError(data.error.message)
            }
        }
    }
}