package com.rodionovmax.mygithubapp.ui.profile

import androidx.lifecycle.LiveData
import com.rodionovmax.mygithubapp.data.network.RepoEntityDto
import com.rodionovmax.mygithubapp.domain.entity.RepoEntity
import io.reactivex.rxjava3.core.Observable

interface ProfileContract {

    interface ViewModel {
        val profileLifeData: Observable<List<RepoEntity>>
        val errorLiveData: Observable<Throwable>
        val progressLiveData: Observable<Boolean>

        fun onStart(username: String?)
    }
}