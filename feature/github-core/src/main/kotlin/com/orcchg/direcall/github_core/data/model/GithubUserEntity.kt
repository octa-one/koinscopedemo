package com.orcchg.direcall.github_core.data.model

import com.squareup.moshi.Json

data class GithubUserEntity(
    @Json(name = "id") val id: Int,
    @Json(name = "login") val login: String,
    @Json(name = "avatar_url") val avatarUrl: String? = null
)
