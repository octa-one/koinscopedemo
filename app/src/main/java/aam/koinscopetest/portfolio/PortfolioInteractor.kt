package aam.koinscopetest.portfolio

import aam.koinscopetest.AppInteractor

interface PortfolioInteractor {

    fun doSmth(): String
}

internal class PortfolioInteractorImpl(
    private val appInteractor: AppInteractor
) : PortfolioInteractor {

    override fun doSmth() = "PORTFOLIO ${appInteractor.getValue()}"
}