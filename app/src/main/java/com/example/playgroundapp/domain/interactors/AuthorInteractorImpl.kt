package com.example.playgroundapp.domain.interactors

import com.example.playgroundapp.data.Resource
import com.example.playgroundapp.domain.AuthorRepository
import com.example.playgroundapp.domain.entity.Author

class AuthorInteractorImpl(
    private val repository: AuthorRepository
) : AuthorInteractor {

    override suspend fun getItems(): List<Author> {
        return when (val response = repository.getItems()) {
            is Resource.Error -> listOf()
            is Resource.Success -> response.data
        }
    }
}