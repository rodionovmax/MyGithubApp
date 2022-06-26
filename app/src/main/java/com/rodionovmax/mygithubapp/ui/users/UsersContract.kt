package com.rodionovmax.mygithubapp.ui.users

import com.rodionovmax.mygithubapp.domain.model.User
import io.reactivex.rxjava3.core.Observable

interface UsersContract {

    interface ViewModel {
        val usersLiveData: Observable<List<User>>
        val errorLiveData: Observable<Throwable>
        val progressLiveData: Observable<Boolean>
        val openProfileLiveData: Observable<User>

        fun onRefresh()
        fun onUserClicked(user: User)
    }

}
