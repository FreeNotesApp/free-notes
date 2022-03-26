package com.github.freenote.ui.note

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
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
private const val ANIMATION_START_POSITION = 200f
private const val ANIMATION_END_POSITION = 0f
private const val ANIMATION_TYPE = "translationY"

class NoteFragment : BaseFragment(R.layout.fragment_note) {
    private val binding: FragmentNoteBinding by viewBinding(FragmentNoteBinding::bind)
    private val vm: NoteViewModel by viewModel()

    override val shownBottomNav: Boolean = false

    private val startAnimationsList by lazy {
        with(binding) {
            listOf(
                ObjectAnimator.ofFloat(colorNoteWhite, ANIMATION_TYPE, -ANIMATION_START_POSITION, ANIMATION_END_POSITION),
                ObjectAnimator.ofFloat(colorNoteGreenLight, ANIMATION_TYPE, -ANIMATION_START_POSITION, ANIMATION_END_POSITION),
                ObjectAnimator.ofFloat(colorNoteRedLight, ANIMATION_TYPE, ANIMATION_START_POSITION, ANIMATION_END_POSITION),
                ObjectAnimator.ofFloat(colorNoteYellowLight, ANIMATION_TYPE, ANIMATION_START_POSITION, ANIMATION_END_POSITION)
            )
        }
    }

    private val closeAnimationsList by lazy {
        with(binding) {
            listOf(
                ObjectAnimator.ofFloat(colorNoteWhite, ANIMATION_TYPE, ANIMATION_END_POSITION, -ANIMATION_START_POSITION),
                ObjectAnimator.ofFloat(colorNoteGreenLight, ANIMATION_TYPE, ANIMATION_END_POSITION, -ANIMATION_START_POSITION),
                ObjectAnimator.ofFloat(colorNoteRedLight, ANIMATION_TYPE, ANIMATION_END_POSITION, ANIMATION_START_POSITION),
                ObjectAnimator.ofFloat(colorNoteYellowLight, ANIMATION_TYPE, ANIMATION_END_POSITION, ANIMATION_START_POSITION)
            )
        }
    }

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
        initColorChange()
    }

    private fun initColorChange() = with(binding){
        colorNoteWhite.setCardBackgroundColor(resources.getColor(converterColorNote(NotesColor.COLOR_1)))
        colorNoteYellowLight.setCardBackgroundColor(resources.getColor(converterColorNote(NotesColor.COLOR_2)))
        colorNoteGreenLight.setCardBackgroundColor(resources.getColor(converterColorNote(NotesColor.COLOR_3)))
        colorNoteRedLight.setCardBackgroundColor(resources.getColor(converterColorNote(NotesColor.COLOR_4)))
    }

    private fun converterColorNote(notesColors: NotesColor): Int {
        return getNoteColorId(notesColors, requireContext())
    }

    private fun initListeners() {
        binding.textNoteEditText.doOnTextChanged { text, _, _, _ ->
            vm.onTextChanged(text.toString())
        }

        binding.colorNoteWhite.setOnClickListener {
            vm.onColorChanged(NotesColor.COLOR_1)
            vm.onChangeColorPanelState()
        }

        binding.colorNoteYellowLight.setOnClickListener {
            vm.onColorChanged(NotesColor.COLOR_2)
            vm.onChangeColorPanelState()
        }

        binding.colorNoteGreenLight.setOnClickListener {
            vm.onColorChanged(NotesColor.COLOR_3)
            vm.onChangeColorPanelState()
        }

        binding.colorNoteRedLight.setOnClickListener {
            vm.onColorChanged(NotesColor.COLOR_4)
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
                    getNoteColorId(it, requireContext()),
                    null
                )
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
            val animationSet = if (it) startAnimationsList else closeAnimationsList

            AnimatorSet().apply {
                playTogether(*animationSet.toTypedArray())
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationStart(animation: Animator?, isReverse: Boolean) {
                        if (it) {
                           binding.colorLinearSearch.isVisible = it
                        }
                    }

                    override fun onAnimationEnd(animation: Animator) {
                        if (!it) {
                            binding.colorLinearSearch.isVisible = it
                        }
                    }
                })
                start()
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

        val space = TextView(context).apply { text = SPACE }
        val linearLayout = LinearLayout(context)
        editText.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT).apply {
            gravity = Gravity.CENTER
        }
        linearLayout.addView(space)
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
        const val SPACE = "     "
    }
}