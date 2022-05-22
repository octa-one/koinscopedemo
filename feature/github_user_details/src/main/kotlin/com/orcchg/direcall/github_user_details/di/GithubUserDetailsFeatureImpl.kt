package com.orcchg.direcall.github_user_details.di

import com.orcchg.direcall.github_core.api.di.GithubCoreFeatureApi
import com.orcchg.direcall.github_user_details.domain.usecase.GetGithubUserDetailsUseCase
import com.orcchg.direcall.github_user_details.presentation.viewmodel.GithubUserDetailsViewModel
import com.orcchg.direcall.scheduler_api.di.SchedulersLibraryApi
import inv.di.FeatureImpl
import inv.di.dsl.ImplDSL
import inv.di.resolve
import org.koin.androidx.viewmodel.dsl.viewModel

object GithubUserDetailsFeatureImpl : FeatureImpl() {

    override val dependencies = listOf(
        SchedulersLibraryApi,
        GithubCoreFeatureApi
    )

    override fun ImplDSL.definitions() {

        factory { resolve(::GetGithubUserDetailsUseCase) }
        viewModel { (login: String) -> GithubUserDetailsViewModel(login, getGithubUserDetailsUseCase = get()) }
    }
}