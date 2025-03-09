package org.example.journey.login.presentation

import org.example.journey.login.domain.User

data class LoginState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val loggedInUser: User? = null
)
