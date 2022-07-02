package com.rodionovmax.mygithubapp.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rodionovmax.mygithubapp.domain.model.User
import com.rodionovmax.mygithubapp.domain.repo.RemoteRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject


class UsersViewModel(
    private val remoteRepo: RemoteRepo
) : UsersContract.ViewModel, ViewModel() {

    override val usersLiveData: Observable<List<User>> = BehaviorSubject.create()
    override val errorLiveData: Observable<Throwable> = BehaviorSubject.create()
    override val progressLiveData: Observable<Boolean> = BehaviorSubject.create()
    override val openProfileLiveData: Observable<User> = BehaviorSubject.create()

    override fun onRefresh() {
        loadData()
    }

    private fun loadData() {
        progressLiveData.mutable().onNext(true)
        remoteRepo.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    progressLiveData.mutable().onNext(false)
                    usersLiveData.mutable().onNext(it)
                },
                onError = {
                    progressLiveData.mutable().onNext(false)
                    errorLiveData.mutable().onNext(it)
                }
            )
    }

    override fun onUserClicked(user: User) {
        openProfileLiveData.mutable().onNext(user)
    }


    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as? MutableLiveData<T> ?: throw IllegalStateException("This is not live data")
    }

    private fun <T : Any> Observable<T>.mutable(): Subject<T> {
        return this as? Subject<T>
            ?: throw IllegalStateException("It is not MutableLiveData o_O")
    }

}

