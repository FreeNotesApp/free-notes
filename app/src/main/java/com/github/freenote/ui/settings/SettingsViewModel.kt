package com.github.freenote.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.freenote.data.repository.AppTheme
import com.github.freenote.data.repository.ThemesRepo

class SettingsViewModel(
    private val themesRepo: ThemesRepo
) : ViewModel() {

    private val _theme = MutableLiveData(themesRepo.getAppTheme())
    val theme: LiveData<AppTheme> = _theme

    fun onChangeTheme(theme: AppTheme) {
        _theme.value = theme
        themesRepo.setAppTheme(theme)
    }
}