package com.example.playgroundapp.data.remote.source

import com.example.playgroundapp.data.remote.HttpResult
import com.example.playgroundapp.data.remote.dto.CharacterResponseApi

interface AuthorRemoteDataSource {
    suspend fun getAuthors(): HttpResult<CharacterResponseApi>
}