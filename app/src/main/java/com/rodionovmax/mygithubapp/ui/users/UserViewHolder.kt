package com.rodionovmax.mygithubapp.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rodionovmax.mygithubapp.R
import com.rodionovmax.mygithubapp.databinding.ItemUserBinding
import com.rodionovmax.mygithubapp.domain.entity.UserEntity

class UserViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
) {
    private val binding = ItemUserBinding.bind(itemView)

    fun bind(userEntity: UserEntity) {
        with(binding) {
            Glide.with(itemView.context).load(userEntity.userImg).centerCrop().into(userImage)
            userName.text = userEntity.username
            userId.text = userEntity.id.toString()
        }

    }
}
