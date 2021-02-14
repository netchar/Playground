package com.example.playgroundapp.domain.entity

data class Author(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val photoUrl: String,
    val email: String
) {
    val fullName: String get() = "$firstName $lastName"
}