package com.rodionovmax.mygithubapp.data.local

import com.rodionovmax.mygithubapp.data.local.UserEntity.Companion.toUserEntity
import com.rodionovmax.mygithubapp.domain.model.User
import com.rodionovmax.mygithubapp.domain.repo.LocalRepo
import kotlinx.coroutines.*

class LocalRepoImpl(
    private val localDataSourceUsers: UserDao
) : LocalRepo {
    override fun getUsersLocal(): List<User> {
        var usersFromDb = listOf<User>()
        runBlocking {
            launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    usersFromDb = localDataSourceUsers.getAll().map { it.toUserModel() }
                }
            }
        }
        return usersFromDb
    }

    override fun insertUsers(users: List<User>) {
        GlobalScope.launch(Dispatchers.IO) {
            localDataSourceUsers.insert(users.toUserEntity())
        }
    }
}