package com.github.freenote

import android.app.Application
import com.github.freenote.di.viewModelModule
import com.github.freenote.ui.utils.NotesColor
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidContext(this@App)
            modules(viewModelModule)
        }
    }
}