package com.rodionovmax.mygithubapp

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.rodionovmax.mygithubapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    companion object {
        lateinit var appInstance: App
        lateinit  var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this
        appContext = applicationContext

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }
    }
}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext().applicationContext as App
