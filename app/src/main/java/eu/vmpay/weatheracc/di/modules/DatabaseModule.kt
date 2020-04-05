package eu.vmpay.weatheracc.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import eu.vmpay.weatheracc.repository.local.AppDatabase
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context) = AppDatabase.getInstance(context)
}