package com.example.playgroundapp.domain

import com.example.playgroundapp.data.Resource
import com.example.playgroundapp.domain.common.Result
import com.example.playgroundapp.domain.entity.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharactersNow(): Result<List<Character>>
    fun getCharacters() : Flow<Resource<List<Character>>>
}