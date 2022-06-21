package com.rodionovmax.mygithubapp.ui.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rodionovmax.mygithubapp.databinding.FragmentUserProfileBinding

class ProfileActivity : AppCompatActivity(){

    private lateinit var binding: FragmentUserProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}
