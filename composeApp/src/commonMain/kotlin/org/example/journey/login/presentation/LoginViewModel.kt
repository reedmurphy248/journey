package org.example.journey.login.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel() : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.UsernameChanged -> {
                _state.value = _state.value.copy(username = action.username)
            }
            is LoginAction.PasswordChanged -> {
                _state.value = _state.value.copy(password = action.password)
            }
            LoginAction.BackButtonPressed -> {
                // reset
                _state.value = LoginState()
            }
            LoginAction.LoginButtonPressed -> {
                _state.value = _state.value.copy(isLoading = true)
                // TODO: login
                // set login details in db
            }
        }
    }

}