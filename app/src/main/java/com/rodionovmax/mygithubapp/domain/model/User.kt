package com.rodionovmax.mygithubapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class User(
    val username: String,
    val id: Long,
    val userImg: String
) : Parcelable
