package com.orcchg.direcall.github_core.api.di

import com.orcchg.direcall.github_core.api.data.repository.GithubCoreRepository
import inv.di.FeatureApi
import inv.di.dsl.ApiDSL

object GithubCoreFeatureApi : FeatureApi() {

    override fun ApiDSL.definitions() {
        scoped<GithubCoreRepository>()
    }
}