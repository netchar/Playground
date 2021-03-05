package com.example.playgroundapp.data.remote.source

import com.example.playgroundapp.data.remote.dto.ApiCharacterResponse
import com.example.playgroundapp.domain.common.Result

interface CharacterRemoteDataSource {
    suspend fun getCharacters(): Result<ApiCharacterResponse>
}