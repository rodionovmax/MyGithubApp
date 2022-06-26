package com.rodionovmax.mygithubapp.data.local

import androidx.room.Dao
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM local_user")
    fun getUsersLocal() : List<UserEntity>

}

@Dao
interface RepoDao {
    @Query("SELECT * FROM local_repository")
    fun getReposLocal() : List<RepoEntity>
}
