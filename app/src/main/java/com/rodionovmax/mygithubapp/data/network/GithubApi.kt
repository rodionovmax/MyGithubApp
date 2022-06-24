package com.rodionovmax.mygithubapp.data.network

import com.rodionovmax.mygithubapp.domain.entity.RepoEntity
import com.rodionovmax.mygithubapp.domain.entity.UserEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {
    @GET("users")
    fun getUsers(): Call<List<UserEntity>>

    @GET("users/{user}/repos")
    fun getRepos(
        @Path("user") userName: String,
    ): Call<List<RepoEntity>>
}
