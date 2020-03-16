package eu.vmpay.weatheracc.fundamentals

import android.app.IntentService
import android.content.Intent
import android.os.Looper
import android.util.Log

const val ACTION_BULK_DOWNLOAD = "eu.vmpay.weatheracc.fundamentals.action.bulk.download"
const val EXTRA_PARAM_CITY = "eu.vmpay.weatheracc.fundamentals.extra.PARAM1"

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 */
class BulkDownloadService : IntentService("BulkDownloadService") {

    override fun onHandleIntent(intent: Intent?) {
        when (intent?.action) {
            ACTION_BULK_DOWNLOAD -> {
                intent.getStringExtra(EXTRA_PARAM_CITY)?.let {
                    fetchWeather(it)
                }
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private fun fetchWeather(city: String) {
        Log.d("BulkDownloadService", "Start fetchWeather for $city on ${Looper.myLooper()}")
        Thread.sleep(3000)
        Log.d("BulkDownloadService", "End fetchWeather for $city on ${Looper.myLooper()}")
    }
}