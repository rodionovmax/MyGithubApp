package com.rodionovmax.mygithubapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rodionovmax.mygithubapp.domain.model.Repo
import com.rodionovmax.mygithubapp.domain.repo.MainRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject


class ProfileViewModel(private val mainRepo: MainRepo) : ProfileContract.ViewModel {

    override val profileLifeData: Observable<List<Repo>> = BehaviorSubject.create()
    override val errorLiveData: Observable<Throwable> = BehaviorSubject.create()
    override val progressLiveData: Observable<Boolean> = BehaviorSubject.create()

    override fun onStart(username: String?) {
        loadData(username)
    }

    private fun loadData(username: String?) {
        progressLiveData.mutable().onNext(true)
        if (username != null) {
            mainRepo.getRepos(username)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        progressLiveData.mutable().onNext(false)
                        profileLifeData.mutable().onNext(it)
                    },
                    onError = {
                        progressLiveData.mutable().onNext(false)
                        errorLiveData.mutable().onNext(it)
                    }
                )
        }
    }

    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as? MutableLiveData<T>
            ?: throw IllegalStateException("It is not MutableLiveData o_O")
    }

    private fun <T : Any> Observable<T>.mutable(): Subject<T> {
        return this as? Subject<T>
            ?: throw IllegalStateException("It is not MutableLiveData o_O")
    }

}
