package com.rodionovmax.mygithubapp.ui.profile

import com.rodionovmax.mygithubapp.domain.model.Repo
import io.reactivex.rxjava3.core.Observable

interface ProfileContract {

    interface ViewModel {
        val profileLifeData: Observable<List<Repo>>
        val errorLiveData: Observable<Throwable>
        val progressLiveData: Observable<Boolean>

        fun onStart(username: String?)
    }
}