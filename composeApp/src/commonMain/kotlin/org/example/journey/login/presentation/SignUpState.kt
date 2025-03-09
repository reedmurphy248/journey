package org.example.journey.login.presentation

//data class SignUpState(
//    val username: String = "",
//    val password: String = "",
//    val firstName: String = "",
//    val lastName: String = "",
//    val email: String = "",
//    val phoneNumber: String = "",
//    val profilePicture: String = "",
//    val currentLocation: String? = null,
//    val bio: String? = null,
//    val validationErrors: List<String> = emptyList(),
//)

data class SignUpState(
    val username: ValidatableParameter = ValidatableParameter(
        value = "",
    ),
    val password: ValidatableParameter = ValidatableParameter(
        value = "",
    ),
    val firstName: String = "",
    val lastName: String = "",
    val email: ValidatableParameter = ValidatableParameter(
        value = "",
    ),
    val phoneNumber: ValidatableParameter = ValidatableParameter(
        value = "",
    ),
    val profilePicture: String? = null,
    val currentLocation: String? = null,
    val bio: String? = null,
    val validationErrors: List<FormValidationError> = emptyList(),
    val isLoading: Boolean = false,
    val formValidationFinished: Boolean = false,
)

data class ValidatableParameter(
    val value: String,
    val validated: Boolean = false,
//    val validator: (String) -> Boolean,
    val errors: List<String> = emptyList(),
)