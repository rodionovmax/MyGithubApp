package com.rodionovmax.mygithubapp.ui.users

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.rodionovmax.mygithubapp.app
import com.rodionovmax.mygithubapp.databinding.ActivityMainBinding
import com.rodionovmax.mygithubapp.domain.entity.UserEntity
import com.rodionovmax.mygithubapp.domain.repo.UsersRepo
import com.rodionovmax.mygithubapp.ui.profile.ProfileActivity


class MainActivity : AppCompatActivity(), UsersContract.View {

    private lateinit var binding: ActivityMainBinding
    private val adapter = UsersAdapter {
        presenter.onUserClicked(it)
    }
    private val usersRepo: UsersRepo by lazy { app.usersRepo }

    private lateinit var presenter: UsersContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

        presenter = extractPresenter()
        presenter.attach(this)
    }

    private fun extractPresenter(): UsersContract.Presenter {
        return lastCustomNonConfigurationInstance as? UsersContract.Presenter ?: UsersPresenter(app.usersRepo)
    }

    override fun onRetainCustomNonConfigurationInstance(): UsersContract.Presenter {
        return presenter
    }

    private fun initViews() {
        binding.getUsersBtn.setOnClickListener {
            presenter.onRefresh()
        }
        initRecyclerView()
        showProgress(false)
    }

    private fun initRecyclerView() {
        binding.recyclerviewUsers.layoutManager = LinearLayoutManager(this)
        binding.recyclerviewUsers.adapter = adapter
    }

    override fun showUsers(users: List<UserEntity>) {
        adapter.setData(users)
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress(inProgress: Boolean) {
        binding.progressBar.isVisible = inProgress
        binding.recyclerviewUsers.isVisible = !inProgress
    }

    override fun openProfileScreen(userEntity: UserEntity) {
        startActivity(Intent(this, ProfileActivity::class.java))
        val i = Intent(this, ProfileActivity::class.java)
        i.putExtra("UserProfile", userEntity)
        startActivity(i)
    }

    /*private fun loadData() {
        showProgress(true)
        usersRepo.getUsers(
            onSuccess = {
                showProgress(false)
                onLoadedData(it)
            },
            onError = {
                showProgress(false)
                onError(it)
            }
        )
    }*/

    /*private fun onError(error: Throwable) {
        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
    }*/

    /*private fun onLoadedData(data: List<UserEntity>) {
        adapter.setData(data)
    }*/

}
