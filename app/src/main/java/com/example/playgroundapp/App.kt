package com.example.playgroundapp

import android.app.Application
import com.example.playgroundapp.data.remote.api.AuthorApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    lateinit var authorApiService: AuthorApiService

    override fun onCreate() {
        super.onCreate()

        val provider = ComponentFactory()
        val retrofit = provider.provideRetrofit()
        authorApiService = retrofit.create(AuthorApiService::class.java)
    }
}

class ComponentFactory {

    fun provideRetrofit(): Retrofit {
        val interceptor = provideLoggingInterceptor()
        val httpClient = provideHttpClient(interceptor)

        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl("https://reqres.in/")
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
}