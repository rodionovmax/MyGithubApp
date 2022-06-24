package com.rodionovmax.mygithubapp.data

import android.os.Handler
import android.os.Looper
import com.rodionovmax.mygithubapp.data.network.RepoEntityDto
import com.rodionovmax.mygithubapp.data.network.UserEntityDto
import com.rodionovmax.mygithubapp.domain.repo.MainRepo

private const val DATA_LOADING_FAKE_DELAY = 3_000L

class FakeMainRepoImpl : MainRepo {

    private val data: List<UserEntityDto> = listOf(
        UserEntityDto("ivey", 6, "https://avatars.githubusercontent.com/u/6?v=4"),
        UserEntityDto("evanphx", 7, "https://avatars.githubusercontent.com/u/7?v=4"),
        UserEntityDto("vanpelt", 17, "https://avatars.githubusercontent.com/u/17?v=4"),
        UserEntityDto("wayneeseguin", 18, "https://avatars.githubusercontent.com/u/18?v=4"),
        UserEntityDto("brynary", 19, "https://avatars.githubusercontent.com/u/19?v=4")
    )

    override fun getUsers(onSuccess: (List<UserEntityDto>) -> Unit, onError: ((Throwable) -> Unit)?) {
        Handler(Looper.getMainLooper()).postDelayed({
            onSuccess(data)
        }, DATA_LOADING_FAKE_DELAY)
    }

    private val repos: List<RepoEntityDto> = listOf(
        RepoEntityDto(124701020, "addressbook-we-tests", "https://github.com/rodionovmax/addressbook-we-tests", "создан проект и первый тест"),
    )

    override fun getRepos(
        userName: String,
        onSuccess: (List<RepoEntityDto>) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        Handler(Looper.getMainLooper()).postDelayed({
            onSuccess(repos)
        }, DATA_LOADING_FAKE_DELAY)
    }
}
