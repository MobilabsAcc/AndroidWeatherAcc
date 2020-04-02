package eu.vmpay.weatheracc.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import eu.vmpay.weatheracc.R
import eu.vmpay.weatheracc.models.CityModel
import kotlinx.android.synthetic.main.item_saved_city.view.*

class CitiesAdapter(
    private val listener: (CityModel) -> Unit
) : ListAdapter<CityModel, CitiesAdapter.CitiesViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CitiesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_saved_city, parent, false)
        )

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) =
        holder.bind(getItem(position), listener)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CityModel>() {
            override fun areItemsTheSame(oldItem: CityModel, newItem: CityModel) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: CityModel, newItem: CityModel) =
                oldItem == newItem
        }
    }

    class CitiesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(city: CityModel, listener: (CityModel) -> Unit) {
            itemView.apply {
                when (city.status) {
                    "Sunny" -> {
                        itemContainer.setBackgroundResource(R.drawable.hot_background)
                    }
                    "Clouds" -> {
                        itemContainer.setBackgroundResource(R.drawable.cloud_background)
                    }
                    "Clear Sky" -> {
                        itemContainer.setBackgroundResource(R.drawable.clear_sky_background)
                    }
                }
                tvCityName.text = city.name
                tvDate.text = city.date
                tvTemperature.text = "${city.temperature} °C"
                setOnClickListener { listener(city) }
            }
        }
    }
}