package com.example.playgroundapp.data.remote.source

import com.example.playgroundapp.data.remote.dto.CharacterResponseApi
import com.example.playgroundapp.domain.common.Result

interface CharacterRemoteDataSource {
    suspend fun getAuthors(): Result<CharacterResponseApi>
}