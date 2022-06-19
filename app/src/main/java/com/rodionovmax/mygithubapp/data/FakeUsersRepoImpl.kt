package com.rodionovmax.mygithubapp.data

import android.os.Handler
import android.os.Looper
import com.rodionovmax.mygithubapp.domain.entity.UserEntity
import com.rodionovmax.mygithubapp.domain.repo.UsersRepo

private const val DATA_LOADING_FAKE_DELAY = 3_000L

class FakeUsersRepoImpl : UsersRepo {

    private val data: List<UserEntity> = listOf(
        UserEntity("ivey", 6, "https://avatars.githubusercontent.com/u/6?v=4"),
        UserEntity("evanphx", 7, "https://avatars.githubusercontent.com/u/7?v=4"),
        UserEntity("vanpelt", 17, "https://avatars.githubusercontent.com/u/17?v=4"),
        UserEntity("wayneeseguin", 18, "https://avatars.githubusercontent.com/u/18?v=4"),
        UserEntity("brynary", 19, "https://avatars.githubusercontent.com/u/19?v=4")
    )

    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        Handler(Looper.getMainLooper()).postDelayed({
            onSuccess(data)
        }, DATA_LOADING_FAKE_DELAY)
    }
}
