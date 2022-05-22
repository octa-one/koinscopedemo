package com.orcchg.direcall.scheduler.di

import com.orcchg.direcall.base.usecase.UseCaseThreadExecutor
import com.orcchg.direcall.scheduler.SchedulersFactoryImpl
import com.orcchg.direcall.scheduler_api.SchedulersFactory
import com.orcchg.direcall.scheduler_api.di.SchedulersLibraryApi
import inv.di.LibraryImpl
import inv.di.dsl.ImplDSL
import inv.di.resolve

object SchedulersLibraryImpl : LibraryImpl(SchedulersLibraryApi) {

    override fun ImplDSL.definitions() {
        scoped { resolve(::UseCaseThreadExecutor) }
        scoped<SchedulersFactory> { resolve(::SchedulersFactoryImpl) }
    }
}