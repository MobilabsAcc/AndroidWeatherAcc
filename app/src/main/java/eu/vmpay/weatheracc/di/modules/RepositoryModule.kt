package eu.vmpay.weatheracc.di.modules

import dagger.Module
import dagger.Provides
import eu.vmpay.weatheracc.repository.Repository
import eu.vmpay.weatheracc.repository.local.AppDatabase
import eu.vmpay.weatheracc.repository.remote.OpenWeatherService
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(
            openWeatherService: OpenWeatherService,
            database: AppDatabase
    ) = Repository(openWeatherService, database.weatherForecastDao())
}