package com.rodionovmax.mygithubapp.domain.repo

import com.rodionovmax.mygithubapp.data.local.UserEntity
import com.rodionovmax.mygithubapp.domain.model.User

interface LocalRepo {
    fun getUsersLocal() : List<User>
    fun insertUsers(users: List<User>)
}