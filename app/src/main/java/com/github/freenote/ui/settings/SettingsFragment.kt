package com.github.freenote.ui.settings

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.freenote.R
import com.github.freenote.data.repository.AppTheme
import com.github.freenote.databinding.FragmentSettingsBinding
import com.github.freenote.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    private val binding: FragmentSettingsBinding by viewBinding(FragmentSettingsBinding::bind)
    private val vm: SettingsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initObservers()
        setToolbarTitle(R.string.settings_title)
    }

    private fun initListeners() {
        binding.lightThemeRadioButton.setOnClickListener {
            vm.onChangeTheme(AppTheme.LIGHT)
            requireActivity().recreate()
        }
        binding.darkerThemeRadioButton.setOnClickListener {
            vm.onChangeTheme(AppTheme.DARKER)
            requireActivity().recreate()
        }
        binding.darkThemeRadioButton.setOnClickListener {
            vm.onChangeTheme(AppTheme.DARK)
            requireActivity().recreate()
        }
    }

    private fun initObservers() {
        vm.theme.observe(viewLifecycleOwner) {
            binding.themeRadioGroup.check(
                when (it) {
                    AppTheme.LIGHT -> binding.lightThemeRadioButton.id
                    AppTheme.DARKER -> binding.darkerThemeRadioButton.id
                    AppTheme.DARK -> binding.darkThemeRadioButton.id
                    else -> binding.lightThemeRadioButton.id
                }
            )
        }
    }
}