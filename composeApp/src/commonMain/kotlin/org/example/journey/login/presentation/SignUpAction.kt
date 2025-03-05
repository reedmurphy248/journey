package org.example.journey.login.presentation

sealed interface SignUpAction {
    data class UsernameChanged(val username: String): SignUpAction
    data class PasswordChanged(val password: String): SignUpAction
    data class FirstNameChanged(val firstName: String): SignUpAction
    data class LastNameChanged(val lastName: String): SignUpAction
    data class EmailChanged(val email: String): SignUpAction
    data class PhoneNumberChanged(val phoneNumber: String): SignUpAction
    data class ProfilePictureChanged(val profilePicture: String): SignUpAction
    data class CurrentLocationChanged(val currentLocation: String): SignUpAction
    data class BioChanged(val bio: String): SignUpAction
    data object BackButtonPressed: SignUpAction
    data object ContinueButtonPressed: SignUpAction
    data object SignUpButtonPressed: SignUpAction
}