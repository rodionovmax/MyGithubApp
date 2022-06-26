package com.rodionovmax.mygithubapp.data.network

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.rodionovmax.mygithubapp.domain.model.Repo
import kotlinx.parcelize.Parcelize


@Parcelize
data class RepoEntityDto(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("html_url") val url: String,
    @SerializedName("description") val description: String?
) : Parcelable {
    fun toRepoModel() = Repo(id, name, url, description)

    companion object {
        fun fromRepoModelToDto(repo: Repo) : RepoEntityDto {
            return RepoEntityDto(
                repo.id,
                repo.name,
                repo.url,
                repo.description
            )
        }
    }
}
