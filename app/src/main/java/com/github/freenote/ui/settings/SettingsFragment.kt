package com.github.freenote.ui.settings

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.freenote.R
import com.github.freenote.ThemeColor
import com.github.freenote.databinding.FragmentSettingsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private val binding: FragmentSettingsBinding by viewBinding(FragmentSettingsBinding::bind)
    private val vm: SettingsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTheme()
        setToolbarTitle(R.string.settings_title)
    }

    private fun initTheme() {
        binding.whileThemeRadioButton.setOnClickListener{
            setAppTheme(R.style.Theme_FreeNote)
            ThemeColor.themeColor = R.style.Theme_FreeNote
        }
        binding.dartALittleThemeRadioButton.setOnClickListener{
            setAppTheme(R.style.Theme_FreeNote_Dark_A_Little)
            ThemeColor.themeColor = R.style.Theme_FreeNote_Dark_A_Little
        }
        binding.darkThemeRadioButton.setOnClickListener{
            setAppTheme(R.style.Theme_FreeNote_Dark)
            ThemeColor.themeColor = R.style.Theme_FreeNote_Dark
        }
    }

    private fun setAppTheme(code: Int) {
        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences(NAME_THEME, Context.MODE_PRIVATE)
        sharedPreferences.edit().putInt(APP_THEME, code).apply()
        activity?.recreate()
    }

    companion object {
        const val APP_THEME = "APP_THEME"
        const val NAME_THEME = "THEME"
    }
}