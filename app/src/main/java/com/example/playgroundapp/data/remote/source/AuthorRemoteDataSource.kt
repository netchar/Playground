package com.example.playgroundapp.data.remote.source

import com.example.playgroundapp.data.remote.BaseRemoteDataSource
import com.example.playgroundapp.data.remote.HttpResult
import com.example.playgroundapp.data.remote.api.AuthorApiService
import com.example.playgroundapp.data.remote.dto.ObjectApi

interface AuthorRemoteDataSource {
    suspend fun getAuthors(): HttpResult<ObjectApi>
}

class AuthorRemoteDataSourceImpl(
    private val api: AuthorApiService
) : BaseRemoteDataSource(), AuthorRemoteDataSource {
    override suspend fun getAuthors(): HttpResult<ObjectApi> {
        return call { api.getItems() }
    }
}