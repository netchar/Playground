package com.example.playgroundapp.data.repository

import com.example.playgroundapp.data.DataMapper
import com.example.playgroundapp.data.Resource
import com.example.playgroundapp.data.remote.HttpResult
import com.example.playgroundapp.data.remote.dto.CharacterResponseApi
import com.example.playgroundapp.data.remote.source.AuthorRemoteDataSource
import com.example.playgroundapp.domain.AuthorRepository
import com.example.playgroundapp.domain.entity.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepositoryImpl(
    private val remote: AuthorRemoteDataSource,
    private val mapper: DataMapper
) : AuthorRepository {

    override suspend fun getItems(): Resource<List<Character>> {
        return withContext(Dispatchers.IO) {

            val response: HttpResult<CharacterResponseApi> = remote.getAuthors()

            when (response) {
                is HttpResult.Success -> Resource.Success(response.data.results.map { mapper.map(it) })
                is HttpResult.Error -> TODO()
            }
        }
    }
}