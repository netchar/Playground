package com.example.playgroundapp.data.remote.api

import com.example.playgroundapp.data.remote.dto.ApiCharacterResponse
import retrofit2.Response
import retrofit2.http.GET

interface CharacterApiService {

    @GET("character")
    suspend fun getCharacters() : Response<ApiCharacterResponse>
}