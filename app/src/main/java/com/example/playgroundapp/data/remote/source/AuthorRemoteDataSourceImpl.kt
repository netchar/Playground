package com.example.playgroundapp.data.remote.source

import com.example.playgroundapp.data.remote.BaseRemoteDataSource
import com.example.playgroundapp.data.remote.HttpResult
import com.example.playgroundapp.data.remote.api.CharacterApiService
import com.example.playgroundapp.data.remote.dto.CharacterResponseApi

class AuthorRemoteDataSourceImpl(
    private val api: CharacterApiService
) : BaseRemoteDataSource(), AuthorRemoteDataSource {

    override suspend fun getAuthors(): HttpResult<CharacterResponseApi> {
        return call { api.getCharacters() }
    }
}