package com.rodionovmax.mygithubapp.ui.users

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.rodionovmax.mygithubapp.app
import com.rodionovmax.mygithubapp.databinding.ActivityMainBinding
import com.rodionovmax.mygithubapp.domain.entity.UserEntity
import com.rodionovmax.mygithubapp.domain.repo.UsersRepo

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter = UsersAdapter()
    private val usersRepo: UsersRepo by lazy { app.usersRepo }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        binding.getUsersBtn.setOnClickListener {
            loadData()
        }
        initRecyclerView()
        showProgress(false)
    }

    private fun initRecyclerView() {
        binding.recyclerviewUsers.layoutManager = LinearLayoutManager(this)
        binding.recyclerviewUsers.adapter = adapter
    }

    private fun loadData() {
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
    }

    private fun onError(error: Throwable) {
        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
    }

    private fun onLoadedData(data: List<UserEntity>) {
        adapter.setData(data)
    }

    private fun showProgress(inProgress: Boolean) {
        binding.progressBar.isVisible = inProgress
        binding.recyclerviewUsers.isVisible = !inProgress
    }
}
