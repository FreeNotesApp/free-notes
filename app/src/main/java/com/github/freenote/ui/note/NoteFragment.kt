package com.github.freenote.ui.note

import com.github.freenote.R
import com.github.freenote.ui.AppState
import com.github.freenote.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoteFragment : BaseFragment(R.layout.fragment_note) {

    private val viewModel: NoteViewModel by viewModel()

    override fun onResume() {
        super.onResume()


        viewModel.getData().observe(viewLifecycleOwner) { renderData(it) }
        viewModel.getNotes()
    }

    private fun renderData(data: AppState) {
        when (data) {
            is AppState.SuccessListNote -> {
                //showNotesList
            }
            is AppState.Loading -> {
                //showLoading()
            }
            is AppState.Error -> {
                //showError(data.error.message)
            }
        }
    }
}