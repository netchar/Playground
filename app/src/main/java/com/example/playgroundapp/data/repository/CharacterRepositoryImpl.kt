package com.example.playgroundapp.data.repository

import com.example.playgroundapp.data.DataMapper
import com.example.playgroundapp.data.NetworkBoundResource
import com.example.playgroundapp.data.Resource
import com.example.playgroundapp.data.cache.dao.CharactersDao
import com.example.playgroundapp.data.cache.dto.CharacterDb
import com.example.playgroundapp.data.remote.dto.CharacterApi
import com.example.playgroundapp.data.remote.dto.CharacterResponseApi
import com.example.playgroundapp.data.remote.source.CharacterRemoteDataSource
import com.example.playgroundapp.domain.CharacterRepository
import com.example.playgroundapp.domain.common.Result
import com.example.playgroundapp.domain.entity.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class CharacterRepositoryImpl(
    private val remote: CharacterRemoteDataSource,
    private val cache: CharactersDao,
    private val mapper: DataMapper
) : CharacterRepository {

    override fun getCharacters(): Flow<Resource<List<Character>>> {
        return object : NetworkBoundResource<List<Character>, CharacterResponseApi>() {
            override fun fetchFromDatabase(): Flow<List<Character>> {
                val dbRecords: Flow<List<CharacterDb>> = cache.getAllFlowable()
                return dbRecords.map { value: List<CharacterDb> -> value.map { mapper.mapToDomainEntity(it) } }
            }

            override fun shouldFetchRemoteData(data: List<Character>?): Boolean {
                return true
            }

            override suspend fun fetchFromRemote(): Result<CharacterResponseApi> {
                return remote.getCharacters()
            }

            override suspend fun saveRemoteResults(data: CharacterResponseApi) {
                val dbEntities = data.results.map { mapper.mapToDatabaseEntity(it) }
                cache.delete(*dbEntities.toTypedArray())
                cache.insert(dbEntities)
            }
        }.asFlow()
    }

    override suspend fun getCharactersNow(): Result<List<Character>> {
        return withContext(Dispatchers.IO) {
            val databaseEntities = cache.getAll()

            if (isCacheValid(databaseEntities)) {
                val domainEntities = databaseEntities.map { mapper.mapToDomainEntity(it) }
                Result.Success(domainEntities)
            } else {
                when (val networkResponse: Result<CharacterResponseApi> = remote.getCharacters()) {
                    is Result.Success -> {
                        putIntoCache(networkResponse.data.results)
                        val domainEntities = cache.getAll().map { mapper.mapToDomainEntity(it) }
                        Result.Success(domainEntities)
                    }
                    is Result.Error -> Result.Error(networkResponse.error)
                }
            }
        }
    }

    private fun isCacheValid(databaseEntities: List<CharacterDb>) = databaseEntities.isNotEmpty()

    private suspend fun putIntoCache(charactersApi: List<CharacterApi>) {
        val dbEntities = charactersApi.map { mapper.mapToDatabaseEntity(it) }
        cache.delete(*dbEntities.toTypedArray())
        cache.insert(dbEntities)
    }
}