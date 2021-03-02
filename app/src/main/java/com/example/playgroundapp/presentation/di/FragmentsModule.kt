package com.example.playgroundapp.presentation.di

import com.example.playgroundapp.presentation.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun bindsHomeFragment() : HomeFragment
}