package com.example.playgroundapp.data

import com.example.playgroundapp.data.cache.dto.DBCharacter
import com.example.playgroundapp.data.remote.dto.ApiCharacter
import com.example.playgroundapp.domain.entity.Character

class DataMapper {
    fun mapToDomainEntity(data: DBCharacter): Character = Character(data.id, data.name, data.type, data.image)
    fun mapToDatabaseEntity(data: ApiCharacter) : DBCharacter = DBCharacter(data.id, data.name, data.type, data.image)
}