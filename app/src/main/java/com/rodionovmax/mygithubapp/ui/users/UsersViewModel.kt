package com.rodionovmax.mygithubapp.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rodionovmax.mygithubapp.data.network.UserEntityDto
import com.rodionovmax.mygithubapp.domain.repo.MainRepo
import com.rodionovmax.mygithubapp.ui.utils.SingleEventLiveData

class UsersViewModel(private val mainRepo: MainRepo) : UsersContract.ViewModel {
    override val usersLiveData: LiveData<List<UserEntityDto>> = MutableLiveData()
    override val errorLiveData: LiveData<Throwable> = SingleEventLiveData()
    override val progressLiveData: LiveData<Boolean> = MutableLiveData()
    override val openProfileLiveData: LiveData<UserEntityDto> = SingleEventLiveData()

    override fun onRefresh() {
        loadData()
    }

    private fun loadData() {
        progressLiveData.mutable().postValue(true)
        mainRepo.getUsers(
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

    override fun onUserClicked(userEntity: UserEntityDto) {
        openProfileLiveData.mutable().postValue(userEntity)
    }


    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as? MutableLiveData<T> ?: throw IllegalStateException("This is not live data")
    }

}

