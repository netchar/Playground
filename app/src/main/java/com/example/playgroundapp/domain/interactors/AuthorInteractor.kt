package com.example.playgroundapp.domain.interactors

import com.example.playgroundapp.domain.entity.Character

interface AuthorInteractor {
    suspend fun getItems(): List<Character>
}