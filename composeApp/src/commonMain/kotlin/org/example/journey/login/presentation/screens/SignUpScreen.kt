package org.example.journey.login.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import journey.composeapp.generated.resources.Res
import journey.composeapp.generated.resources.back_button_label
import journey.composeapp.generated.resources.continue_button_label
import journey.composeapp.generated.resources.create_account_label
import journey.composeapp.generated.resources.email_placeholder
import journey.composeapp.generated.resources.journey
import journey.composeapp.generated.resources.password_placeholder
import journey.composeapp.generated.resources.phone_number_placeholder
import journey.composeapp.generated.resources.username_placeholder
import org.example.journey.core.presentation.displayMedium
import org.example.journey.core.presentation.labelLarge
import org.example.journey.core.presentation.onBackground
import org.example.journey.login.presentation.CurrentScreen
import org.example.journey.login.presentation.FormValidationError
import org.example.journey.login.presentation.SignUpAction
import org.example.journey.login.presentation.SignUpState
import org.example.journey.login.presentation.SignUpViewModel
import org.example.journey.login.presentation.authButtonSpacing
import org.example.journey.login.presentation.authScreensWidth
import org.example.journey.login.presentation.components.AuthButtonPrimary
import org.example.journey.login.presentation.components.AuthButtonSecondary
import org.example.journey.login.presentation.components.FormInputBar
import org.jetbrains.compose.resources.stringResource

@Composable
fun SignUpScreenRoot(
    viewModel: SignUpViewModel,
    onContinueClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    // Need to change this so that it doesn't trigger continue whenever page is visited on back click
    LaunchedEffect(state.formValidationFinished) {
        if (state.formValidationFinished && state.validationErrors.isEmpty()) {
            onContinueClick()
        }
    }

    SignUpScreen(
        state = state,
        onAction = { action ->
            when (action) {
                SignUpAction.BackButtonPressed -> {
                    viewModel.onAction(action)
                    onBackClick()
                }
                else -> viewModel.onAction(action)
            }
        }
    )
}

@Composable
fun SignUpScreen(
    state: SignUpState,
    onAction: (SignUpAction) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = stringResource(Res.string.journey), color = onBackground, style = displayMedium)
        Spacer(modifier = Modifier.height(50.dp))
        Column(
            modifier = Modifier.fillMaxWidth(authScreensWidth),
            verticalArrangement = Arrangement.spacedBy(50.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                Text(text = stringResource(Res.string.create_account_label), color = onBackground, style = labelLarge)
                Column(
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    FormInputBar(
                        leadingIcon = Icons.Outlined.Person,
                        value = state.username.value,
                        placeholder = stringResource(Res.string.username_placeholder),
                        onValueChange = {
                            onAction(SignUpAction.UsernameChanged(it))
                        },
                        error = state.validationErrors.find { error ->
                            error == FormValidationError.InvalidUsername ||
                                    error == FormValidationError.UsernameNotUnique
                        }
                    )
                    FormInputBar(
                        leadingIcon = Icons.Outlined.Lock,
                        value = state.password.value,
                        placeholder = stringResource(Res.string.password_placeholder),
                        onValueChange = {
                            onAction(SignUpAction.PasswordChanged(it))
                        },
                        isPassword = true,
                        error = state.validationErrors.find { error ->
                            error == FormValidationError.InvalidPassword
                        }
                    )
                    FormInputBar(
                        leadingIcon = Icons.Outlined.Email,
                        value = state.email.value,
                        placeholder = stringResource(Res.string.email_placeholder),
                        onValueChange = {
                            onAction(SignUpAction.EmailChanged(it))
                        },
                        error = state.validationErrors.find { error ->
                            error == FormValidationError.InvalidEmail ||
                                    error == FormValidationError.EmailNotUnique
                        }
                    )
                    FormInputBar(
                        leadingIcon = Icons.Outlined.Phone,
                        value = state.phoneNumber.value,
                        placeholder = stringResource(Res.string.phone_number_placeholder),
                        onValueChange = {
                            onAction(SignUpAction.PhoneNumberChanged(it))
                        },
                        error = state.validationErrors.find { error ->
                            error == FormValidationError.InvalidPhoneNumber ||
                                    error == FormValidationError.PhoneNumberNotUnique
                        }
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(authButtonSpacing)
            ) {
                AuthButtonPrimary(
                    text = stringResource(Res.string.continue_button_label),
                    onClick = {
                        onAction(SignUpAction.ContinueButtonPressed(CurrentScreen.SignUp))
                    }
                )
                AuthButtonSecondary(
                    text = stringResource(Res.string.back_button_label),
                    onClick = {
                        onAction(SignUpAction.BackButtonPressed)
                    }
                )
            }
        }
    }
}