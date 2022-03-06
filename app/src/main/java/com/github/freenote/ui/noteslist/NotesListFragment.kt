package com.github.freenote.ui.noteslist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.freenote.R
import com.github.freenote.databinding.FragmentNotesListBinding
import com.github.freenote.domain.NoteDbEntity
import com.github.freenote.ui.base.ScreenState
import com.github.freenote.ui.note.NoteFragment
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
                vm.onNoteClickedFinished()
            }
        }

        initView()
    }

    private fun initView() {
        binding.fragNotesListFabAdd.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.add(R.id.activity_main, NoteFragment())
                ?.addToBackStack("")
                ?.commit()
        }
    }

    private fun renderData(data: ScreenState<LiveData<List<NoteDbEntity>>>) {
        when (data) {
            is ScreenState.Success -> {
                // тут ошибка не пойму почемууууу
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