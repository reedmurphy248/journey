package org.example.journey.login.presentation

sealed interface LoginAction {
    data class UsernameChanged(val username: String): LoginAction
    data class PasswordChanged(val password: String): LoginAction
    data object BackButtonPressed: LoginAction
    data object LoginButtonPressed: LoginAction
}