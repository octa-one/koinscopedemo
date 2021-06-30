package aam.koinscopetest

import aam.koinscopetest.base.BaseScopeFragment
import aam.koinscopetest.portfolio.PortfolioInteractor
import aam.koinscopetest.portfolio.di.PortfolioFeatureApi
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import org.koin.android.ext.android.inject

class Fragment1 : BaseScopeFragment() {

    private val portfolioInteractor: PortfolioInteractor by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fmt_1, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.text_info).text = "${portfolioInteractor.doSmth()} \n $portfolioInteractor"
        view.findViewById<Button>(R.id.button_next).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, Fragment2())
                .addToBackStack(null)
                .commit()
        }
        view.findViewById<Button>(R.id.button_back).setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    override val dependencies = listOf(PortfolioFeatureApi)
}