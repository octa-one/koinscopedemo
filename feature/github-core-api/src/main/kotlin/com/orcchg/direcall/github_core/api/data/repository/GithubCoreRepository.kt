package com.orcchg.direcall.github_core.api.data.repository

import com.orcchg.direcall.github_core.api.domain.model.GithubRepo
import com.orcchg.direcall.github_core.api.domain.model.GithubUser
import com.orcchg.direcall.github_core.api.domain.model.GithubUserDetails
import io.reactivex.Single

interface GithubCoreRepository {

    fun repos(login: String): Single<List<GithubRepo>>

    fun users(): Single<List<GithubUser>>

    fun user(login: String): Single<GithubUserDetails>
}