package com.example.playgroundapp.data.remote.api

import com.example.playgroundapp.data.remote.dto.ObjectApi
import retrofit2.Response
import retrofit2.http.GET

interface AuthorApiService {

    @GET("api/users")
    suspend fun getItems() : Response<ObjectApi>
}