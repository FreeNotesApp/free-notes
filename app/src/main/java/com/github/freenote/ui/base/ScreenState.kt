package com.github.freenote.ui

sealed  class  ScreenState<out T> {
    data class Success<out T>(val value: T) : ScreenState<T>()
    data class Error(val error: Throwable) : ScreenState<Nothing>()
    object Loading : ScreenState<Nothing>()
}
