package eu.vmpay.weatheracc.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.vmpay.weatheracc.models.WeatherForecast
import eu.vmpay.weatheracc.repository.Repository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class ForecastListViewModel @Inject constructor(repository: Repository) : ViewModel() {

    private val tag = "ForecastListViewModel"

    val weatherList = MutableLiveData<List<WeatherForecast>>()

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
    }

}
