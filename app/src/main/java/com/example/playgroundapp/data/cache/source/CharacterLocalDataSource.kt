package com.example.playgroundapp.data.cache.source

import com.example.playgroundapp.data.cache.dto.CharacterDb
import kotlinx.coroutines.flow.Flow

interface CharacterLocalDataSource {
    suspend fun insert(item: CharacterDb)
    suspend fun insert(items: List<CharacterDb>)
    suspend fun getAll(): List<CharacterDb>
    fun getAllFlowable(): Flow<List<CharacterDb>>
    suspend fun get(characterId: Int): CharacterDb
    suspend fun delete(vararg characters: CharacterDb): Int
}