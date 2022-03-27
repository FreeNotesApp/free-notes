package com.github.freenote.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.freenote.R
import com.github.freenote.data.repository.NoteRepo
import com.github.freenote.domain.NoteDbEntity
import com.github.freenote.ui.base.ScreenState
import com.github.freenote.ui.utils.NotesColor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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