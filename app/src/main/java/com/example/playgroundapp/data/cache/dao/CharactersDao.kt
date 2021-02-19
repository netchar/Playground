package com.example.playgroundapp.data.cache.dao

import androidx.room.*
import com.example.playgroundapp.data.cache.dto.CharacterDb

@Dao
interface CharactersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: CharacterDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<CharacterDb>)

    @Query("SELECT * FROM characters")
    suspend fun getAll(): List<CharacterDb>

    @Query("SELECT * FROM characters WHERE characters.id = :characterId")
    suspend fun get(characterId: Int): CharacterDb

    @Delete
    suspend fun delete(vararg characters: CharacterDb) : Int
}