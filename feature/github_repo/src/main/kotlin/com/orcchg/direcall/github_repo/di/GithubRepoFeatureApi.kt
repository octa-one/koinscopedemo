package com.orcchg.direcall.github_repo.di

import com.orcchg.direcall.github_core.api.di.GithubCoreFeatureApi
import com.orcchg.direcall.github_repo.domain.usecase.GetGithubReposUseCase
import com.orcchg.direcall.github_repo.presentation.viewmodel.GithubRepoViewModel
import com.orcchg.direcall.scheduler_api.di.SchedulersLibraryApi
import inv.di.DynamicFeatureApi
import inv.di.dsl.ImplDSL
import inv.di.resolve
import org.koin.androidx.viewmodel.dsl.viewModel

object GithubRepoFeatureApi : DynamicFeatureApi() {

    override val dependencies = listOf(
        SchedulersLibraryApi,
        GithubCoreFeatureApi
    )

    override fun ImplDSL.definitions() {

        factory { resolve(::GetGithubReposUseCase) }
        viewModel { (login: String) -> GithubRepoViewModel(login, getGithubReposUseCase = get()) }
    }
}