package eu.vmpay.weatheracc.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import eu.vmpay.weatheracc.R
import eu.vmpay.weatheracc.di.Injector
import eu.vmpay.weatheracc.viewModels.SplashViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {
    private val factory by lazy { Injector.provideFactory(context!!) }
    private val viewModel by viewModels<SplashViewModel> { factory }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lifecycleScope.launch {
            delay(2500)
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToForecastListFragment())
        }
    }
}
