package aam.koinscopetest

import aam.koinscopetest.base.BaseScopeFragment
import aam.koinscopetest.market.MarketInteractor
import aam.koinscopetest.market.di.MarketFeatureInternalApi
import aam.koinscopetest.portfolio.PortfolioInteractor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModel
import org.koin.android.scope.ScopeService
import org.koin.androidx.viewmodel.ext.android.viewModel

class Fragment2 : BaseScopeFragment() {

    private val viewModel: ViewModel2 by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fmt_2, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.text_info).text = viewModel.getText()
        view.findViewById<Button>(R.id.button_back).setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    override val dependencies = listOf(MarketFeatureInternalApi)
}

internal class ViewModel2(
    private val portfolioInteractor: PortfolioInteractor,
    private val marketInteractor: MarketInteractor
) : ViewModel() {

    fun getText() = "${portfolioInteractor.doSmth()} ${marketInteractor.doSmth()} \n $portfolioInteractor $marketInteractor"
}