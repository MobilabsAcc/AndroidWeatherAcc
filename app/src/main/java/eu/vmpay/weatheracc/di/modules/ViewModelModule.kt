package eu.vmpay.weatheracc.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import eu.vmpay.weatheracc.viewModels.ForecastListViewModel
import eu.vmpay.weatheracc.viewModels.SearchViewModel
import eu.vmpay.weatheracc.viewModels.SplashViewModel

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(key = ForecastListViewModel::class)
    abstract fun bindForecastListViewModel(viewModel: ForecastListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(key = SplashViewModel::class)
    abstract fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(key = SearchViewModel::class)
    abstract fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel
}