package com.example.playgroundapp.data.remote.source

import com.example.playgroundapp.data.remote.BaseRemoteDataSource
import com.example.playgroundapp.data.remote.api.CharacterApiService
import com.example.playgroundapp.data.remote.dto.CharacterResponseApi
import com.example.playgroundapp.domain.common.Result

class CharacterRemoteDataSourceImpl(
    private val api: CharacterApiService
) : BaseRemoteDataSource(), CharacterRemoteDataSource {

    override suspend fun getCharacters(): Result<CharacterResponseApi> {
        return call { api.getCharacters() }
    }
}