package com.example.playgroundapp.presentation.di

import com.example.playgroundapp.data.cache.dao.CharactersDao
import com.example.playgroundapp.data.cache.source.CharacterLocalDataSource
import com.example.playgroundapp.data.cache.source.CharacterLocalDataSourceImpl
import dagger.Module
import dagger.Provides

@Module(includes = [DatabaseModule::class])
class LocalDataSourceModule {

    @Provides
    fun provideCharacterLocalDataSource(dao: CharactersDao): CharacterLocalDataSource {
        return CharacterLocalDataSourceImpl(dao)
    }
}