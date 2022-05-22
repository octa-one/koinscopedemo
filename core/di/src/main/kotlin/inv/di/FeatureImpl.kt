package inv.di

import inv.di.dsl.ImplDSL
import org.koin.core.component.getScopeName
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier

/**
 * Базовый класс для объявления реализаций api фичи в impl модуле,
 * а так же [dependencies], необходимых для создания этих реализаций.
 */
abstract class FeatureImpl(val api: FeatureApi? = null) : AbstractFeatureApi() {

    final override val qualifier: Qualifier = api?.getScopeName() ?: this.getScopeName()

    override val isLibrary: Boolean = api?.isLibrary ?: false

    override val dependencies: List<AbstractFeatureApi> = emptyList()

    protected abstract fun ImplDSL.definitions()

    fun createModule(): Module {
        api?.dependencies = dependencies

        return Module(false).apply {
            scope(qualifier) { definitions() }
        }
    }
}

abstract class LibraryImpl(api: LibraryApi) : FeatureImpl(api) {

    override val isLibrary: Boolean = api.isLibrary
}