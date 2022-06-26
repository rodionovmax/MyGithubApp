package com.rodionovmax.mygithubapp.data.local

import androidx.room.Query

interface GithubLocalDao {
    @Query("SELECT * FROM UserEntity")
    fun
}