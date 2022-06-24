package com.rodionovmax.mygithubapp.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rodionovmax.mygithubapp.R
import com.rodionovmax.mygithubapp.databinding.ItemRepoBinding
import com.rodionovmax.mygithubapp.data.network.RepoEntityDto

class ReposViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
) {
    private val binding = ItemRepoBinding.bind(itemView)

    fun bind(repoEntity: RepoEntityDto) {
        binding.repoName.text = repoEntity.name
    }
}