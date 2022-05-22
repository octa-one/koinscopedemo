package com.orcchg.direcall.apptest.di

import com.orcchg.direcall.github_user_details.presentation.viewmodel.GithubUserDetailsViewModel
import org.koin.test.check.CheckParameters

val implParameters: CheckParameters = {

    // GithubUserDetailsFeatureImpl
    withParameter<GithubUserDetailsViewModel> { "login" }
}