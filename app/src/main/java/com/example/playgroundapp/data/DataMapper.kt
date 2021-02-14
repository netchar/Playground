package com.example.playgroundapp.data

import com.example.playgroundapp.data.remote.dto.CharacterApi
import com.example.playgroundapp.domain.entity.Character

class DataMapper {
    fun map(data: CharacterApi): Character {
        return Character(data.id, data.name, data.status, data.species, data.type, data.gender, data.image)
    }
}
