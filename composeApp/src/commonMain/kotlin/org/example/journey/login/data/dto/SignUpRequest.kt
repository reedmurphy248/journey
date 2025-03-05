package org.example.journey.login.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequest(
    val username: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val profilePicture: String,
    val currentLocation: String?,
    val bio: String?,
)
