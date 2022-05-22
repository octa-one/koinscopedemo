package com.orcchg.direcall.ui_core_lib

import androidx.annotation.LayoutRes
import inv.di.android.DependenciesComponent
import inv.di.android.fragmentScope
import org.koin.android.scope.AndroidScopeComponent
import org.koin.core.scope.Scope

abstract class BaseScopeFragment(
    @LayoutRes contentLayoutId: Int = 0
) : BaseFragment(contentLayoutId), AndroidScopeComponent, DependenciesComponent {

    override val scope: Scope by fragmentScope()
}
