package eu.vmpay.weatheracc.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import eu.vmpay.weatheracc.R
import eu.vmpay.weatheracc.models.WeatherForecast
import eu.vmpay.weatheracc.models.WeatherIcon
import kotlinx.android.synthetic.main.item_saved_city.view.*

class CitiesAdapter(
        private val listener: (WeatherForecast) -> Unit
) : ListAdapter<WeatherForecast, CitiesAdapter.CitiesViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            CitiesViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_saved_city, parent, false)
            )

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) =
            holder.bind(getItem(position), listener)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WeatherForecast>() {
            override fun areItemsTheSame(oldItem: WeatherForecast, newItem: WeatherForecast) =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: WeatherForecast, newItem: WeatherForecast) =
                    oldItem == newItem
        }
    }

    class CitiesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(city: WeatherForecast, listener: (WeatherForecast) -> Unit) {
            itemView.apply {
                tvCityName.text = city.name
                tvDate.text = city.weather.firstOrNull()?.description
                tvTemperature.text = "${city.main.temp}°"
                setOnClickListener { listener(city) }
                when (city.weather.firstOrNull()?.icon) {
                    WeatherIcon.CLEAR_SKY, WeatherIcon.CLEAR_SKY_NIGHT -> Pair(R.drawable.ic_yellow_sun, R.drawable.clear_sky_background)
                    WeatherIcon.FEW_CLOUDS, WeatherIcon.FEW_CLOUDS_NIGHT -> Pair(R.drawable.ic_part_cloudy, R.drawable.cloud_background)
                    WeatherIcon.SCATTERED_CLOUDS, WeatherIcon.SCATTERED_CLOUDS_NIGHT,
                    WeatherIcon.BROKEN_CLOUDS, WeatherIcon.BROKEN_CLOUDS_NIGHT -> Pair(R.drawable.ic_cloudy, R.drawable.cloud_background)
                    WeatherIcon.SHOWER_RAIN, WeatherIcon.SHOWER_RAIN_NIGHT,
                    WeatherIcon.RAIN, WeatherIcon.RAIN_NIGHT -> Pair(R.drawable.ic_rainy, R.drawable.mist_background)
                    WeatherIcon.THUNDERSTORM, WeatherIcon.THUNDERSTORM_NIGHT -> Pair(R.drawable.ic_thunder, R.drawable.mist_background)
                    WeatherIcon.SNOW, WeatherIcon.SNOW_NIGHT -> Pair(R.drawable.ic_snowy, R.drawable.mist_background)
                    WeatherIcon.MIST, WeatherIcon.MIST_NIGHT -> Pair(R.drawable.ic_foggy, R.drawable.mist_background)
                    else -> null
                }?.let {
                    ivIcon.setImageResource(it.first)
                    itemContainer.setBackgroundResource(it.second)
                }
            }
        }
    }
}