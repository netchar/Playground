package com.example.playgroundapp.data

import com.example.playgroundapp.data.cache.dto.CharacterDb
import com.example.playgroundapp.data.remote.dto.CharacterApi
import com.example.playgroundapp.domain.entity.Character

class DataMapper {
    fun mapToDomainEntity(data: CharacterDb): Character = Character(data.id, data.name, data.type, data.image)
    fun mapToDatabaseEntity(data: CharacterApi) : CharacterDb = CharacterDb(data.id, data.name, data.type, data.image)
}
