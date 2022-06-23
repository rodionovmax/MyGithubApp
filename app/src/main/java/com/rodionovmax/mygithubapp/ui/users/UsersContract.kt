package com.rodionovmax.mygithubapp.ui.users

import androidx.lifecycle.LiveData
import com.rodionovmax.mygithubapp.domain.entity.UserEntity

interface UsersContract {

    interface ViewModel {
        val usersLiveData: LiveData<List<UserEntity>>
        val errorLiveData: LiveData<Throwable>
        val progressLiveData: LiveData<Boolean>
        val openProfileLiveData: LiveData<UserEntity>

        fun onRefresh()
        fun onUserClicked(userEntity: UserEntity)
    }

}
