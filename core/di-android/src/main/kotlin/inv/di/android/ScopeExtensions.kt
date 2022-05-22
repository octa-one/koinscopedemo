package inv.di.android

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import inv.di.getFeatureApiManager
import org.koin.android.ext.android.getKoin
import org.koin.androidx.scope.LifecycleScopeDelegate
import org.koin.core.Koin
import org.koin.core.component.getScopeId
import org.koin.core.component.getScopeName

fun <F> F.fragmentScope() where F : Fragment, F : DependenciesComponent = lifecycleOwnerScope(getKoin())

fun <A> A.activityScope() where A : AppCompatActivity, A : DependenciesComponent = lifecycleOwnerScope(getKoin())

@Suppress("NAME_SHADOWING")
private fun <L> L.lifecycleOwnerScope(koin: Koin) where L : LifecycleOwner, L : DependenciesComponent =
    LifecycleScopeDelegate<L>(this, koin) delegate@{ koin: Koin ->
        val lifecycleScope = koin.createScope(getScopeId(), getScopeName())
        dependencies.forEach { feature ->
            koin.getFeatureApiManager(feature).link(lifecycleScope)
        }
        return@delegate lifecycleScope
    }