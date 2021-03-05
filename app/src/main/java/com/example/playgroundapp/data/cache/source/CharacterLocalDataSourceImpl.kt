package com.example.playgroundapp.data.cache.source

import com.example.playgroundapp.data.cache.dao.CharactersDao
import com.example.playgroundapp.data.cache.dto.DBCharacter
import kotlinx.coroutines.flow.Flow

class CharacterLocalDataSourceImpl(
        private val dao: CharactersDao
) : CharacterLocalDataSource {
    override suspend fun insert(item: DBCharacter) {
        dao.insert(item)
    }

    override suspend fun insert(items: List<DBCharacter>) {
        dao.insert(items)
    }

    override suspend fun getAll(): List<DBCharacter> {
        return dao.getAll()
    }

    override fun getAllFlowable(): Flow<List<DBCharacter>> {
        return dao.getAllFlowable()
    }

    override suspend fun get(characterId: Int): DBCharacter {
        return dao.get(characterId)
    }

    override suspend fun delete(vararg DBCharacters: DBCharacter): Int {
        return dao.delete(*DBCharacters)
    }
}