package com.rodionovmax.mygithubapp.domain.repo

import com.rodionovmax.mygithubapp.domain.model.Repo
import com.rodionovmax.mygithubapp.domain.model.User
import io.reactivex.rxjava3.core.Single

interface MainRepo {

    // used with LiveData
    fun getUsers(
        onSuccess: (List<User>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )

    // used with RxJava
    fun getUsers() : Single<List<User>>

    fun getRepos(
        userName: String,
        onSuccess: (List<Repo>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )

    // used with RxJava
    fun getRepos(userName: String) : Single<List<Repo>>
}
