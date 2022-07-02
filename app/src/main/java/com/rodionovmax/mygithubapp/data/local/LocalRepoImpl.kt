package com.rodionovmax.mygithubapp.data.local

import com.rodionovmax.mygithubapp.data.local.UserEntity.Companion.toUserEntity
import com.rodionovmax.mygithubapp.domain.model.User
import com.rodionovmax.mygithubapp.domain.repo.LocalRepo
import kotlinx.coroutines.*
import java.util.*

class LocalRepoImpl(
    private val db: LocalDatabase
) : LocalRepo {
    override fun getUsersLocal(): List<User> {
        var usersFromDb = listOf<User>()
        runBlocking {
            launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    usersFromDb = db.userDao.getAll().map { it.toUserModel() }
                }
            }
        }
        return usersFromDb
    }

    override fun insertUsers(users: List<User>) {
        GlobalScope.launch(Dispatchers.IO) {
            db.userDao.insert(users.toUserEntity())
        }
    }
}