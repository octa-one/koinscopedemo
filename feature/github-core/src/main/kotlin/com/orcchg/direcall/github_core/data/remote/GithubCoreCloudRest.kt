package com.orcchg.direcall.github_core.data.remote

import com.orcchg.direcall.github_core.data.model.GithubRepoEntity
import com.orcchg.direcall.github_core.data.model.GithubUserDetailsEntity
import com.orcchg.direcall.github_core.data.model.GithubUserEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubCoreCloudRest {

    @GET("users/{login}/repos")
    fun repos(@Path("login") login: String): Single<List<GithubRepoEntity>>

    @GET("users/{login}")
    fun userDetails(@Path("login") login: String): Single<GithubUserDetailsEntity>

    @GET("users")
    fun users(): Single<List<GithubUserEntity>>
}