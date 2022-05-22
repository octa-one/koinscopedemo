package com.orcchg.direcall.github_core.api.domain.model

data class GithubUserDetails(
    val login: String,
    val url: String?,
    val avatarUrl: String?
)