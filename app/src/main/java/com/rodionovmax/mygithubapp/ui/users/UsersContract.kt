package com.rodionovmax.mygithubapp.ui.users

import com.rodionovmax.mygithubapp.domain.entity.UserEntity
import io.reactivex.rxjava3.core.Observable

interface UsersContract {

    interface ViewModel {
        val usersLiveData: Observable<List<UserEntity>>
        val errorLiveData: Observable<Throwable>
        val progressLiveData: Observable<Boolean>
        val openProfileLiveData: Observable<UserEntity>

        fun onRefresh()
        fun onUserClicked(userEntity: UserEntity)
    }

}
