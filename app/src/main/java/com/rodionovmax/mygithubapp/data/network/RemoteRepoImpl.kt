package com.rodionovmax.mygithubapp.data.network

import com.rodionovmax.mygithubapp.domain.model.Repo
import com.rodionovmax.mygithubapp.domain.model.User
import com.rodionovmax.mygithubapp.domain.repo.MainRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import retrofit2.*
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://api.github.com/"

class RemoteRepoImpl : MainRepo {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    private val api: GithubApi = retrofit.create(GithubApi::class.java)

    // implementation for LiveData
    /*override fun getUsers(onSuccess: (List<UserEntityDto>) -> Unit, onError: ((Throwable) -> Unit)?) {
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
    }*/

    override fun getUsers(
        onSuccess: (List<User>) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        api.getUsers().subscribeBy(
            onSuccess = { users ->
                onSuccess.invoke(users.map { it.toUserModel() })
            },
            onError = {
                onError?.invoke(it)
            }
        )
    }

    override fun getUsers(): Single<List<User>> = api.getUsers()
        .map { users ->
            users.map {
                it.toUserModel()
            }
        }

    override fun getRepos(
        userName: String,
        onSuccess: (List<Repo>) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        api.getRepos(userName).subscribeBy(
            onSuccess = { repos ->
                onSuccess.invoke(repos.map { it.toRepoModel() })
            },
            onError = {
                onError?.invoke(it)
            }
        )
    }

    override fun getRepos(userName: String): Single<List<Repo>> = api.getRepos(userName)
        .map { repos ->
            repos.map {
                it.toRepoModel()
            }
        }

    /*override fun getRepos(userName: String, onSuccess: (List<RepoEntityDto>) -> Unit, onError: ((Throwable) -> Unit)?) {
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
    }*/


}
