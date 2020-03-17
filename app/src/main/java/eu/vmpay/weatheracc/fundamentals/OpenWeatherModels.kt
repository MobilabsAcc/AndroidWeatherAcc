package eu.vmpay.weatheracc.fundamentals

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OpenWeatherModels(
        val id: String,
        val name: String
) : Parcelable