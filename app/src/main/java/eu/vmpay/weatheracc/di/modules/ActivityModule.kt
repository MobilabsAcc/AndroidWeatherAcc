package eu.vmpay.weatheracc.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import eu.vmpay.weatheracc.MainActivity

@Module
internal abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun mainActivity(): MainActivity
}