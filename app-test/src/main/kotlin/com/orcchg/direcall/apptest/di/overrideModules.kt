package com.orcchg.direcall.apptest.di

import com.orcchg.direcall.network.api.di.NetworkLibraryApi
import io.mockk.mockk
import okhttp3.OkHttpClient
import org.koin.dsl.module

internal val overrideModules = listOf(
    module {
        scope<NetworkLibraryApi> {
            scoped<OkHttpClient> { mockk() }
        }
    }
)
