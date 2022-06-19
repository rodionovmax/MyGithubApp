package com.rodionovmax.mygithubapp.data.network

import com.rodionovmax.mygithubapp.domain.entity.UserEntity
import retrofit2.Call
import retrofit2.http.GET

interface GithubApi {
    @GET("users")
    fun getUsers(): Call<List<UserEntity>>
}