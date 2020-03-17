package eu.vmpay.weatheracc.fundamentals

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import eu.vmpay.weatheracc.R
import kotlinx.android.synthetic.main.activity_secondary.*

class SecondaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)
        val cityName = intent.let {
            Log.d("SecondaryActivity", "Parcelable ${it.getParcelableExtra<OpenWeatherModels>(WEATHER_MODEL_KEY)}")
            it.getStringExtra(CITY_NAME_KEY)
        }
        Log.d("SecondaryActivity", "String extra $cityName")
        btnCompleteActivity.setOnClickListener {
            val intent = Intent().putExtra(CITY_NAME_KEY, cityName)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}
