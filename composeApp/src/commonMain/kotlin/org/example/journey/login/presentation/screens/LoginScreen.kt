package org.example.journey.login.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import journey.composeapp.generated.resources.Res
import journey.composeapp.generated.resources.back_button_label
import journey.composeapp.generated.resources.journey
import journey.composeapp.generated.resources.login_button_label
import journey.composeapp.generated.resources.password_placeholder
import journey.composeapp.generated.resources.username_or_email_placeholder
import org.example.journey.core.presentation.displayMedium
import org.example.journey.core.presentation.labelLarge
import org.example.journey.core.presentation.onBackground
import org.example.journey.login.domain.User
import org.example.journey.login.presentation.LoginAction
import org.example.journey.login.presentation.LoginState
import org.example.journey.login.presentation.LoginViewModel
import org.example.journey.login.presentation.authButtonSpacing
import org.example.journey.login.presentation.authScreensWidth
import org.example.journey.login.presentation.components.AuthButtonPrimary
import org.example.journey.login.presentation.components.AuthButtonSecondary
import org.example.journey.login.presentation.components.FormInputBar
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginScreenRoot(
    viewModel: LoginViewModel,
    onBackClick: () -> Unit,
    onLoginSuccess: (User) -> Unit,
    onLoginFailure: (String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.error) {
        state.error?.let { error ->
            onLoginFailure(error)
        }
    }

    LaunchedEffect(state.loggedInUser) {
        state.loggedInUser?.let { user ->
            onLoginSuccess(user)
        }
    }

    LoginScreen(
        state = state,
        onAction = { action ->
            when (action) {
                LoginAction.BackButtonPressed -> {
                    viewModel.onAction(LoginAction.BackButtonPressed)
                    onBackClick()
                }
                else -> viewModel.onAction(action)
            }
        }
    )
}

@Composable
fun LoginScreen(
    state: LoginState,
    onAction: (LoginAction) -> Unit
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
                Text(text = stringResource(Res.string.login_button_label), color = onBackground, style = labelLarge)
                Column(
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    FormInputBar(
                        leadingIcon = Icons.Outlined.Person,
                        value = state.username,
                        placeholder = stringResource(Res.string.username_or_email_placeholder),
                        onValueChange = {
                            onAction(LoginAction.UsernameChanged(it))
                        }
                    )
                    FormInputBar(
                        leadingIcon = Icons.Outlined.Lock,
                        value = state.password,
                        placeholder = stringResource(Res.string.password_placeholder),
                        onValueChange = {
                            onAction(LoginAction.PasswordChanged(it))
                        },
                        isPassword = true
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(authButtonSpacing)
            ) {
                AuthButtonPrimary(
                    text = stringResource(Res.string.login_button_label),
                    onClick = { onAction(LoginAction.LoginButtonPressed) }
                )
                AuthButtonSecondary(
                    text = stringResource(Res.string.back_button_label),
                    onClick = { onAction(LoginAction.BackButtonPressed) }
                )
            }
        }
    }
}