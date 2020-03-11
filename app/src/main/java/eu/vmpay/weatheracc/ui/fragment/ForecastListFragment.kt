package eu.vmpay.weatheracc.ui.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eu.vmpay.weatheracc.R
import eu.vmpay.weatheracc.viewModels.ForecastListViewModel

class ForecastListFragment : Fragment() {

    companion object {
        fun newInstance() = ForecastListFragment()
    }

    private lateinit var viewModel: ForecastListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.forecast_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ForecastListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}