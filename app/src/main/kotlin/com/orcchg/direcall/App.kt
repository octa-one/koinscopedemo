package com.orcchg.direcall

import android.app.Application
import com.orcchg.direcall.github_repo.di.GithubCoreFeatureImpl
import com.orcchg.direcall.github_user_details.di.GithubUserDetailsFeatureImpl
import com.orcchg.direcall.github_user_list.di.GithubUserListFeatureImpl
import com.orcchg.direcall.network.di.NetworkLibraryImpl
import com.orcchg.direcall.scheduler.di.SchedulersLibraryImpl
import inv.di.FeatureApiManager
import inv.di.FeatureImpl
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(
                module {
                    single(FeatureApiManager.FEATURE_MAP_QUALIFIER) { features().associateBy { it.qualifier } }
                }
            )
        }
    }
}

fun features(): List<FeatureImpl> =
    listOf(
        SchedulersLibraryImpl,
        NetworkLibraryImpl,
        GithubCoreFeatureImpl,
        GithubUserListFeatureImpl,
        GithubUserDetailsFeatureImpl
    )
