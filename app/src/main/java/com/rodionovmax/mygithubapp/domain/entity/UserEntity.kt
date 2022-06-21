package com.rodionovmax.mygithubapp.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserEntity(
    @SerializedName("login") val username: String,
    @SerializedName("id") val id: Long,
    @SerializedName("avatar_url") val userImg: String
) : Parcelable
