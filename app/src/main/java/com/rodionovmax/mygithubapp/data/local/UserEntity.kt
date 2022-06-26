package com.rodionovmax.mygithubapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "local_user")
data class UserEntity(
    val username: String,
    @PrimaryKey val id: Long,
    val userImg: String
)
