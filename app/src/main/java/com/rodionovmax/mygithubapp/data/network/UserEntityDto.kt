package com.rodionovmax.mygithubapp.data.network

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.rodionovmax.mygithubapp.domain.model.User
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserEntityDto(
    @SerializedName("login") val username: String,
    @SerializedName("id") val id: Long,
    @SerializedName("avatar_url") val userImg: String
) : Parcelable {
    fun toUserModel() = User(username, id, userImg)

    companion object {
        fun fromUserModelToDto(user: User) : UserEntityDto {
            return UserEntityDto(
                user.username,
                user.id,
                user.userImg
            )
        }
    }
}

