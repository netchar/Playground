package com.example.playgroundapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CharacterResponseApi(
        @SerializedName("results") val results: List<CharacterApi>
)