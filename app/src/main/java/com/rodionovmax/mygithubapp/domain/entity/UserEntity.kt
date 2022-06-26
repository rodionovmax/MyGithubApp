package com.rodionovmax.mygithubapp.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserEntity(
    val username: String,
    val id: Long,
    val userImg: String
) : Parcelable
