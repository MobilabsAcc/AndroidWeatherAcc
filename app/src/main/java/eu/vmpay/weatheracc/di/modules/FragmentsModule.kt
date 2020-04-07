package eu.vmpay.weatheracc.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import eu.vmpay.weatheracc.ui.fragment.ForecastListFragment
import eu.vmpay.weatheracc.ui.fragment.SearchFragment
import eu.vmpay.weatheracc.ui.fragment.SplashFragment

@Module
internal abstract class FragmentsModule {

    @ContributesAndroidInjector
    internal abstract fun bindForecastListFragment(): ForecastListFragment

    @ContributesAndroidInjector
    internal abstract fun bindSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    internal abstract fun bindSearchFragment(): SearchFragment
}