package eu.vmpay.weatheracc.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.vmpay.weatheracc.models.WeatherForecast
import eu.vmpay.weatheracc.repository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val cityList = MutableLiveData<List<WeatherForecast>>()
    val errorMessage = MutableLiveData<String>()

    fun searchCity(cityName: String) {
        viewModelScope.launch {
            try {
                val result = repository.findCityByName(cityName)
                cityList.postValue(result.list)
            } catch (e: Exception) {
                e.printStackTrace()
                errorMessage.postValue(e.toString())
            }
        }
    }

    fun storeCity(weatherForecast: WeatherForecast) {
        viewModelScope.launch {
            repository.storeCity(weatherForecast)
        }
    }
}
