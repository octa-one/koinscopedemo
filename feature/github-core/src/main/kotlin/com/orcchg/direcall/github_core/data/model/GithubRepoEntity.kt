package com.orcchg.direcall.github_core.data.model

import com.squareup.moshi.Json

data class GithubRepoEntity(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "default_branch") val defaultBranch: String,
    @Json(name = "size") val size: Int,
    @Json(name = "url") val url: String
)
