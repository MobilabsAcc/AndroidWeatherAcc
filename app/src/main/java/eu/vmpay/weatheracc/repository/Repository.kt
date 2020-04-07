package eu.vmpay.weatheracc.repository

import eu.vmpay.weatheracc.repository.local.WeatherForecastDao
import eu.vmpay.weatheracc.repository.remote.OpenWeatherService

class Repository(
        private val openWeatherService: OpenWeatherService,
        private val weatherForecastDao: WeatherForecastDao
) {

    fun getWeatherListFlow() = weatherForecastDao.getAllFlow()

    suspend fun getWeatherList() = weatherForecastDao.getAll()

    suspend fun fetchWeatherByCityIdList(cityIdList: List<Long>) =
            openWeatherService.getWeatherByCityIdList(
                    cityIdList.fold("", { acc: String, cityId: Long ->
                        "$acc$cityId,"
                    })
            ).also { weatherForecastDao.insert(it.list) }
}
