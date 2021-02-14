package com.example.playgroundapp.domain.entity

import com.google.gson.annotations.SerializedName

data class Character(
        val id: Int,
        val name: String,
        val status: String,
        val species: String,
        val type: String,
        val gender: String,
        val image: String,
)