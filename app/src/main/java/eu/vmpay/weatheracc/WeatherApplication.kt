package eu.vmpay.weatheracc

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import eu.vmpay.weatheracc.di.components.DaggerAppComponent

class WeatherApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().create(this)
}