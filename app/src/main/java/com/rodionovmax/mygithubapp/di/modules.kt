package com.rodionovmax.mygithubapp.di

import com.rodionovmax.mygithubapp.App
import com.rodionovmax.mygithubapp.data.FakeRepoImpl
import com.rodionovmax.mygithubapp.data.local.LocalDatabase
import com.rodionovmax.mygithubapp.data.local.LocalRepoImpl
import com.rodionovmax.mygithubapp.data.network.GithubApi
import com.rodionovmax.mygithubapp.data.network.RemoteRepoImpl
import com.rodionovmax.mygithubapp.domain.repo.LocalRepo
import com.rodionovmax.mygithubapp.domain.repo.RemoteRepo
import com.rodionovmax.mygithubapp.ui.profile.ProfileViewModel
import com.rodionovmax.mygithubapp.ui.users.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single(named("url")) { "https://api.github.com/" }

    single {
        Retrofit.Builder()
            .baseUrl(get<String>(named("url")))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
    single<GithubApi> { get<Retrofit>().create(GithubApi::class.java) }
    single<RemoteRepo> { RemoteRepoImpl(get()) }
    single { FakeRepoImpl(get()) }
    single<LocalRepo> { LocalRepoImpl(get()) }
    single {
        LocalDatabase.create(App.appContext)
    }

    viewModel { UsersViewModel(get()) }
    viewModel { ProfileViewModel(get()) }

}


