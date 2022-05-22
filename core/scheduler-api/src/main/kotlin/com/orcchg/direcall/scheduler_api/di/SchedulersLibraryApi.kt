package com.orcchg.direcall.scheduler_api.di

import com.orcchg.direcall.scheduler_api.SchedulersFactory
import inv.di.LibraryApi
import inv.di.dsl.ApiDSL

object SchedulersLibraryApi : LibraryApi() {

    override fun ApiDSL.definitions() {
        scoped<SchedulersFactory>()
    }
}