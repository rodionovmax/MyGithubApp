package com.rodionovmax.mygithubapp.ui.users

import androidx.lifecycle.LiveData
import com.rodionovmax.mygithubapp.data.network.UserEntityDto

interface UsersContract {

    interface ViewModel {
        val usersLiveData: LiveData<List<UserEntityDto>>
        val errorLiveData: LiveData<Throwable>
        val progressLiveData: LiveData<Boolean>
        val openProfileLiveData: LiveData<UserEntityDto>

        fun onRefresh()
        fun onUserClicked(userEntity: UserEntityDto)
    }

}
