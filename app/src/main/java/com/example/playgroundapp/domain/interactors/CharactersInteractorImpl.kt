package com.example.playgroundapp.domain.interactors

import com.example.playgroundapp.data.Resource
import com.example.playgroundapp.domain.CharacterRepository
import com.example.playgroundapp.domain.common.Result
import com.example.playgroundapp.domain.entity.Character
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersInteractorImpl @Inject constructor(
    private val repository: CharacterRepository
) : CharacterInteractor {

    override suspend fun getCharactersNow(): Result<List<Character>> {
        return repository.getCharactersNow()
    }

    override fun getCharacters(): Flow<Resource<List<Character>>> {
        return repository.getCharacters()
    }
}