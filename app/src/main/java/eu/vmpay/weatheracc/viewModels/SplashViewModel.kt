package eu.vmpay.weatheracc.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.vmpay.weatheracc.repository.Repository
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(repository: Repository) : ViewModel() {

    val proceed = MutableLiveData<Boolean>()

    init {
        viewModelScope.launch {
            val animationDone = async {
                delay(2500)
                true
            }
            val fetchDone = async {
                try {
                    val list = repository.getWeatherList().map { it.id }
                    if (list.isNotEmpty())
                        repository.fetchWeatherByCityIdList(list)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                true
            }
            animationDone.await() && fetchDone.await()
            proceed.postValue(true)
        }
    }
}
