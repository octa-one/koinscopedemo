package com.orcchg.direcall.github_core.data.convert

import com.orcchg.direcall.github_core.api.domain.model.GithubUser
import com.orcchg.direcall.github_core.data.model.GithubUserEntity
import com.orcchg.direcall.util.Converter

class GithubUserCloudConverter : Converter<GithubUserEntity, GithubUser> {

    override fun convert(from: GithubUserEntity): GithubUser =
        GithubUser(
            login = from.login,
            avatarUrl = from.avatarUrl
        )
}
