package com.rodionovmax.mygithubapp.ui.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.rodionovmax.mygithubapp.databinding.FragmentUserProfileBinding
import com.rodionovmax.mygithubapp.domain.entity.UserEntity
import com.rodionovmax.mygithubapp.ui.users.USER_PROFILE

class ProfileActivity : AppCompatActivity(){

    private lateinit var binding: FragmentUserProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val profile = intent.extras?.getParcelable<UserEntity>(USER_PROFILE)

        Glide.with(applicationContext).load(profile?.userImg).centerCrop().into(binding.userImageDetails)
        binding.userNameDetails.text = profile?.username
        binding.userIdDetails.text = profile?.id.toString()
    }

}
