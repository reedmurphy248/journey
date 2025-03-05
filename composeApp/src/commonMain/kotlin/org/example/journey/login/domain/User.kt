package org.example.journey.login.domain

data class User(
    val id: String,
    val username: String,
    val profilePicture: String,
    val firstName: String,
    val lastName: String,
    val location: String,
    val bio: String
)
