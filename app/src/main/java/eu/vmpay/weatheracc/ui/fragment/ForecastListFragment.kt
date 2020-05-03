package eu.vmpay.weatheracc.ui.fragment

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dagger.android.support.DaggerFragment
import eu.vmpay.weatheracc.R
import eu.vmpay.weatheracc.adapters.CitiesAdapter
import eu.vmpay.weatheracc.models.Units
import eu.vmpay.weatheracc.viewModels.ForecastListViewModel
import kotlinx.android.synthetic.main.forecast_list_fragment.view.*
import javax.inject.Inject

class ForecastListFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by viewModels<ForecastListViewModel> { factory }

    private val citiesAdapter by lazy {
        CitiesAdapter {
            Toast.makeText(context, it.name, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.forecast_list_fragment, container, false).apply {
            unitsOverlay.setOnClickListener {
                viewModel.updateUnits()
            }
            rvCity.adapter = citiesAdapter
            floatingActionButton.setOnClickListener {
                findNavController().navigate(
                        ForecastListFragmentDirections.actionForecastListFragmentToSearchFragment()
                )
            }

            with(viewModel) {
                weatherList.observe(viewLifecycleOwner, Observer {
                    citiesAdapter.submitList(it)
                })
                units.observe(viewLifecycleOwner, Observer {
                    tvC.setTypeface(null, if (it == Units.IMPERIAL) Typeface.NORMAL else Typeface.BOLD)
                    tvF.setTypeface(null, if (it == Units.METRIC) Typeface.NORMAL else Typeface.BOLD)
                })
            }
        }
    }
}
