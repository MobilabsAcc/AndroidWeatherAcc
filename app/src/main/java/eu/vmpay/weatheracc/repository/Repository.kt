package eu.vmpay.weatheracc.repository

import eu.vmpay.weatheracc.repository.local.WeatherForecastDao

class Repository(private val weatherForecastDao: WeatherForecastDao) {

    fun getWeatherList() = weatherForecastDao.getAll()
}
