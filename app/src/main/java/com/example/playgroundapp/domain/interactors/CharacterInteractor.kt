package com.example.playgroundapp.domain.interactors

import com.example.playgroundapp.data.Resource
import com.example.playgroundapp.domain.common.Result
import com.example.playgroundapp.domain.entity.Character
import kotlinx.coroutines.flow.Flow

interface CharacterInteractor {
    fun getCharacters(): Flow<Resource<List<Character>>>
    suspend fun getCharactersNow(): Result<List<Character>>
}