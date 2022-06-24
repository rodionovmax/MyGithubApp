package com.rodionovmax.mygithubapp.ui.profile

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rodionovmax.mygithubapp.domain.entity.RepoEntity

class ReposAdapter : RecyclerView.Adapter<ReposViewHolder>() {

    private val data = mutableListOf<RepoEntity>()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ReposViewHolder(parent)

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount() = data.size

    private fun getItem(pos: Int) = data[pos]

    @SuppressLint("NotifyDataSetChanged")
    fun setData(repos: List<RepoEntity>) {
        data.clear()
        data.addAll(repos)
        notifyDataSetChanged()
    }
}