package com.rodionovmax.mygithubapp.data

import android.os.Handler
import com.rodionovmax.mygithubapp.domain.model.Repo
import com.rodionovmax.mygithubapp.domain.model.User
import com.rodionovmax.mygithubapp.domain.repo.RemoteRepo
import io.reactivex.rxjava3.core.Single

private const val DATA_LOADING_FAKE_DELAY = 3_000L

class FakeRepoImpl(
    private val uiHandler: Handler
) : RemoteRepo {

    private val users: List<User> = listOf(
        User("ivey", 6, "https://avatars.githubusercontent.com/u/6?v=4"),
        User("evanphx", 7, "https://avatars.githubusercontent.com/u/7?v=4"),
        User("vanpelt", 17, "https://avatars.githubusercontent.com/u/17?v=4"),
        User("wayneeseguin", 18, "https://avatars.githubusercontent.com/u/18?v=4"),
        User("brynary", 19, "https://avatars.githubusercontent.com/u/19?v=4")
    )

    override fun getUsers(onSuccess: (List<User>) -> Unit, onError: ((Throwable) -> Unit)?) {
        uiHandler.postDelayed({
            onSuccess(users)
        }, DATA_LOADING_FAKE_DELAY)
    }

    override fun getUsers(): Single<List<User>> = Single.just(users)

    private val repos: List<Repo> = listOf(
        Repo(124701020, "addressbook-we-tests", "https://github.com/rodionovmax/addressbook-we-tests", "создан проект и первый тест"),
    )

    override fun getRepos(
        userName: String,
        onSuccess: (List<Repo>) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        uiHandler.postDelayed({
            onSuccess(repos)
        }, DATA_LOADING_FAKE_DELAY)
    }

    override fun getRepos(userName: String): Single<List<Repo>> = Single.just(repos)
}
