package com.orcchg.direcall.github_repo

import com.orcchg.direcall.apptest.checkDynamicFeatureApi
import com.orcchg.direcall.features
import com.orcchg.direcall.github_repo.di.GithubRepoFeatureApi
import com.orcchg.direcall.github_repo.presentation.viewmodel.GithubRepoViewModel
import org.junit.Test

class GithubRepoFeatureTest {

    @Test
    fun `check github repo feature`() {
        checkDynamicFeatureApi(features(), GithubRepoFeatureApi) {
            withParameter<GithubRepoViewModel> { "login" }
        }
    }
}