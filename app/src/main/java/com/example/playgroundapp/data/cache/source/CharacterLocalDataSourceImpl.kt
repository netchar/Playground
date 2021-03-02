package com.example.playgroundapp.data.cache.source

import com.example.playgroundapp.data.cache.dao.CharactersDao
import com.example.playgroundapp.data.cache.dto.CharacterDb
import kotlinx.coroutines.flow.Flow

class CharacterLocalDataSourceImpl(
        private val dao: CharactersDao
) : CharacterLocalDataSource {
    override suspend fun insert(item: CharacterDb) {
        dao.insert(item)
    }

    override suspend fun insert(items: List<CharacterDb>) {
        dao.insert(items)
    }

    override suspend fun getAll(): List<CharacterDb> {
        return dao.getAll()
    }

    override fun getAllFlowable(): Flow<List<CharacterDb>> {
        return dao.getAllFlowable()
    }

    override suspend fun get(characterId: Int): CharacterDb {
        return dao.get(characterId)
    }

    override suspend fun delete(vararg characters: CharacterDb): Int {
        return dao.delete(*characters)
    }
}