package com.example.playgroundapp.domain.interactors

import com.example.playgroundapp.domain.common.Result
import com.example.playgroundapp.domain.entity.Character

interface CharacterInteractor {
    suspend fun getCharacters(): Result<List<Character>>
}