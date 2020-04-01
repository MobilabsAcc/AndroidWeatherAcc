package eu.vmpay.weatheracc.di

import android.content.Context
import eu.vmpay.weatheracc.repository.Repository
import eu.vmpay.weatheracc.repository.local.AppDatabase
import eu.vmpay.weatheracc.repository.local.WeatherForecastDao
import eu.vmpay.weatheracc.viewModels.ViewModelFactory

object Injector {
    private lateinit var appDatabase: AppDatabase
    private lateinit var repository: Repository
    private lateinit var factory: ViewModelFactory

    fun provideFactory(context: Context) =
            if (::factory.isInitialized) factory
            else ViewModelFactory(provideRepository(context)).also { factory = it }

    private fun provideRepository(context: Context) =
            if (::repository.isInitialized) repository
            else Repository(provideWeatherDao(context)).also { repository = it }

    private fun provideWeatherDao(context: Context): WeatherForecastDao =
            if (::appDatabase.isInitialized) appDatabase.weatherForecastDao()
            else AppDatabase.getInstance(context).let {
                appDatabase = it
                it.weatherForecastDao()
            }
}
