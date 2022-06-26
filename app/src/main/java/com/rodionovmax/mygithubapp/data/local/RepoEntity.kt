package com.rodionovmax.mygithubapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "local_repository")
data class RepoEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val url: String,
    val description: String?
)
