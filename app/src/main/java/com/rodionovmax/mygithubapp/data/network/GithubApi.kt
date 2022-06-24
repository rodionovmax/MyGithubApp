package com.rodionovmax.mygithubapp.data.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("users")
    fun getUsers(): Call<List<UserEntityDto>>

    @GET("users/{user}/repos")
    fun getRepos(
        @Path("user") userName: String,
    ): Call<List<RepoEntityDto>>
}
