package com.rodionovmax.mygithubapp

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.rodionovmax.mygithubapp.data.FakeUsersRepoImpl
import com.rodionovmax.mygithubapp.data.network.RemoteRepoImpl
import com.rodionovmax.mygithubapp.domain.repo.UsersRepo

class App : Application() {
    val usersRepo: UsersRepo by lazy { RemoteRepoImpl() }
}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext().applicationContext as App
