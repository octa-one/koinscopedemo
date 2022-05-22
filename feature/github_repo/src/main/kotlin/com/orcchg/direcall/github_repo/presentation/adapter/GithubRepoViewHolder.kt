package com.orcchg.direcall.github_repo.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.orcchg.direcall.github_core.api.domain.model.GithubRepo
import com.orcchg.direcall.github_repo.databinding.RvGithubRepoListItemBinding

class GithubRepoViewHolder(
    private val binding: RvGithubRepoListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: GithubRepo) {
        binding.tvTitle.text = model.name
    }
}
