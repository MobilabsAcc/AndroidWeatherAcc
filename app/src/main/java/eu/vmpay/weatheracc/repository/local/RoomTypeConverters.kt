package eu.vmpay.weatheracc.repository.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import eu.vmpay.weatheracc.models.Weather

class RoomTypeConverters {

    @TypeConverter
    fun weatherToJson(value: List<Weather>?): String? = value?.let { Gson().toJson(value) }

    @TypeConverter
    fun jsonToWeather(value: String?): List<Weather>? =
            value?.let { Gson().fromJson(value, Array<Weather>::class.java) as Array<Weather> }?.toList()
}
