package com.github.freenote.ui.settings

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.freenote.R
import com.github.freenote.databinding.FragmentSettingsBinding
import com.github.freenote.domain.NoteDbEntity
import com.github.freenote.ui.base.BaseFragment
import com.github.freenote.ui.base.ScreenState
import org.koin.androidx.viewmodel.ext.android.viewModel


class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    private val binding: FragmentSettingsBinding by viewBinding(FragmentSettingsBinding::bind)
    private val vm: SettingsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initTheme()
        setToolbarTitle(R.string.settings_title)
    }

    private fun initObservers() {
        vm.notes.observe(viewLifecycleOwner) {
            renderData(it)
        }
    }

    private fun initTheme() {
        binding.whileThemeRadioButton.setOnClickListener{
            setAppTheme(R.style.Theme_FreeNote)
        }
        binding.darkALittleThemeRadioButton.setOnClickListener{
            setAppTheme(R.style.Theme_FreeNote_Dark_A_Little)
        }
        binding.darkThemeRadioButton.setOnClickListener{
            setAppTheme(R.style.Theme_FreeNote_Dark)
        }
    }

    private fun setAppTheme(code: Int) {
        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences(NAME_THEME, Context.MODE_PRIVATE)
        sharedPreferences.edit().putInt(APP_THEME, code).apply()
        activity?.recreate()
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

    companion object {
        const val APP_THEME = "THEME_REFERENCES_KEY"
        const val NAME_THEME = "CURRENT_THEME_KEY"
    }
}