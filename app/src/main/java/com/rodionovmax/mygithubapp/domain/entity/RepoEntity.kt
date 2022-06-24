package com.rodionovmax.mygithubapp.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RepoEntity (
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("html_url") val url: String,
    @SerializedName("description") val description: String
) : Parcelable