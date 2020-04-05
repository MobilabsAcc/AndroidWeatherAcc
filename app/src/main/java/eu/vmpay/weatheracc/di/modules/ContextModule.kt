package eu.vmpay.weatheracc.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import eu.vmpay.weatheracc.WeatherApplication


@Module
class ContextModule {

    @Provides
    fun providesContext(application: WeatherApplication): Context = application.applicationContext
}