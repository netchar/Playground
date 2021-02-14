package com.example.playgroundapp.data

import com.example.playgroundapp.data.remote.dto.AuthorApi
import com.example.playgroundapp.domain.entity.Author

class DataMapper {
    fun map(data: AuthorApi): Author {
        return Author(data.id, data.first_name, data.last_name, data.avatar, data.email)
    }
}
