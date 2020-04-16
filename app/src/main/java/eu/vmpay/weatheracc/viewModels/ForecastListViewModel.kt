package eu.vmpay.weatheracc.viewModels

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.vmpay.weatheracc.models.Units
import eu.vmpay.weatheracc.models.WeatherForecast
import eu.vmpay.weatheracc.repository.Repository
import eu.vmpay.weatheracc.repository.local.getUnits
import eu.vmpay.weatheracc.repository.local.setUnits
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class ForecastListViewModel @Inject constructor(
        private val repository: Repository,
        private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val tag = "ForecastListViewModel"

    val weatherList = MutableLiveData<List<WeatherForecast>>()
    val units = MutableLiveData<Units>()

    init {
        repository.getWeatherListFlow()
                .onStart {
                    Log.d(tag, "Flow starting")
                }
                .onCompletion {
                    Log.d(tag, "Flow complete")
                }
                .catch {
                    Log.d(tag, "Flow error $it")
                }
                .onEach {
                    Log.d(tag, "Flow success $it")
                    weatherList.value = it
                }
                .launchIn(viewModelScope)
        units.value = sharedPreferences.getUnits()
    }

    fun updateUnits() {
        val currentUnits = sharedPreferences.getUnits()
        val newUnits = if (currentUnits == Units.METRIC) Units.IMPERIAL
        else Units.METRIC
        viewModelScope.launch {
            repository.getWeatherList().map { it.id }.let {
                try {
                    repository.fetchWeatherByCityIdList(it, newUnits)
                    sharedPreferences.setUnits(newUnits)
                    units.postValue(newUnits)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}
