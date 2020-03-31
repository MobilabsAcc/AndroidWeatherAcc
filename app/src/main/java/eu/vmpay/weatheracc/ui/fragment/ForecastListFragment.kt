package eu.vmpay.weatheracc.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import eu.vmpay.weatheracc.R
import eu.vmpay.weatheracc.di.Injector
import eu.vmpay.weatheracc.viewModels.ForecastListViewModel
import kotlinx.android.synthetic.main.forecast_list_fragment.view.*

class ForecastListFragment : Fragment() {
    private val factory by lazy { Injector.provideFactory(context!!) }
    private val viewModel by viewModels<ForecastListViewModel> { factory }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.forecast_list_fragment, container, false).apply {
            viewModel.weatherList.observe(viewLifecycleOwner, Observer {
                textView.text = it.toString()
            })
        }
    }
}
