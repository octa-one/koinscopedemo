package com.orcchg.direcall.apptest

import com.orcchg.direcall.apptest.di.implParameters
import com.orcchg.direcall.apptest.di.overrideModules
import inv.di.DynamicFeatureApi
import inv.di.FeatureImpl
import inv.di.getFeatureApiManager
import io.mockk.mockkClass
import org.koin.core.module.Module
import org.koin.test.check.CheckParameters
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProvider

fun checkDynamicFeatureApi(
    features: List<FeatureImpl>,
    dynamicFeature: DynamicFeatureApi,
    module: Module? = null,
    parameters: CheckParameters? = null
) {
    checkDynamicFeatureApi(features, overrideModules, implParameters, dynamicFeature, module, parameters)
}

internal fun checkDynamicFeatureApi(
    features: List<FeatureImpl>,
    featuresOverrideModules: List<Module>,
    featuresImplParameters: CheckParameters,
    dynamicFeature: DynamicFeatureApi,
    module: Module? = null,
    parameters: CheckParameters? = null
) {

    MockProvider.register { kClass -> mockkClass(kClass) }

    val koinApp = createKoinApplication(features)
    val koin = koinApp.koin

    val testScope = koin.createTestScope()

    features.forEach { impl ->
        koin.getFeatureApiManager(impl).link(testScope)
    }

    val scopeManager = koin.getFeatureApiManager(dynamicFeature)
    scopeManager.link(testScope)

    koinApp.koin.loadModules(featuresOverrideModules, true)
    koinApp.koin.loadModules(listOfNotNull(module), true)

    koin.checkModules {
        featuresImplParameters(this)
        parameters?.invoke(this)
    }

    koinApp.close()
}