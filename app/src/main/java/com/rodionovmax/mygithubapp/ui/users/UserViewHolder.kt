package com.rodionovmax.mygithubapp.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rodionovmax.mygithubapp.R
import com.rodionovmax.mygithubapp.databinding.ItemUserBinding
import com.rodionovmax.mygithubapp.data.network.UserEntityDto

class UserViewHolder(
    parent: ViewGroup,
    private val onItemClickListener: (userEntity: UserEntityDto) -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
) {
    private lateinit var userEntity: UserEntityDto

    private val binding = ItemUserBinding.bind(itemView).apply {
        userCard.setOnClickListener {
            onItemClickListener.invoke(userEntity)
        }
    }

    fun bind(userEntity: UserEntityDto) {
        this.userEntity = userEntity
        Glide.with(itemView.context).load(userEntity.userImg).centerCrop().into(binding.userImage)
        binding.userName.text = userEntity.username
        binding.userId.text = userEntity.id.toString()

    }
}
