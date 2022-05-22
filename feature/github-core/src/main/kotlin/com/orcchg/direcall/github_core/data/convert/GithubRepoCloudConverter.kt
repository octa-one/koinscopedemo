package com.orcchg.direcall.github_core.data.convert

import com.orcchg.direcall.github_core.api.domain.model.GithubRepo
import com.orcchg.direcall.github_core.data.model.GithubRepoEntity
import com.orcchg.direcall.util.Converter

class GithubRepoCloudConverter : Converter<GithubRepoEntity, GithubRepo> {

    override fun convert(from: GithubRepoEntity): GithubRepo =
        GithubRepo(
            name = from.name,
            size = from.size,
            url = from.url,
            defaultBranch = from.defaultBranch
        )
}
