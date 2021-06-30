package aam.koinscopetest

import aam.koinscopetest.base.getFeatureScopeManager
import aam.koinscopetest.market.MarketSmthProvider
import aam.koinscopetest.market.di.CoreFeatureScope
import aam.koinscopetest.market.di.FeatureScope
import aam.koinscopetest.market.di.MarketFeatureInternalApi
import io.mockk.mockk
import io.mockk.mockkClass
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.Koin
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.module
import org.koin.dsl.scoped
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProvider
import org.reflections.Reflections

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Before
    fun setUp() {
        MockProvider.register { kClass -> mockkClass(kClass) }
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_koin() {
        val reflections = Reflections("aam.koinscopetest")
        val features = reflections.getSubTypesOf(FeatureScope::class.java)
        features
            .filterNot { it == CoreFeatureScope::class.java }
            .forEach { featureClass ->
                testFeature { requireNotNull(featureClass.kotlin.objectInstance) { "$featureClass is not an object" } }
            }
    }

    val testModule = module {
        scope<MarketFeatureInternalApi> {
            scoped<MarketSmthProvider>()
        }
    }

    private fun testFeature(featureInitializer: () -> FeatureScope) {
        val koin = startKoin {
            androidContext(mockk(relaxed = true))
            allowOverride(false)
        }.koin
        val feature = featureInitializer()

        feature.loadScope()
        val set = mutableSetOf<FeatureScope>()
        traverseDependencies(set, feature)
        set.forEach { it.loadScope()}

        val scopeManager = koin.getFeatureScopeManager(feature)
        val testScope = koin.createTestScope()
        scopeManager.linkFrom(testScope)

        koin.loadModules(listOf(testModule), true)

        assert(scopeManager.isScopeActive())
        koin.checkModules()
        if (feature !is CoreFeatureScope) {
            assert(!scopeManager.isScopeActive())
        }
        stopKoin()
    }

    @OptIn(KoinInternalApi::class)
    private fun Koin.createTestScope(): Scope {
        val qualifier = named("test")
        scopeRegistry.loadScopes(listOf(module { scope(qualifier) {} }))
        return createScope("test", qualifier)
    }
}

private fun traverseDependencies(acc: MutableSet<FeatureScope>, feature: FeatureScope) {
    feature.dependencies.forEach {
        acc.add(it)
        traverseDependencies(acc, it)
    }
}