package com.rodionovmax.mygithubapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rodionovmax.mygithubapp.domain.model.Repo

@Entity(tableName = "local_repository")
data class RepoEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val url: String,
    val description: String?
) {
    fun toRepoModel() = Repo(id, name, url, description)
}
