package eu.vmpay.weatheracc.di.components

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import eu.vmpay.weatheracc.WeatherApplication
import eu.vmpay.weatheracc.di.modules.*
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
        ViewModelFactoryModule::class
    ]
)

interface AppComponent : AndroidInjector<WeatherApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<WeatherApplication>()
}