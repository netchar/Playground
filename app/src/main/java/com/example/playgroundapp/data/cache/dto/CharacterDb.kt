package com.example.playgroundapp.data.cache.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "characters")
data class CharacterDb(
    @PrimaryKey
    val id: Int,
    val name: String,
    val type: String,
    val image: String,
)