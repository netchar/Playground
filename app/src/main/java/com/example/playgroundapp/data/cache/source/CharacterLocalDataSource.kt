package com.example.playgroundapp.data.cache.source

import com.example.playgroundapp.data.cache.dto.DBCharacter
import kotlinx.coroutines.flow.Flow

interface CharacterLocalDataSource {
    suspend fun insert(item: DBCharacter)
    suspend fun insert(items: List<DBCharacter>)
    suspend fun getAll(): List<DBCharacter>
    fun getAllFlowable(): Flow<List<DBCharacter>>
    suspend fun get(characterId: Int): DBCharacter
    suspend fun delete(vararg DBCharacters: DBCharacter): Int
}