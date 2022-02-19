package com.github.evgeniychufarnov.freenote

import android.app.Application
import com.github.evgeniychufarnov.freenote.di.viewModelModule
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