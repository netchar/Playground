package com.example.playgroundapp.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.playgroundapp.data.cache.dao.CharactersDao
import com.example.playgroundapp.data.cache.dto.CharacterDb

@Database(entities = [CharacterDb::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getCharactersDao() : CharactersDao
}