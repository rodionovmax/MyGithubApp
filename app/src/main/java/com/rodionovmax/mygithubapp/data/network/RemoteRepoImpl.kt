package com.rodionovmax.mygithubapp.data.network

import com.rodionovmax.mygithubapp.domain.repo.MainRepo
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://api.github.com/"

class RemoteRepoImpl : MainRepo {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api: GithubApi = retrofit.create(GithubApi::class.java)

    override fun getUsers(onSuccess: (List<UserEntityDto>) -> Unit, onError: ((Throwable) -> Unit)?) {
        api.getUsers().enqueue(object : Callback<List<UserEntityDto>> {
            override fun onResponse(
                call: Call<List<UserEntityDto>>,
                response: Response<List<UserEntityDto>>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    onSuccess.invoke(body)
                } else {
                    onError?.invoke(IllegalStateException("Data error"))
                }
            }

            override fun onFailure(call: Call<List<UserEntityDto>>, t: Throwable) {
                onError?.invoke(t)
            }

        })
    }

    override fun getRepos(userName: String, onSuccess: (List<RepoEntityDto>) -> Unit, onError: ((Throwable) -> Unit)?) {
        api.getRepos(userName).enqueue(object : Callback<List<RepoEntityDto>> {
            override fun onResponse(
                call: Call<List<RepoEntityDto>>,
                response: Response<List<RepoEntityDto>>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    onSuccess.invoke(body)
                } else {
                    onError?.invoke(IllegalStateException("Data error"))
                }
            }

            override fun onFailure(call: Call<List<RepoEntityDto>>, t: Throwable) {
                onError?.invoke(t)
            }

        })
    }
}
