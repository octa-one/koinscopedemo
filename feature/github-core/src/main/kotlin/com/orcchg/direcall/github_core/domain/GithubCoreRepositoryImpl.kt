package com.orcchg.direcall.github_core.domain

import com.orcchg.direcall.github_core.api.data.repository.GithubCoreRepository
import com.orcchg.direcall.github_core.api.domain.model.GithubRepo
import com.orcchg.direcall.github_core.api.domain.model.GithubUser
import com.orcchg.direcall.github_core.api.domain.model.GithubUserDetails
import com.orcchg.direcall.github_core.data.convert.GithubRepoCloudConverter
import com.orcchg.direcall.github_core.data.convert.GithubUserCloudConverter
import com.orcchg.direcall.github_core.data.convert.GithubUserDetailsCloudConverter
import com.orcchg.direcall.github_core.data.remote.GithubCoreCloudRest
import io.reactivex.Single

class GithubCoreRepositoryImpl(
    private val githubCoreCloudRest: GithubCoreCloudRest,
    private val repoConverter: GithubRepoCloudConverter,
    private val userConverter: GithubUserCloudConverter,
    private val userDetailsConverter: GithubUserDetailsCloudConverter
) : GithubCoreRepository {

    override fun repos(login: String): Single<List<GithubRepo>> =
        githubCoreCloudRest.repos(login).map(repoConverter::convertList)

    override fun users(): Single<List<GithubUser>> =
        githubCoreCloudRest.users().map(userConverter::convertList)

    override fun user(login: String): Single<GithubUserDetails> =
        githubCoreCloudRest.userDetails(login).map(userDetailsConverter::convert)

}
