package com.example.playgroundapp.domain.interactors

import com.example.playgroundapp.domain.CharacterRepository
import com.example.playgroundapp.domain.common.Result
import com.example.playgroundapp.domain.entity.Character

class AuthorInteractorImpl(
    private val repository: CharacterRepository
) : CharacterInteractor {

    override suspend fun getCharacters(): Result<List<Character>> {
        return repository.getCharacters()
    }
}