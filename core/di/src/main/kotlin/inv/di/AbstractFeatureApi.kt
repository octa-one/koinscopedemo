package inv.di

import org.koin.core.qualifier.Qualifier

@Suppress("UnnecessaryAbstractClass")
abstract class AbstractFeatureApi {

    abstract val qualifier: Qualifier

    abstract val isLibrary: Boolean

    abstract val dependencies: List<AbstractFeatureApi>
}