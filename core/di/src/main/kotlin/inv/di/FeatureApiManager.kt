package inv.di

import androidx.annotation.MainThread
import org.koin.core.Koin
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.core.scope.ScopeCallback
import org.koin.core.scope.ScopeID

class FeatureApiManager<F : AbstractFeatureApi> internal constructor(
    private val koin: Koin,
    private val feature: F
) {

    private val featureScope: Scope = createScope()
    private val childScopes = mutableSetOf<ScopeID>()

    private val scopeCallback = object : ScopeCallback {
        override fun onScopeClose(scope: Scope) {
            if (childScopes.remove(scope.id)) {
                if (!feature.isLibrary && childScopes.isEmpty()) {
                    featureScope.close()
                }
            }
        }
    }

    @MainThread
    fun link(childScope: Scope) {
        if (childScope.id in childScopes) return

        childScopes.add(childScope.id)
        childScope.linkTo(featureScope)
        childScope.registerCallback(scopeCallback)
    }

    private fun createScope(): Scope {
        loadScopeIfNeeded()
        return koin.createScope(feature.qualifier.value, feature.qualifier, this)
            .also { scope ->
                feature.dependencies.forEach { dependency ->
                    koin.getFeatureApiManager(dependency).link(scope)
                }
            }
    }

    @OptIn(KoinInternalApi::class)
    private fun loadScopeIfNeeded() {
        if (feature.qualifier !in koin.scopeRegistry.scopeDefinitions) {
            when (feature) {
                is FeatureApi, is FeatureImpl -> {
                    koin.get<Map<Qualifier, FeatureImpl>>(FEATURE_MAP_QUALIFIER)
                        .getOrElse(feature.qualifier) { error("Необходимо указать реализацию ${feature.qualifier} в :app") }
                        .let { featureImpl -> koin.loadModules(listOf(featureImpl.createModule()), allowOverride = false) }
                }
                is DynamicFeatureApi -> {
                    koin.loadModules(listOf(feature.createModule()), allowOverride = false)
                }
            }
        }
    }

    companion object {

        val FEATURE_MAP_QUALIFIER = named("features")
    }
}