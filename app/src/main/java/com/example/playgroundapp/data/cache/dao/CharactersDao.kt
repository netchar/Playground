package com.example.playgroundapp.data.cache.dao

import androidx.room.*
import com.example.playgroundapp.data.cache.dto.DBCharacter
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DBCharacter)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<DBCharacter>)

    @Query("SELECT * FROM characters")
    suspend fun getAll(): List<DBCharacter>

    @Query("SELECT * FROM characters")
    fun getAllFlowable(): Flow<List<DBCharacter>>

    @Query("SELECT * FROM characters WHERE characters.id = :characterId")
    suspend fun get(characterId: Int): DBCharacter

    @Delete
    suspend fun delete(vararg DBCharacters: DBCharacter) : Int
}