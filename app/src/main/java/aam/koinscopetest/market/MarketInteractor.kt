package aam.koinscopetest.market

import aam.koinscopetest.portfolio.PortfolioInteractor

interface MarketInteractor {

    fun doSmth(): String
}

internal class MarketInteractorImpl(
    private val portfolioInteractor: PortfolioInteractor
) : MarketInteractor {

    override fun doSmth() = "MARKET" + portfolioInteractor.doSmth()
}

internal class MarketSmthProvider {

    fun provide() = "ok"
}