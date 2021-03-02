package com.example.playgroundapp.presentation.di

import com.example.playgroundapp.domain.interactors.CharacterInteractor
import com.example.playgroundapp.domain.interactors.CharactersInteractorImpl
import dagger.Binds
import dagger.Module

@Module
abstract class InteractorsModule {

    @Binds
    abstract fun providesCharactersInteractor(interactor: CharactersInteractorImpl): CharacterInteractor
}