package inv.di

import inv.di.dsl.ImplDSL
import org.koin.core.component.getScopeName
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier

abstract class DynamicFeatureApi : AbstractFeatureApi() {

    final override val qualifier: Qualifier = getScopeName()

    final override val isLibrary: Boolean = false

    override val dependencies: List<AbstractFeatureApi> = emptyList()

    protected abstract fun ImplDSL.definitions()

    fun createModule(): Module =
        Module(false).apply {
            scope(qualifier) { definitions() }
        }
}