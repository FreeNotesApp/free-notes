package com.github.freenote.ui.notedate

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.freenote.R
import com.github.freenote.databinding.FragmentNoteBinding
import com.github.freenote.databinding.FragmentNoteDateBinding
import com.github.freenote.domain.NoteDbEntity
import com.github.freenote.ui.base.ScreenState
import com.github.freenote.ui.note.NoteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class NoteDateFragment : Fragment(R.layout.fragment_note_date) {

    private val binding: FragmentNoteDateBinding by viewBinding(FragmentNoteDateBinding::bind)
    private val vm: NoteDateViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
}