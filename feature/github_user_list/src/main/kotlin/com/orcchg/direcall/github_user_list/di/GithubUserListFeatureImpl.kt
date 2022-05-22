package com.orcchg.direcall.github_user_list.di

import com.orcchg.direcall.github_core.api.di.GithubCoreFeatureApi
import com.orcchg.direcall.github_user_list.domain.usecase.GetGithubUsersUseCase
import com.orcchg.direcall.github_user_list.presentation.viewmodel.GithubUserListViewModel
import com.orcchg.direcall.scheduler_api.di.SchedulersLibraryApi
import inv.di.FeatureImpl
import inv.di.dsl.ImplDSL
import inv.di.resolve
import org.koin.androidx.viewmodel.dsl.viewModel

object GithubUserListFeatureImpl : FeatureImpl() {

    override val dependencies = listOf(
        SchedulersLibraryApi,
        GithubCoreFeatureApi
    )

    override fun ImplDSL.definitions() {

        factory { resolve(::GetGithubUsersUseCase) }
        viewModel { resolve(::GithubUserListViewModel) }
    }
}