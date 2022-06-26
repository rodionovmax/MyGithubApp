package com.rodionovmax.mygithubapp.ui.users

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rodionovmax.mygithubapp.data.network.UserEntityDto
import com.rodionovmax.mygithubapp.domain.entity.UserEntity

class UsersAdapter(
    private val onItemClickListener: (UserEntity) -> Unit
) : RecyclerView.Adapter<UserViewHolder>() {

    private val data = mutableListOf<UserEntity>()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(parent, onItemClickListener)

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount() =  data.size

    override fun getItemId(position: Int) = getItem(position).id

    private fun getItem(pos: Int) = data[pos]

    fun setData(users: List<UserEntity>) {
        data.clear()
        data.addAll(users)
        notifyDataSetChanged()
    }
}
