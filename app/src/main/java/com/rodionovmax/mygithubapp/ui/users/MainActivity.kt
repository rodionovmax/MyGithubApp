package com.rodionovmax.mygithubapp.ui.users

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.rodionovmax.mygithubapp.App
import com.rodionovmax.mygithubapp.app
import com.rodionovmax.mygithubapp.databinding.ActivityMainBinding
import com.rodionovmax.mygithubapp.domain.model.User
import com.rodionovmax.mygithubapp.ui.profile.ProfileActivity
import io.reactivex.rxjava3.disposables.CompositeDisposable

const val USER_PROFILE = "UserProfile"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter = UsersAdapter {
        viewModel.onUserClicked(it)
    }

    private lateinit var viewModel: UsersContract.ViewModel
    private val viewModelDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        viewModel = extractViewModel()

        viewModelDisposable.addAll(
            viewModel.progressLiveData.subscribe { showProgress(it) },
            viewModel.usersLiveData.subscribe { showUsers(it) },
            viewModel.errorLiveData.subscribe { showError(it) },
            viewModel.openProfileLiveData.subscribe { openProfileScreen(it) }
        )
    }

    override fun onDestroy() {
        viewModelDisposable.dispose()
        super.onDestroy()
    }

    private fun extractViewModel(): UsersContract.ViewModel {
        return lastCustomNonConfigurationInstance as? UsersContract.ViewModel ?: UsersViewModel(app.remoteRepo, app.getDB().userDao)
    }

    override fun onRetainCustomNonConfigurationInstance(): UsersContract.ViewModel {
        return viewModel
    }

    private fun initViews() {
        binding.getUsersBtn.setOnClickListener {
            viewModel.onRefresh()
        }
        initRecyclerView()
        showProgress(false)
    }

    private fun initRecyclerView() {
        binding.recyclerviewUsers.layoutManager = LinearLayoutManager(this)
        binding.recyclerviewUsers.adapter = adapter
    }

    private fun showUsers(users: List<User>) {
        adapter.setData(users)
    }

    private fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    private fun showProgress(inProgress: Boolean) {
        binding.progressBar.isVisible = inProgress
        binding.recyclerviewUsers.isVisible = !inProgress
    }

    private fun openProfileScreen(user: User) {
        val i = Intent(this, ProfileActivity::class.java)
        i.putExtra(USER_PROFILE, user)
        startActivity(i)
    }

}
