package com.rodionovmax.mygithubapp.data

import android.os.Handler
import android.os.Looper
import com.rodionovmax.mygithubapp.domain.entity.RepoEntity
import com.rodionovmax.mygithubapp.domain.entity.UserEntity
import com.rodionovmax.mygithubapp.domain.repo.MainRepo
import io.reactivex.rxjava3.core.Single

private const val DATA_LOADING_FAKE_DELAY = 3_000L

class FakeMainRepoImpl : MainRepo {

    private val users: List<UserEntity> = listOf(
        UserEntity("ivey", 6, "https://avatars.githubusercontent.com/u/6?v=4"),
        UserEntity("evanphx", 7, "https://avatars.githubusercontent.com/u/7?v=4"),
        UserEntity("vanpelt", 17, "https://avatars.githubusercontent.com/u/17?v=4"),
        UserEntity("wayneeseguin", 18, "https://avatars.githubusercontent.com/u/18?v=4"),
        UserEntity("brynary", 19, "https://avatars.githubusercontent.com/u/19?v=4")
    )

    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        Handler(Looper.getMainLooper()).postDelayed({
            onSuccess(users)
        }, DATA_LOADING_FAKE_DELAY)
    }

    override fun getUsers(): Single<List<UserEntity>> = Single.just(users)

    private val repos: List<RepoEntity> = listOf(
        RepoEntity(124701020, "addressbook-we-tests", "https://github.com/rodionovmax/addressbook-we-tests", "создан проект и первый тест"),
    )

    override fun getRepos(
        userName: String,
        onSuccess: (List<RepoEntity>) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        Handler(Looper.getMainLooper()).postDelayed({
            onSuccess(repos)
        }, DATA_LOADING_FAKE_DELAY)
    }

    override fun getRepos(userName: String): Single<List<RepoEntity>> = Single.just(repos)
}
