package eu.vmpay.weatheracc.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import eu.vmpay.weatheracc.R
import eu.vmpay.weatheracc.fundamentals.ACTION_BULK_DOWNLOAD
import eu.vmpay.weatheracc.fundamentals.BulkDownloadService
import eu.vmpay.weatheracc.fundamentals.EXTRA_PARAM_CITY
import eu.vmpay.weatheracc.viewModels.ForecastListViewModel
import kotlinx.android.synthetic.main.forecast_list_fragment.view.*

class ForecastListFragment : Fragment() {

    private lateinit var viewModel: ForecastListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.forecast_list_fragment, container, false)
                .apply {
                    btnStartService.setOnClickListener {
                        val serviceIntent = Intent(context, BulkDownloadService::class.java).apply {
                            action = ACTION_BULK_DOWNLOAD
                            putExtra(EXTRA_PARAM_CITY, "Warsaw")
                        }
                        context.startService(serviceIntent)
                    }
                }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ForecastListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}