package com.orcchg.direcall.network.api.di

import inv.di.LibraryApi
import inv.di.dsl.ApiDSL
import retrofit2.Retrofit

object NetworkLibraryApi : LibraryApi() {

    override fun ApiDSL.definitions() {
        scoped<Retrofit>()
    }
}