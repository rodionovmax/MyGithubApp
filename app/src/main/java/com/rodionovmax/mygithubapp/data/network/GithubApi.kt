package com.rodionovmax.mygithubapp.data.network

import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("users")
    fun getUsers(): Single<List<UserEntityDto>>

    @GET("users/{user}/repos")
    fun getRepos(
        @Path("user") userName: String,
    ): Single<List<RepoEntityDto>>
}
