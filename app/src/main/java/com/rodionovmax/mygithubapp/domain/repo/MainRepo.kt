package com.rodionovmax.mygithubapp.domain.repo

import com.rodionovmax.mygithubapp.data.network.RepoEntityDto
import com.rodionovmax.mygithubapp.data.network.UserEntityDto
import com.rodionovmax.mygithubapp.domain.entity.RepoEntity
import com.rodionovmax.mygithubapp.domain.entity.UserEntity
import io.reactivex.rxjava3.core.Single

interface MainRepo {

    // used with LiveData
    fun getUsers(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )

    // used with RxJava
    fun getUsers() : Single<List<UserEntity>>

    fun getRepos(
        userName: String,
        onSuccess: (List<RepoEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )

    // used with RxJava
    fun getRepos(userName: String) : Single<List<RepoEntity>>
}
