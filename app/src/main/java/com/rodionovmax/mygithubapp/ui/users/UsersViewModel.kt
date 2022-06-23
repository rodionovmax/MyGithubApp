package com.rodionovmax.mygithubapp.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rodionovmax.mygithubapp.domain.entity.UserEntity
import com.rodionovmax.mygithubapp.domain.repo.UsersRepo
import com.rodionovmax.mygithubapp.utils.SingleEventLiveData

class UsersViewModel(private val usersRepo: UsersRepo) : UsersContract.ViewModel {
    override val usersLiveData: LiveData<List<UserEntity>> = MutableLiveData()
    override val errorLiveData: LiveData<Throwable> = SingleEventLiveData()
    override val progressLiveData: LiveData<Boolean> = MutableLiveData()
    override val openProfileLiveData: LiveData<UserEntity> = SingleEventLiveData()

    override fun onRefresh() {
        loadData()
    }

    private fun loadData() {
        progressLiveData.mutable().postValue(true)
        usersRepo.getUsers(
            onSuccess = {
                progressLiveData.mutable().postValue(false)
                usersLiveData.mutable().postValue(it)
            },
            onError = {
                progressLiveData.mutable().postValue(false)
                errorLiveData.mutable().postValue(it)
            }
        )
    }

    override fun onUserClicked(userEntity: UserEntity) {
        openProfileLiveData.mutable().postValue(userEntity)
    }


    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as? MutableLiveData<T> ?: throw IllegalStateException("This is not live data")
    }

}

