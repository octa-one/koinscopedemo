package com.orcchg.direcall.apptest

import android.annotation.SuppressLint
import com.orcchg.direcall.apptest.di.implParameters
import com.orcchg.direcall.apptest.di.overrideModules
import inv.di.FeatureApi
import inv.di.FeatureImpl
import inv.di.dsl.ApiDefinition
import inv.di.getFeatureApiManager
import inv.di.internal.ModuleInternal
import io.mockk.mockkClass
import org.koin.core.module.Module
import org.koin.test.check.CheckParameters
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProvider

fun checkFeatures(features: List<FeatureImpl>) {
    checkFeatures(features, overrideModules, implParameters)
}

internal fun checkFeatures(
    features: List<FeatureImpl>,
    featuresOverrideModules: List<Module>,
    featuresImplParameters: CheckParameters
) {
    MockProvider.register { kClass -> mockkClass(kClass) }

    val koinApp = createKoinApplication(features)
    val koin = koinApp.koin

    val testScope = koin.createTestScope()

    features.forEach { impl ->
        checkApiDefinitions(impl.createModule(), impl.api)
        koin.getFeatureApiManager(impl).link(testScope)
    }

    koin.loadModules(featuresOverrideModules, true)

    koin.checkModules(featuresImplParameters)

    koinApp.close()
}

@SuppressLint("RestrictedApi")
private fun checkApiDefinitions(module: Module, api: FeatureApi?) {
    if (api == null) return
    val apiDefinitions = api.getApiDefinitions().toMutableSet()
    ModuleInternal.getMappings(module).values.forEach { factory ->
        val def = factory.beanDefinition

        apiDefinitions.remove(
            ApiDefinition(def.kind, def.qualifier, def.primaryType)
        )
        apiDefinitions.removeAll(
            def.secondaryTypes.map { ApiDefinition(def.kind, def.qualifier, it) }.toSet()
        )
    }
    if (apiDefinitions.isNotEmpty()) {
        throw IllegalStateException("Definitions not found for: ${apiDefinitions.joinToString { it.type.simpleName.toString() }}")
    }
}
