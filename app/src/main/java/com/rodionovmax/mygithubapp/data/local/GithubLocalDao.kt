package com.rodionovmax.mygithubapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM local_user")
    fun getAll() : List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<UserEntity>)

}

@Dao
interface RepoDao {
    @Query("SELECT * FROM local_repository")
    fun getReposLocal() : List<RepoEntity>
}
