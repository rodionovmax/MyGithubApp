package com.rodionovmax.mygithubapp.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rodionovmax.mygithubapp.R
import com.rodionovmax.mygithubapp.databinding.ItemUserBinding
import com.rodionovmax.mygithubapp.domain.model.User

class UserViewHolder(
    parent: ViewGroup,
    private val onItemClickListener: (user: User) -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
) {
    private lateinit var user: User

    private val binding = ItemUserBinding.bind(itemView).apply {
        userCard.setOnClickListener {
            onItemClickListener.invoke(user)
        }
    }

    fun bind(user: User) {
        this.user = user
        Glide.with(itemView.context).load(user.userImg).centerCrop().into(binding.userImage)
        binding.userName.text = user.username
        binding.userId.text = user.id.toString()

    }
}
