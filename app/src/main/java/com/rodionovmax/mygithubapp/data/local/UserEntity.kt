package com.rodionovmax.mygithubapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rodionovmax.mygithubapp.domain.model.User

@Entity(tableName = "local_user")
data class UserEntity(
    val username: String,
    @PrimaryKey val id: Long,
    val userImg: String
) {
    fun toUserModel() = User(username, id, userImg)

    companion object {
        fun List<User>.toUserEntity(): List<UserEntity> = map {
            UserEntity(username = it.username, id = it.id, userImg = it.userImg)
        }
    }
}
