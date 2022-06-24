package com.rodionovmax.mygithubapp.domain.entity

import com.google.gson.annotations.SerializedName

data class UserEntity(
    @SerializedName("login") val username: String,
    @SerializedName("id") val id: Long,
    @SerializedName("avatar_url") val userImg: String
)
