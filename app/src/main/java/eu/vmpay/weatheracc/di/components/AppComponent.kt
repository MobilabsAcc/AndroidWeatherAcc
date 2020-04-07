package eu.vmpay.weatheracc.di.components

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import eu.vmpay.weatheracc.WeatherApplication
import eu.vmpay.weatheracc.di.modules.ActivityModule
import eu.vmpay.weatheracc.di.modules.ContextModule
import eu.vmpay.weatheracc.di.modules.DatabaseModule
import eu.vmpay.weatheracc.di.modules.FragmentsModule
import eu.vmpay.weatheracc.di.modules.RemoteModule
import eu.vmpay.weatheracc.di.modules.RepositoryModule
import eu.vmpay.weatheracc.di.modules.ViewModelFactoryModule
import eu.vmpay.weatheracc.di.modules.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            RepositoryModule::class,
            FragmentsModule::class,
            ViewModelModule::class,
            DatabaseModule::class,
            ActivityModule::class,
            ContextModule::class,
            ViewModelFactoryModule::class,
            RemoteModule::class
        ]
)

interface AppComponent : AndroidInjector<WeatherApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<WeatherApplication>()
}