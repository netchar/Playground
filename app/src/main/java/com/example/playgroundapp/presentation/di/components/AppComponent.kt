package com.example.playgroundapp.presentation.di.components

import android.app.Application
import com.example.playgroundapp.App
import com.example.playgroundapp.presentation.di.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            RepositoryModule::class,
            NetworkModule::class,
            DatabaseModule::class,
            InteractorsModule::class,
            ViewModelBindingModule::class,
            AndroidInjectionModule::class,
            FragmentsModule::class
        ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): AppComponent.Builder
        fun build(): AppComponent
    }
}