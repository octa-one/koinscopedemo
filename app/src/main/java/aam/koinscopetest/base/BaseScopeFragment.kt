package aam.koinscopetest.base

import aam.koinscopetest.market.di.FeatureScope
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.getKoin
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.LifecycleScopeDelegate
import org.koin.androidx.scope.getScopeOrNull
import org.koin.core.Koin
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.component.getScopeId
import org.koin.core.component.getScopeName
import org.koin.core.scope.Scope
import org.koin.dsl.ScopeDSL
import org.koin.dsl.module

abstract class BaseScopeFragment : Fragment(), AndroidScopeComponent {

    override val scope: Scope by fragmentScope2()

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dependencies.forEach { feature ->
            getKoin().getFeatureScopeManager(feature).linkFrom(scope)
        }
    }

    internal abstract val dependencies: List<FeatureScope>
}

@OptIn(KoinInternalApi::class)
fun Fragment.fragmentScope2() = LifecycleScopeDelegate(this){ koin: Koin ->
    val scopeQualifier = getScopeName()
    if (scopeQualifier !in koin.scopeRegistry.scopeDefinitions) {
        koin.scopeRegistry.loadScopes(
            listOf(module { scope(scopeQualifier, emptyScopeSet) })
        )
    }
    val scope = koin.createScope(getScopeId(), getScopeName())
    scope
}

private val emptyScopeSet: ScopeDSL.() -> Unit = {}