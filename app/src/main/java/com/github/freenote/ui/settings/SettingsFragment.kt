package com.github.freenote.ui.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.freenote.R
import com.github.freenote.databinding.FragmentNoteBinding
import com.github.freenote.databinding.FragmentSettingsBinding
import com.github.freenote.domain.NoteDbEntity
import com.github.freenote.ui.base.ScreenState
import com.github.freenote.ui.note.NoteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private val binding: FragmentSettingsBinding by viewBinding(FragmentSettingsBinding::bind)
    private val vm: SettingsViewModel by viewModel()

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