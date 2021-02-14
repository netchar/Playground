package com.example.playgroundapp.domain

import com.example.playgroundapp.data.Resource
import com.example.playgroundapp.domain.entity.Author

interface AuthorRepository {
    suspend fun getItems(): Resource<List<Author>>
}