package com.rodionovmax.mygithubapp.ui.profile

import androidx.lifecycle.LiveData
import com.rodionovmax.mygithubapp.domain.entity.RepoEntity

interface ProfileContract {

    interface ViewModel {
        val profileLifeData: LiveData<List<RepoEntity>>
        val errorLiveData: LiveData<Throwable>
        val progressLiveData: LiveData<Boolean>

        fun onStart(username: String?)
    }
}