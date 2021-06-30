package aam.koinscopetest

import aam.koinscopetest.market.di.CoreFeatureScope
import org.koin.dsl.ScopeDSL
import org.koin.dsl.scoped

class AppInteractor {

    fun getValue() = "with app"
}

object GlobalFeatureApi : CoreFeatureScope() {

    override fun ScopeDSL.definitions() {
        scoped<AppInteractor>()
    }
}