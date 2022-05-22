package com.orcchg.direcall.apptest

import inv.di.FeatureImpl
import io.mockk.mockk
import org.koin.android.ext.koin.androidContext
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.koinApplication
import org.koin.dsl.module

internal fun createKoinApplication(features: List<FeatureImpl>): KoinApplication {
    return koinApplication {
        androidContext(mockk(relaxed = true))
        modules(features.map { it.createModule() })
        allowOverride(true)
    }
}

@OptIn(KoinInternalApi::class)
internal fun Koin.createTestScope(): Scope {
    val qualifier = named("test")
    scopeRegistry.loadScopes(listOf(module { scope(qualifier) {} }))
    return createScope("test", qualifier)
}