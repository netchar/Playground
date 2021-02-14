package com.example.playgroundapp.data.remote.dto

import com.example.playgroundapp.domain.entity.Author
import com.google.gson.annotations.SerializedName


data class AuthorApi(
    @SerializedName("id") val id: Int,
    @SerializedName("email") val email: String,
    @SerializedName("first_name") val first_name: String,
    @SerializedName("last_name") val last_name: String,
    @SerializedName("avatar") val avatar: String
)