package aam.koinscopetest.market.di

import aam.koinscopetest.ViewModel2
import aam.koinscopetest.market.MarketInteractor
import aam.koinscopetest.market.MarketInteractorImpl
import aam.koinscopetest.market.MarketSmthProvider
import aam.koinscopetest.portfolio.di.PortfolioFeatureApi
import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinComponent
import org.koin.core.context.GlobalContext
import org.koin.core.qualifier.TypeQualifier
import org.koin.dsl.ScopeDSL
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.dsl.scoped

abstract class FeatureScope : KoinComponent {

    init { loadScope() }

    open val dependencies: List<FeatureScope> = emptyList()
    protected abstract fun ScopeDSL.definitions()

    @VisibleForTesting(otherwise = PRIVATE)
    fun loadScope() {
        val qualifier = TypeQualifier(this::class)
        getKoin().loadModules(listOf(module { scope(qualifier) { definitions() } }))
    }
}

abstract class CoreFeatureScope : FeatureScope()

object MarketFeatureApi : FeatureScope() {

    override val dependencies = listOf(PortfolioFeatureApi)

    override fun ScopeDSL.definitions() {
        scoped<MarketInteractorImpl>() bind MarketInteractor::class
    }
}

internal object MarketFeatureInternalApi : FeatureScope() {

    override val dependencies = listOf(MarketFeatureApi, PortfolioFeatureApi)

    override fun ScopeDSL.definitions() {
        scoped<MarketSmthProvider>()
        viewModel<ViewModel2>()
    }
}


/*
private fun <F : Feature> F.loadScope2(scopeSet: ScopeDSL.() -> Unit) {
    val qualifier = TypeQualifier(this::class)
    KoinPlatformTools.defaultContext().get().loadModules(listOf(module { scope(qualifier, scopeSet) }))
}

private inline fun <reified F : Feature> Koin.loadScope(noinline scopeSet: ScopeDSL.() -> Unit) {
    val qualifier = TypeQualifier(F::class)
    loadModules(listOf(module { scope(qualifier, scopeSet) }))
}*/
