package com.rodionovmax.mygithubapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rodionovmax.mygithubapp.data.network.RepoEntityDto
import com.rodionovmax.mygithubapp.domain.repo.MainRepo
import com.rodionovmax.mygithubapp.ui.utils.SingleEventLiveData

class ProfileViewModel(private val mainRepo: MainRepo) : ProfileContract.ViewModel {

    override val profileLifeData: LiveData<List<RepoEntityDto>> = MutableLiveData()
    override val errorLiveData: LiveData<Throwable> = SingleEventLiveData()
    override val progressLiveData: LiveData<Boolean> = MutableLiveData()

    override fun onStart(username: String?) {
        loadData(username)
    }

    private fun loadData(username: String?) {
        progressLiveData.mutable().postValue(true)
        if (username != null) {
            mainRepo.getRepos(
                username,
                onSuccess = {
                    progressLiveData.mutable().postValue(false)
                    profileLifeData.mutable().postValue(it)
                },
                onError = {
                    progressLiveData.mutable().postValue(false)
                    errorLiveData.mutable().postValue(it)
                }
            )
        }
    }

    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as? MutableLiveData<T>
            ?: throw IllegalStateException("It is not MutableLiveData o_O")
    }

}