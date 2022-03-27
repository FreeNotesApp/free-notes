package com.github.freenote.ui

import androidx.lifecycle.ViewModel
import com.github.freenote.data.repository.ThemesRepo

class MainViewModel(
    private val themesRepo: ThemesRepo
) : ViewModel() {

    fun getAppTheme() = themesRepo.getAppTheme()
}