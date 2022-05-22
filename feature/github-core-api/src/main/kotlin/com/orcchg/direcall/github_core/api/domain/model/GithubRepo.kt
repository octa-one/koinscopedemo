package com.orcchg.direcall.github_core.api.domain.model

data class GithubRepo(
    val name: String,
    val size: Int,
    val url: String,
    val defaultBranch: String
)