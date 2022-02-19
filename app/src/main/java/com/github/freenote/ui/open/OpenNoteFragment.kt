package com.github.freenote.ui.open

import com.github.freenote.R
import com.github.freenote.ui.AppState
import com.github.freenote.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class OpenNoteFragment : BaseFragment(R.layout.fragment_open_note) {

    private val viewModel: OpenNoteViewModel by viewModel()

    override fun onResume() {
        super.onResume()

        viewModel.getData().observe(viewLifecycleOwner) { renderData(it) }
        //ниже в скобки нужно положить заметку которая придет через парсинг от адаптера
        //viewModel.getNote()
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