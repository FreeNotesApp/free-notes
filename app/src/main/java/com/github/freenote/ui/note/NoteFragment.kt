package com.github.freenote.ui.note

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.freenote.R
import com.github.freenote.databinding.FragmentNoteBinding
import com.github.freenote.domain.NoteDbEntity
import com.github.freenote.ui.base.BaseFragment
import com.github.freenote.ui.utils.NotesColor
import com.github.freenote.ui.utils.getNoteColorId
import org.koin.androidx.viewmodel.ext.android.viewModel

const val NOTE_EXTRA_KEY = "note"

class NoteFragment : BaseFragment(R.layout.fragment_note) {
    private val binding: FragmentNoteBinding by viewBinding(FragmentNoteBinding::bind)
    private val vm: NoteViewModel by viewModel()

    override val shownBottomNav: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<NoteDbEntity>(NOTE_EXTRA_KEY)?.let {
            vm.setNote(it)
        }

        setToolbarTitle(R.string.note)
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.textNoteEditText.doOnTextChanged { text, _, _, _ ->
            vm.onTextChanged(text.toString())
        }

        binding.colorNoteWhite.setOnClickListener {
            vm.onColorChanged(NotesColor.DEFAULT)
            vm.onChangeColorPanelState()
        }

        binding.colorNoteRedLight.setOnClickListener {
            vm.onColorChanged(NotesColor.RED)
            vm.onChangeColorPanelState()
        }

        binding.colorNoteYellowLight.setOnClickListener {
            vm.onColorChanged(NotesColor.YELLOW)
            vm.onChangeColorPanelState()
        }

        binding.colorNoteGreenLight.setOnClickListener {
            vm.onColorChanged(NotesColor.GREEN)
            vm.onChangeColorPanelState()
        }

        binding.colorNotePurple.setOnClickListener {
            vm.onColorChanged(NotesColor.PURPLE)
            vm.onChangeColorPanelState()
        }

        binding.colorNoteYellowBig.setOnClickListener {
            vm.onColorChanged(NotesColor.YELLOW_BIG)
            vm.onChangeColorPanelState()
        }

        binding.colorNoteGreenSwamp.setOnClickListener {
            vm.onColorChanged(NotesColor.GREEN_SWAMP)
            vm.onChangeColorPanelState()
        }

        binding.colorNoteBrown.setOnClickListener {
            vm.onColorChanged(NotesColor.BROWN)
            vm.onChangeColorPanelState()
        }
    }

    private fun initObservers() {
        vm.text.observe(viewLifecycleOwner) {
            if (binding.textNoteEditText.text.toString() != it) {
                binding.textNoteEditText.setText(it)
            }
        }

        vm.color.observe(viewLifecycleOwner) {
            binding.nameNoteTextInput.boxStrokeColor =
                resources.getColor(
                    getNoteColorId(it),
                    null)
        }

        vm.navigateBackEvent.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().popBackStack()
                vm.onNavigateBackFinished()
            }
        }

        vm.title.observe(viewLifecycleOwner) {
            binding.nameNoteTextInput.hint = it
        }

        vm.colorPanelState.observe(viewLifecycleOwner) {
            binding.colorLinearSearch.isVisible = it
            if (it) with(binding) {
                ViewCompat.animate(colorNoteWhite)
                    .translationY(NULL_POSITION)
                ViewCompat.animate(colorNoteGreenLight)
                    .translationY(NULL_POSITION)
                ViewCompat.animate(colorNoteRedLight)
                    .translationY(NULL_POSITION)
                ViewCompat.animate(colorNoteYellowLight)
                    .translationY(NULL_POSITION)
            } else with(binding) {
                ViewCompat.animate(colorNoteWhite)
                    .translationY(START_COLOR_POSITION_MIN)
                ViewCompat.animate(colorNoteGreenLight)
                    .translationY(START_COLOR_POSITION_MIN)
                ViewCompat.animate(colorNoteRedLight)
                    .translationY(START_COLOR_POSITION_PLS)
                ViewCompat.animate(colorNoteYellowLight)
                    .translationY(START_COLOR_POSITION_PLS)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_note, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.delete_bottom_view_note -> {
            vm.onNoteDelete()
            true
        }
        R.id.change_title_bottom_view_note -> {
            changeTitleNote()
            true
        }
        R.id.color_bottom_view_note -> {
            vm.onChangeColorPanelState()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
    private fun changeTitleNote() {
        val editText = EditText(context).apply {
            setText(vm.title.value)
        }

        val space1 = TextView(context).apply { text = SPACE }
        val linearLayout = LinearLayout(context)
        editText.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT).apply {
            gravity = Gravity.CENTER
            width = WIDTH_TEXT_VIEW
        }
        linearLayout.addView(space1)
        linearLayout.addView(editText)

        val alert = AlertDialog.Builder(requireActivity())
            .setView(linearLayout)
            .setCancelable(false)
            .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                vm.onTitleChanged(editText.text.toString())
                dialog.dismiss()
            }.create()

        alert.setTitle(getString(R.string.change_title))
        alert.show()
    }

    override fun onPause() {
        super.onPause()
        vm.onNoteSave(getString(R.string.note))
    }

    companion object {
        const val WIDTH_TEXT_VIEW = 700
        const val SPACE = "     "
        const val NULL_POSITION = 0F
        const val START_COLOR_POSITION_MIN = -200F
        const val START_COLOR_POSITION_PLS = 200F
    }
}