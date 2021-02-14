package com.example.playgroundapp.data.repository

import com.example.playgroundapp.data.DataMapper
import com.example.playgroundapp.data.Resource
import com.example.playgroundapp.data.remote.HttpResult
import com.example.playgroundapp.data.remote.BaseRemoteDataSource
import com.example.playgroundapp.data.remote.api.AuthorApiService
import com.example.playgroundapp.data.remote.dto.ObjectApi
import com.example.playgroundapp.data.remote.source.AuthorRemoteDataSource
import com.example.playgroundapp.domain.AuthorRepository
import com.example.playgroundapp.domain.entity.Author
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthorRepositoryImpl(
    private val remote: AuthorRemoteDataSource,
    private val mapper: DataMapper
) : AuthorRepository {

    override suspend fun getItems(): Resource<List<Author>> {
        return withContext(Dispatchers.IO) {

            val response: HttpResult<ObjectApi> = remote.getAuthors()

            when (response) {
                is HttpResult.Success -> Resource.Success(response.data.items.map { mapper.map(it) })
                is HttpResult.Error -> TODO()
            }
        }
    }
}