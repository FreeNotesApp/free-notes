package com.github.freenote.ui.settings

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.freenote.R
import com.github.freenote.databinding.FragmentSettingsBinding
import com.github.freenote.domain.NoteDbEntity
import com.github.freenote.ui.base.ScreenState
import org.koin.androidx.viewmodel.ext.android.viewModel


class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private val binding: FragmentSettingsBinding by viewBinding(FragmentSettingsBinding::bind)
    private val vm: SettingsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initTheme()
    }

    private fun initObservers() {
        vm.notes.observe(viewLifecycleOwner) {
            renderData(it)
        }
    }

    private fun initTheme() {
        binding.redThemeRadioButton.setOnClickListener{
            setAppTheme(R.style.Theme_FreeNote)
        }
        binding.blackThemeRadioButton.setOnClickListener{
            setAppTheme(R.style.Theme_FreeNote_Gray)
        }
        binding.greenThemeRadioButton.setOnClickListener{
            setAppTheme(R.style.Theme_FreeNote_Brown)
        }
    }

    private fun setAppTheme(code: Int) {
        val sharedPreferences: SharedPreferences? =
            activity?.getSharedPreferences(NAME_THEME, Context.MODE_PRIVATE)
        sharedPreferences?.edit()?.putInt(APP_THEME, code)?.apply()
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
        const val APP_THEME = "APP_THEME"
        const val NAME_THEME = "THEME"
    }
}