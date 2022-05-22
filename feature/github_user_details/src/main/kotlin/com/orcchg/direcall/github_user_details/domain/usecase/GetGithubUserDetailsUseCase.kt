package com.orcchg.direcall.github_user_details.domain.usecase

import com.orcchg.direcall.base.Params
import com.orcchg.direcall.base.processSingle
import com.orcchg.direcall.base.usecase.SingleUseCase
import com.orcchg.direcall.github_core.api.data.repository.GithubCoreRepository
import com.orcchg.direcall.github_core.api.domain.model.GithubUserDetails
import com.orcchg.direcall.scheduler_api.SchedulersFactory
import io.reactivex.Single

class GetGithubUserDetailsUseCase(
    private val repository: GithubCoreRepository,
    schedulersFactory: SchedulersFactory
) : SingleUseCase<GithubUserDetails>(schedulersFactory) {

    override fun sourceImpl(params: Params): Single<GithubUserDetails> =
        params.processSingle("login", repository::user)
}
