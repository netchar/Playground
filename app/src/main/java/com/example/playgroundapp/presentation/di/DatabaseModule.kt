package com.example.playgroundapp.presentation.di

import android.app.Application
import androidx.room.Room
import com.example.playgroundapp.data.cache.AppDatabase
import com.example.playgroundapp.data.cache.dao.CharactersDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    companion object {
        const val DATABASE_NAME = "playground_database"
    }

    @Provides
    @Singleton
    fun provideDatabase(applicationContext: Application): AppDatabase {
        return Room.databaseBuilder(applicationContext, AppDatabase::class.java, DATABASE_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideCharactersDao(database: AppDatabase) : CharactersDao {
        return database.getCharactersDao()
    }
}