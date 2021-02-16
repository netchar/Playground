package com.example.playgroundapp.domain

import com.example.playgroundapp.domain.common.Result
import com.example.playgroundapp.domain.entity.Character

interface CharacterRepository {
    suspend fun getCharacters(): Result<List<Character>>
}