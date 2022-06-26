package com.rodionovmax.mygithubapp.domain.repo

import com.rodionovmax.mygithubapp.data.network.RepoEntityDto
import com.rodionovmax.mygithubapp.data.network.UserEntityDto

interface MainRepo {

    fun getUsers(
        onSuccess: (List<UserEntityDto>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )

    fun getRepos(
        userName: String,
        onSuccess: (List<RepoEntityDto>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )
}
