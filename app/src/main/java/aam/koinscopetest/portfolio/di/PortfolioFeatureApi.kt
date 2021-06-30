package aam.koinscopetest.portfolio.di

import aam.koinscopetest.GlobalFeatureApi
import aam.koinscopetest.market.di.FeatureScope
import aam.koinscopetest.portfolio.PortfolioInteractor
import aam.koinscopetest.portfolio.PortfolioInteractorImpl
import org.koin.dsl.ScopeDSL
import org.koin.dsl.bind
import org.koin.dsl.scoped

object PortfolioFeatureApi : FeatureScope() {

    override val dependencies = listOf(GlobalFeatureApi)

    override fun ScopeDSL.definitions() {
        scoped<PortfolioInteractorImpl>() bind PortfolioInteractor::class
    }
}

object PortfolioFeatureInternalApi : FeatureScope() {

    override val dependencies = listOf(PortfolioFeatureApi)

    override fun ScopeDSL.definitions() = Unit
}