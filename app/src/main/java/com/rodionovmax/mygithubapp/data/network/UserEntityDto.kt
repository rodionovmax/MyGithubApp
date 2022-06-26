package com.rodionovmax.mygithubapp.data.network

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.rodionovmax.mygithubapp.domain.entity.UserEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserEntityDto(
    @SerializedName("login") val username: String,
    @SerializedName("id") val id: Long,
    @SerializedName("avatar_url") val userImg: String
) : Parcelable {
    fun toUserEntity() = UserEntity(username, id, userImg)

    companion object {
        fun fromUserEntity(userEntity: UserEntity) : UserEntityDto {
            return UserEntityDto(
                userEntity.username,
                userEntity.id,
                userEntity.userImg
            )
        }
    }
}

