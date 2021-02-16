package com.example.playgroundapp.data.repository

import com.example.playgroundapp.data.DataMapper
import com.example.playgroundapp.data.remote.dto.CharacterResponseApi
import com.example.playgroundapp.data.remote.source.CharacterRemoteDataSource
import com.example.playgroundapp.domain.CharacterRepository
import com.example.playgroundapp.domain.common.Result
import com.example.playgroundapp.domain.entity.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepositoryImpl(
    private val remote: CharacterRemoteDataSource,
    private val mapper: DataMapper
) : CharacterRepository {

    override suspend fun getCharacters(): Result<List<Character>> {
        return withContext(Dispatchers.IO) {
            when (val response: Result<CharacterResponseApi> = remote.getAuthors()) {
                is Result.Success -> Result.Success(response.data.results.map { mapper.map(it) })
                is Result.Error -> Result.Error(response.error)
            }
        }
    }
}