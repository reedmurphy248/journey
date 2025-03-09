package org.example.journey.login.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

private const val usernameMinLength = 10
private const val usernameMaxLength = 30
private val usernameRegex = Regex("^[a-zA-Z0-9_]{$usernameMinLength,$usernameMaxLength}\$")

enum class FormValidationError {
    InvalidUsername,
    UsernameNotUnique,
    InvalidPassword,
    InvalidEmail,
    EmailNotUnique,
    InvalidPhoneNumber,
    PhoneNumberNotUnique
}

class SignUpViewModel() : ViewModel() {
    private val _state = MutableStateFlow(SignUpState())
    val state = _state.asStateFlow()

    fun onAction(action: SignUpAction) {
        when (action) {
            SignUpAction.BackButtonPressed -> TODO()
            is SignUpAction.BioChanged -> {
                _state.value = _state.value.copy(bio = action.bio)
            }
            is SignUpAction.CurrentLocationChanged -> {
                _state.value = _state.value.copy(currentLocation = action.currentLocation)
            }
            is SignUpAction.EmailChanged -> {
                _state.value = _state.value.copy(email = _state.value.email.copy(value = action.email))
            }
            is SignUpAction.FirstNameChanged -> {
                _state.value = _state.value.copy(firstName = action.firstName)
            }
            is SignUpAction.LastNameChanged -> {
                _state.value = _state.value.copy(lastName = action.lastName)
            }
            is SignUpAction.PasswordChanged -> {
                _state.value = _state.value.copy(password = _state.value.password.copy(value = action.password))
            }
            is SignUpAction.PhoneNumberChanged -> {
                _state.value = _state.value.copy(phoneNumber = _state.value.phoneNumber.copy(value = action.phoneNumber))
            }
            is SignUpAction.ProfilePictureChanged -> {
                _state.value = _state.value.copy(profilePicture = action.profilePicture)
            }
            is SignUpAction.UsernameChanged -> {
                _state.value = _state.value.copy(username = _state.value.username.copy(value = action.username))
            }
            is SignUpAction.ContinueButtonPressed -> {
                _state.value = _state.value.copy(formValidationFinished = false)
                _state.value = _state.value.copy(isLoading = true)
                when (action.currentScreen) {
                    CurrentScreen.SignUp -> {
                        _state.value = _state.value.copy(
                            validationErrors = validateForm()
                        )
                    }
                    CurrentScreen.SelectProfilePicture -> {

                    }
                    CurrentScreen.AddBio -> {

                    }
                    CurrentScreen.SignUpEndSummary -> TODO()
                }
                _state.value = _state.value.copy(isLoading = false)
                _state.value = _state.value.copy(formValidationFinished = true)
            }
            is SignUpAction.LaunchAppButtonPressed -> {

            }
            SignUpAction.ChangeProfilePictureButtonPressed -> TODO()
        }
    }

    // username must be between 10 and 30 characters, contain only letters, numbers, and underscores
    // password must be between 8 and 30 characters, contain at least one letter, one number, and one special character
    // email must be a valid email address
    // phone number must be a valid phone number

    private fun validateForm(): List<FormValidationError> {
        val validationErrors = mutableListOf<FormValidationError>()

        val usernameError = validateUsername(_state.value.username.value)
        if (usernameError != null) {
            validationErrors.add(usernameError)
        }

        val passwordError = validatePassword(_state.value.password.value)
        if (passwordError != null) {
            validationErrors.add(passwordError)
        }

        val emailError = validateEmail(_state.value.email.value)
        if (emailError != null) {
            validationErrors.add(emailError)
        }

        val phoneNumberError = validatePhoneNumber(_state.value.phoneNumber.value)
        if (phoneNumberError != null) {
            validationErrors.add(phoneNumberError)
        }

        return validationErrors
    }

    private fun validateUsername(username: String): FormValidationError? {
        if (validateUsernameStructure(username)) {
            if (!validateUsernameUnique(username)) {
                return FormValidationError.UsernameNotUnique
            } else {
                _state.value = _state.value.copy(username = _state.value.username.copy(validated = true))
            }
        } else {
            return FormValidationError.InvalidUsername
        }

        return null
    }

    private fun validateUsernameStructure(username: String): Boolean {
        return username.length in usernameMinLength..usernameMaxLength &&
            username.matches(usernameRegex)
    }

    private fun validateUsernameUnique(username: String): Boolean {
        // need to implement backend call
        return true
    }

    private fun validatePassword(password: String): FormValidationError? {
        if (password.matches(Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,30}\$"))) {
            _state.value = _state.value.copy(password = _state.value.password.copy(validated = true))
        } else {
            return FormValidationError.InvalidPassword
        }

        return null
    }

    private fun validateEmail(email: String): FormValidationError? {
        if (validateEmailStructure(email)) {
            if (!validateEmailUnique(email)) {
                return FormValidationError.EmailNotUnique
            } else {
                _state.value = _state.value.copy(email = _state.value.email.copy(validated = true))
            }
        } else {
            return FormValidationError.InvalidEmail
        }

        return null
    }

    private fun validateEmailStructure(email: String): Boolean {
        return email.matches(Regex("[a-zA-Z0-9+_.-]+@[a-z-]+\\.[a-z]+"));
    }

    private fun validateEmailUnique(email: String): Boolean {
        // need to implement backend call
        return true
    }

    private fun validatePhoneNumber(phoneNumber: String): FormValidationError? {
        // figure out how to validate with localization
        return null
    }

}