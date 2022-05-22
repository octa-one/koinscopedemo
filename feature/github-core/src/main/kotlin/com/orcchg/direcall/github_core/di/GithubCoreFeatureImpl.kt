package com.orcchg.direcall.github_repo.di

import com.orcchg.direcall.github_core.api.data.repository.GithubCoreRepository
import com.orcchg.direcall.github_core.api.di.GithubCoreFeatureApi
import com.orcchg.direcall.github_core.data.convert.GithubRepoCloudConverter
import com.orcchg.direcall.github_core.data.convert.GithubUserCloudConverter
import com.orcchg.direcall.github_core.data.convert.GithubUserDetailsCloudConverter
import com.orcchg.direcall.github_core.data.remote.GithubCoreCloudRest
import com.orcchg.direcall.github_core.domain.GithubCoreRepositoryImpl
import com.orcchg.direcall.network.api.di.NetworkLibraryApi
import inv.di.FeatureImpl
import inv.di.dsl.ImplDSL
import inv.di.resolve
import retrofit2.Retrofit
import retrofit2.create

object GithubCoreFeatureImpl : FeatureImpl(GithubCoreFeatureApi) {

    override val dependencies = listOf(
        NetworkLibraryApi
    )

    override fun ImplDSL.definitions() {
        factory { resolve(::GithubRepoCloudConverter) }
        factory { resolve(::GithubUserCloudConverter) }
        factory { resolve(::GithubUserDetailsCloudConverter) }

        factory<GithubCoreCloudRest> { get<Retrofit>().create() }

        scoped<GithubCoreRepository> { resolve(::GithubCoreRepositoryImpl) }
    }
}