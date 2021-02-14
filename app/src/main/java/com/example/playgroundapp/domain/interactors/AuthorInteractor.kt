package com.example.playgroundapp.domain.interactors

import com.example.playgroundapp.domain.entity.Author

interface AuthorInteractor {
    suspend fun getItems(): List<Author>
}