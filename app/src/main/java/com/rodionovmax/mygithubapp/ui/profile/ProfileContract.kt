package com.rodionovmax.mygithubapp.ui.profile

import androidx.lifecycle.LiveData
import com.rodionovmax.mygithubapp.data.network.RepoEntityDto

interface ProfileContract {

    interface ViewModel {
        val profileLifeData: LiveData<List<RepoEntityDto>>
        val errorLiveData: LiveData<Throwable>
        val progressLiveData: LiveData<Boolean>

        fun onStart(username: String?)
    }
}