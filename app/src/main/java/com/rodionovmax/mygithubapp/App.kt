package com.rodionovmax.mygithubapp

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import com.rodionovmax.mygithubapp.data.FakeRepoImpl
import com.rodionovmax.mygithubapp.data.local.LocalDatabase
import com.rodionovmax.mygithubapp.data.network.GithubApi
import com.rodionovmax.mygithubapp.data.network.RemoteRepoImpl
import com.rodionovmax.mygithubapp.domain.repo.RemoteRepo
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {
    private val baseUrl = "https://api.github.com/"
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    private val api: GithubApi by lazy { retrofit.create(GithubApi::class.java) }
    private val uiHandler: Handler by lazy { Handler(Looper.getMainLooper()) }

    val remoteRepo: RemoteRepo by lazy { RemoteRepoImpl(api) }
    val fakeRepo: FakeRepoImpl by lazy { FakeRepoImpl(uiHandler) }
    private lateinit var appInstance: App

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    fun getDB() = LocalDatabase.getInstance(appInstance.applicationContext)
}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext().applicationContext as App
