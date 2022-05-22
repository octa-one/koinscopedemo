package com.orcchg.direcall.github_core.data.convert

import com.orcchg.direcall.github_core.api.domain.model.GithubUserDetails
import com.orcchg.direcall.github_core.data.model.GithubUserDetailsEntity
import com.orcchg.direcall.util.Converter

class GithubUserDetailsCloudConverter : Converter<GithubUserDetailsEntity, GithubUserDetails> {

    override fun convert(from: GithubUserDetailsEntity): GithubUserDetails =
        GithubUserDetails(
            login = from.login,
            url = from.reposUrl,
            avatarUrl = from.avatarUrl
        )
}
