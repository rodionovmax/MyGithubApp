package com.rodionovmax.mygithubapp.ui.profile

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.rodionovmax.mygithubapp.app
import com.rodionovmax.mygithubapp.databinding.ActivityProfileBinding
import com.rodionovmax.mygithubapp.domain.model.Repo
import com.rodionovmax.mygithubapp.domain.model.User
import com.rodionovmax.mygithubapp.ui.users.USER_PROFILE
import io.reactivex.rxjava3.disposables.CompositeDisposable

class ProfileActivity : AppCompatActivity(){

    private lateinit var binding: ActivityProfileBinding
    private lateinit var viewModel: ProfileContract.ViewModel
    private var profile: User? = null
    private val adapter = ReposAdapter()
    private val viewModelDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        profile = intent.extras?.getParcelable<User>(USER_PROFILE)

        viewModel = extractViewModel()
        viewModelDisposable.addAll(
            viewModel.progressLiveData.subscribe { showProgress(it) },
            viewModel.profileLifeData.subscribe { showRepos(it) },
            viewModel.errorLiveData.subscribe { showError(it) }
        )
        initViews()

    }

    override fun onDestroy() {
        viewModelDisposable.dispose()
        super.onDestroy()
    }

    private fun extractViewModel(): ProfileContract.ViewModel {
        return lastCustomNonConfigurationInstance as? ProfileContract.ViewModel
            ?: ProfileViewModel(app.remoteRepo)
    }

    override fun onRetainCustomNonConfigurationInstance(): ProfileContract.ViewModel {
        return viewModel
    }

    private fun initViews() {
        viewModel.onStart(profile?.username)
        initRecyclerView()

        showProgress(false)
    }

    private fun showProfileDetails() {
        Glide.with(applicationContext).load(profile?.userImg).centerCrop().into(binding.userImageDetails)
        binding.userNameDetails.text = profile?.username
        binding.userIdDetails.text = profile?.id.toString()
    }

    private fun showRepos(repos: List<Repo>) {
        showProfileDetails()
        adapter.setData(repos)
    }

    private fun showProgress(inProgress: Boolean) {
        binding.profileProgressBar.isVisible = inProgress
        binding.recyclerviewRepos.isVisible = !inProgress
    }

    private fun showError(throwable: Throwable?) {
        Toast.makeText(this, throwable?.message, Toast.LENGTH_SHORT).show()
    }

    private fun initRecyclerView() {
        binding.recyclerviewRepos.layoutManager = LinearLayoutManager(this)
        binding.recyclerviewRepos.adapter = adapter
    }

}
