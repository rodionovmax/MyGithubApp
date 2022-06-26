package com.rodionovmax.mygithubapp.ui.users

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.rodionovmax.mygithubapp.app
import com.rodionovmax.mygithubapp.databinding.ActivityMainBinding
import com.rodionovmax.mygithubapp.data.network.UserEntityDto
import com.rodionovmax.mygithubapp.ui.profile.ProfileActivity

const val USER_PROFILE = "UserProfile"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter = UsersAdapter {
        viewModel.onUserClicked(it)
    }

    private lateinit var viewModel: UsersContract.ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel = extractViewModel()

        viewModel.progressLiveData.observe(this) {showProgress(it)}
        viewModel.errorLiveData.observe(this) {showError(it)}
        viewModel.usersLiveData.observe(this) {showUsers(it)}
        viewModel.openProfileLiveData.observe(this) {openProfileScreen(it)}
    }

    private fun extractViewModel(): UsersContract.ViewModel {
        return lastCustomNonConfigurationInstance as? UsersContract.ViewModel ?: UsersViewModel(app.mainRepo)
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

    private fun showUsers(users: List<UserEntityDto>) {
        adapter.setData(users)
    }

    private fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    private fun showProgress(inProgress: Boolean) {
        binding.progressBar.isVisible = inProgress
        binding.recyclerviewUsers.isVisible = !inProgress
    }

    private fun openProfileScreen(userEntity: UserEntityDto) {
        val i = Intent(this, ProfileActivity::class.java)
        i.putExtra(USER_PROFILE, userEntity)
        startActivity(i)
    }

}
