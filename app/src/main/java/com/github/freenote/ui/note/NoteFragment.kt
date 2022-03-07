package com.github.freenote.ui.note

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.freenote.R
import com.github.freenote.databinding.FragmentNoteBinding
import com.github.freenote.domain.NoteDbEntity
import com.github.freenote.ui.base.ScreenState
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoteFragment : Fragment(R.layout.fragment_note) {

    private val binding: FragmentNoteBinding by viewBinding(FragmentNoteBinding::bind)
    private val vm: NoteViewModel by viewModel()

    private var note: NoteDbEntity? = null
    private var noteDeleteUser = false
    private var colorPanelNote = false
    private var boxStrokeColor = R.color.yellow_light

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initView()
        saveColorInNoteEntity()
    }

    private fun initView() {
        vm.notes.observe(viewLifecycleOwner) {
            renderData(it)
        }
        note = arguments?.getParcelable(BUNDLE_EXTRA)


        if (note == null) {
            binding.nameNoteTextInput.hint = getString(R.string.note)
            binding.nameNoteTextInput.boxStrokeColor = resources.getColor(R.color.yellow_light)
        } else {
            binding.nameNoteTextInput.boxStrokeColor = resources.getColor(pushBackgroundNote())
            binding.nameNoteTextInput.hint = note?.title
            binding.textNoteEditText.setText(note?.text)
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
        val toolbar = requireActivity().findViewById<Toolbar>(R.id.tool_bar_layout)
        val appCompatActivity = requireActivity() as AppCompatActivity?
        appCompatActivity!!.setSupportActionBar(toolbar)
        toolbar?.title = getString(R.string.note)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_note, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.delete_bottom_view_note -> {
            vm.onNoteDelete(note)
            noteDeleteUser = true
            true
        }
        R.id.change_title_bottom_view_note -> {
            changeTitleNote()
            true
        }
        R.id.color_bottom_view_note -> {
            if (colorPanelNote) {
                binding.colorLinearSearch.visibility = View.GONE
                colorPanelNote = false
            }
            else {
                binding.colorLinearSearch.visibility = View.VISIBLE
                colorPanelNote = true
            }
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun changeTitleNote() {
        val editText = EditText(context)
        editText.setText(note?.title)
        val dialogBuilder = activity?.let { AlertDialog.Builder(it) }
        dialogBuilder
            ?.setView(editText)
            ?.setCancelable(false)
            ?.setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                note?.title = editText.text.toString()
                binding.nameNoteTextInput.hint = editText.text.toString()
                dialog.dismiss()
            }
        val alert = dialogBuilder?.create()
        alert?.setTitle(getString(R.string.change_title))
        alert?.show()
    }

    private fun saveColorInNoteEntity() = with(binding){
        colorNoteWhite.setOnClickListener {
            note?.color = R.color.white.toString()
            boxStrokeColor = R.color.white
            binding.nameNoteTextInput.boxStrokeColor = resources.getColor(R.color.white)
        }
        colorNoteYellowLight.setOnClickListener {
            note?.color = R.color.yellow_light.toString()
            boxStrokeColor = R.color.yellow_light
            binding.nameNoteTextInput.boxStrokeColor = resources.getColor(R.color.yellow_light)
        }
        colorNoteGreenLight.setOnClickListener {
            note?.color = R.color.green_light.toString()
            boxStrokeColor = R.color.green_light
            binding.nameNoteTextInput.boxStrokeColor = resources.getColor(R.color.green_light)
        }
        colorNoteRedLight.setOnClickListener {
            note?.color = R.color.red_light.toString()
            boxStrokeColor = R.color.red_light
            binding.nameNoteTextInput.boxStrokeColor = resources.getColor(R.color.red_light)
        }
    }

    private fun pushBackgroundNote(): Int {
        when (note?.color) {
            R.color.white.toString() -> return R.color.white
            R.color.yellow_light.toString() -> return R.color.yellow_light
            R.color.green_light.toString() -> return R.color.green_light
            R.color.red_light.toString() -> return R.color.red_light
        }
        return R.color.white
    }

    override fun onPause() {
        super.onPause()
        if (!noteDeleteUser) {
            if (note == null)
                vm.onNoteSave(binding.textNoteEditText.text.toString(), boxStrokeColor)
            else {
                note?.text = binding.textNoteEditText.text.toString()
                vm.onNoteReplace(note)
            }
        }
    }

    companion object {
        const val BUNDLE_EXTRA = "note_bundle"

        @JvmStatic
        fun newInstance(bundle: Bundle): NoteFragment {
            val fragment = NoteFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}