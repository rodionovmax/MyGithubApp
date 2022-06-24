package com.rodionovmax.mygithubapp.domain.repo

import com.rodionovmax.mygithubapp.domain.entity.RepoEntity
import com.rodionovmax.mygithubapp.domain.entity.UserEntity

interface MainRepo {

    fun getUsers(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )

    fun getRepos(
        userName: String,
        onSuccess: (List<RepoEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )
}
