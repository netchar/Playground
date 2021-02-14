package com.example.playgroundapp.domain.interactors

import com.example.playgroundapp.data.Resource
import com.example.playgroundapp.domain.AuthorRepository
import com.example.playgroundapp.domain.entity.Character

class AuthorInteractorImpl(
    private val repository: AuthorRepository
) : AuthorInteractor {

    override suspend fun getItems(): List<Character> {
        return when (val response = repository.getItems()) {
            is Resource.Error -> listOf()
            is Resource.Success -> response.data
        }
    }
}