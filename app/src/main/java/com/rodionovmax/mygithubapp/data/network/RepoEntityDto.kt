package com.rodionovmax.mygithubapp.data.network

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.rodionovmax.mygithubapp.domain.entity.RepoEntity
import kotlinx.parcelize.Parcelize


@Parcelize
data class RepoEntityDto(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("html_url") val url: String,
    @SerializedName("description") val description: String?
) : Parcelable {
    fun toRepoEntity() = RepoEntity(id, name, url, description)

    companion object {
        fun fromRepoEntity(repoEntity: RepoEntity) : RepoEntityDto {
            return RepoEntityDto(
                repoEntity.id,
                repoEntity.name,
                repoEntity.url,
                repoEntity.description
            )
        }
    }
}
