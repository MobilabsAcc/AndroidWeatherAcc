package eu.vmpay.weatheracc.fundamentals

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class JobDoneReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        val cityName = intent.getStringExtra(CITY_NAME_KEY)
        Log.d("JobDoneReceiver", "onReceive data $cityName")
    }
}