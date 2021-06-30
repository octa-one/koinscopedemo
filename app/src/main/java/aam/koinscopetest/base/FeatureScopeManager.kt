package aam.koinscopetest.base

import aam.koinscopetest.market.di.CoreFeatureScope
import aam.koinscopetest.market.di.FeatureScope
import android.util.Log
import androidx.annotation.CallSuper
import org.koin.core.Koin
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.TypeQualifier
import org.koin.core.scope.Scope
import org.koin.core.scope.ScopeCallback
import org.koin.core.scope.ScopeID
import org.koin.ext.getFullName

@Suppress("MemberVisibilityCanBePrivate")
abstract class AbstractFeatureScopeManager<F : FeatureScope>(
    protected val koin: Koin,
    protected val feature: F
) {

    protected val featureClass = feature::class
    protected val scopeId: ScopeID = featureClass.getFullName()
    protected val scopeQualifier: Qualifier = TypeQualifier(featureClass)

    protected val childScopes = mutableSetOf<ScopeID>()

    private fun getOrCreateScope(): Scope {
        var featureScope = koin.getScopeOrNull(scopeId)

        if (featureScope == null) {
            check(childScopes.isEmpty()) { "Refs count $${childScopes.size} != 0" }
            featureScope = koin.createScope(scopeId, scopeQualifier, this)
                .also { scope ->
                    feature.dependencies.forEach { dependency ->
                        koin.getFeatureScopeManager(dependency).linkFrom(scope)
                    }
                }
        }

        return featureScope
    }

    open fun isScopeActive(): Boolean = childScopes.isNotEmpty()

    @CallSuper
    open fun linkFrom(childScope: Scope) {
        if (childScope.id in childScopes) return
        val featureScope = getOrCreateScope()
        childScopes.add(childScope.id)
        childScope.linkTo(featureScope)
    }
}

private class FeatureScopeManager<F : FeatureScope> (koin: Koin, feature: F) :
    AbstractFeatureScopeManager<F>(koin, feature) {

    override fun linkFrom(childScope: Scope) {
        super.linkFrom(childScope)
        childScope.registerCallback(object : ScopeCallback {
            override fun onScopeClose(scope: Scope) {
                if (childScopes.remove(scope.id)) {
                    if (childScopes.isEmpty()) {
                        koin.getScopeOrNull(scopeId)?.close()
                    }
                }
            }
        })
    }
}

private class CoreFeatureScopeManager<F : FeatureScope>(koin: Koin, feature: F) :
    AbstractFeatureScopeManager<F>(koin, feature) {

    override fun isScopeActive(): Boolean = true
}

fun <F : FeatureScope> Koin.getFeatureScopeManager(feature: F): AbstractFeatureScopeManager<F> {
    val scopeManager = getScopeOrNull(feature::class.getFullName())?.get<AbstractFeatureScopeManager<F>>()
    if (scopeManager != null) return scopeManager
    return if (feature is CoreFeatureScope) CoreFeatureScopeManager(this, feature)
    else FeatureScopeManager(this, feature)
}