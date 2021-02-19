package com.example.playgroundapp

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.playgroundapp.data.cache.AppDatabase
import com.example.playgroundapp.data.remote.api.CharacterApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {
    lateinit var authorApiService: CharacterApiService
    lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()

        val provider = ComponentFactory()
        val retrofit = provider.provideRetrofit()
        authorApiService = retrofit.create(CharacterApiService::class.java)
        database = provider.provideDatabase(this)
    }
}

class ComponentFactory {
    companion object {
        private const val BASE_URL = "https://rickandmortyapi.com/api/"
    }

    fun provideRetrofit(): Retrofit {
        val interceptor = provideLoggingInterceptor()
        val httpClient = provideHttpClient(interceptor)
        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    private fun provideHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    fun provideDatabase(applicationContext: Application): AppDatabase {
        return Room.databaseBuilder(applicationContext, AppDatabase::class.java, "playground_database")
            .build()
    }
}