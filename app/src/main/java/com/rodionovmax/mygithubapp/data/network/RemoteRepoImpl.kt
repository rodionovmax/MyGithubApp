package com.rodionovmax.mygithubapp.data.network

import com.rodionovmax.mygithubapp.domain.entity.RepoEntity
import com.rodionovmax.mygithubapp.domain.entity.UserEntity
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

    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        api.getUsers().enqueue(object : Callback<List<UserEntity>> {
            override fun onResponse(
                call: Call<List<UserEntity>>,
                response: Response<List<UserEntity>>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    onSuccess.invoke(body)
                } else {
                    onError?.invoke(IllegalStateException("Data error"))
                }
            }

            override fun onFailure(call: Call<List<UserEntity>>, t: Throwable) {
                onError?.invoke(t)
            }

        })
    }

    override fun getRepos(userName: String, onSuccess: (List<RepoEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        api.getRepos(userName).enqueue(object : Callback<List<RepoEntity>> {
            override fun onResponse(
                call: Call<List<RepoEntity>>,
                response: Response<List<RepoEntity>>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    onSuccess.invoke(body)
                } else {
                    onError?.invoke(IllegalStateException("Data error"))
                }
            }

            override fun onFailure(call: Call<List<RepoEntity>>, t: Throwable) {
                onError?.invoke(t)
            }

        })
    }
}
