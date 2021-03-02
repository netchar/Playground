package com.example.playgroundapp.presentation.di

import com.example.playgroundapp.data.remote.api.CharacterApiService
import com.example.playgroundapp.data.remote.source.CharacterRemoteDataSource
import com.example.playgroundapp.data.remote.source.CharacterRemoteDataSourceImpl
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
class RemoteDataSourceModule {

    @Provides
    fun provideCharacterRemoteDataSource(api: CharacterApiService): CharacterRemoteDataSource {
        return CharacterRemoteDataSourceImpl(api)
    }
}