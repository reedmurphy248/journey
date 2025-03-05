package org.example.journey.login.data.mapper

import org.example.journey.login.data.dto.LoginResponse
import org.example.journey.login.domain.User

fun LoginResponse.toUser(): User {
    return User(
        id = id,
        username = username,
        profilePicture = profilePicture,
        firstName = firstName,
        lastName = lastName,
        location = location,
        bio = bio
    )
}