package com.rodionovmax.mygithubapp.domain.repo

import com.rodionovmax.mygithubapp.domain.entity.UserEntity

interface UsersRepo {

    fun getUsers(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )
}
