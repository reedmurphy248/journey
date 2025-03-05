package org.example.journey.login.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val id: String,
    val username: String,
    val profilePicture: String,
    val firstName: String,
    val lastName: String,
    val location: String,
    val bio: String
)
