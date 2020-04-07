package eu.vmpay.weatheracc.di.modules

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import eu.vmpay.weatheracc.repository.local.AppDatabase
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context) = AppDatabase.getInstance(context)

    @Provides
    @Singleton
    fun provideSharedPrefs(context: Context): SharedPreferences =
            context.getSharedPreferences("weather_app", Context.MODE_PRIVATE)
}