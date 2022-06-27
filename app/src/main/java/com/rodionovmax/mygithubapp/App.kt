package com.rodionovmax.mygithubapp

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.rodionovmax.mygithubapp.data.local.LocalDatabase
import com.rodionovmax.mygithubapp.data.network.RemoteRepoImpl
import com.rodionovmax.mygithubapp.domain.repo.RemoteRepo

class App : Application() {
    val remoteRepo: RemoteRepo by lazy { RemoteRepoImpl() }
    private lateinit var appInstance : App

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    fun getDB() = LocalDatabase.getInstance(appInstance.applicationContext)
}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext().applicationContext as App
