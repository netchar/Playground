package com.example.playgroundapp.presentation.di

import com.example.playgroundapp.data.DataMapper
import com.example.playgroundapp.data.cache.source.CharacterLocalDataSource
import com.example.playgroundapp.data.remote.source.CharacterRemoteDataSource
import com.example.playgroundapp.data.repository.CharacterRepositoryImpl
import com.example.playgroundapp.domain.CharacterRepository
import dagger.Module
import dagger.Provides

@Module(includes = [LocalDataSourceModule::class, RemoteDataSourceModule::class])
class RepositoryModule {

    @Provides
    fun provideCharacterRepo(remote: CharacterRemoteDataSource, local: CharacterLocalDataSource, mapper: DataMapper) : CharacterRepository {
        return CharacterRepositoryImpl(remote, local, mapper)
    }

    @Provides
    fun providesMapper() : DataMapper {
        return DataMapper()
    }
}