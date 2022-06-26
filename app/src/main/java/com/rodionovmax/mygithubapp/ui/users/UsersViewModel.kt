package com.rodionovmax.mygithubapp.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rodionovmax.mygithubapp.domain.entity.UserEntity
import com.rodionovmax.mygithubapp.domain.repo.MainRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject


class UsersViewModel(private val mainRepo: MainRepo) : UsersContract.ViewModel {
    override val usersLiveData: Observable<List<UserEntity>> = BehaviorSubject.create()
    override val errorLiveData: Observable<Throwable> = BehaviorSubject.create()
    override val progressLiveData: Observable<Boolean> = BehaviorSubject.create()
    override val openProfileLiveData: Observable<UserEntity> = BehaviorSubject.create()

    override fun onRefresh() {
        loadData()
    }

    private fun loadData() {
        progressLiveData.mutable().onNext(true)
        mainRepo.getUsers()
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

    override fun onUserClicked(userEntity: UserEntity) {
        openProfileLiveData.mutable().onNext(userEntity)
    }


    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as? MutableLiveData<T> ?: throw IllegalStateException("This is not live data")
    }

    private fun <T : Any> Observable<T>.mutable(): Subject<T> {
        return this as? Subject<T>
            ?: throw IllegalStateException("It is not MutableLiveData o_O")
    }

}

