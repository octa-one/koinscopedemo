package inv.di

import androidx.annotation.RestrictTo
import inv.di.dsl.ApiDSL
import inv.di.dsl.ApiDefinition
import org.koin.core.component.getScopeName
import org.koin.core.qualifier.Qualifier

/**
 * Базовый класс для объявления api фичи в api модуле, а именно
 * интерфейсов definitions, реализацию которых можно будет получить из этой фичи
 */
abstract class FeatureApi : AbstractFeatureApi() {

    final override val qualifier: Qualifier = getScopeName()

    override val isLibrary: Boolean = false

    final override var dependencies: List<AbstractFeatureApi> = emptyList()

    protected abstract fun ApiDSL.definitions()

    @RestrictTo(RestrictTo.Scope.TESTS)
    fun getApiDefinitions(): List<ApiDefinition> =
        ApiDSL().apply { definitions() }.definitions
}

abstract class LibraryApi : FeatureApi() {

    override val isLibrary: Boolean = true
}
