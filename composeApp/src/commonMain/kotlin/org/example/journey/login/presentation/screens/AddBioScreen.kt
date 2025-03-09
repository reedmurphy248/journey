package org.example.journey.login.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import journey.composeapp.generated.resources.Res
import journey.composeapp.generated.resources.add_a_bio_body
import journey.composeapp.generated.resources.add_a_bio_title
import journey.composeapp.generated.resources.back_button_label
import journey.composeapp.generated.resources.bio_placeholder
import journey.composeapp.generated.resources.continue_button_label
import journey.composeapp.generated.resources.current_location_placeholder
import journey.composeapp.generated.resources.first_name_placeholder
import journey.composeapp.generated.resources.journey
import journey.composeapp.generated.resources.last_name_placeholder
import journey.composeapp.generated.resources.skip_for_now_button_label
import org.example.journey.core.presentation.bodyLarge
import org.example.journey.core.presentation.displayMedium
import org.example.journey.core.presentation.labelMedium
import org.example.journey.core.presentation.onBackground
import org.example.journey.core.presentation.primary
import org.example.journey.core.presentation.tertiary
import org.example.journey.core.presentation.titleLarge
import org.example.journey.login.presentation.CurrentScreen
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
fun AddBioScreenRoot(
    viewModel: SignUpViewModel,
    onContinueClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    AddBioScreen(
        state = state,
        onAction = { action ->
            when (action) {
                SignUpAction.BackButtonPressed -> {
                    onBackClick()
                }
                is SignUpAction.ContinueButtonPressed -> {
                    onContinueClick()
                }
                else -> viewModel.onAction(action)
            }
        }
    )
}

@Composable
fun AddBioScreen(
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
            verticalArrangement = Arrangement.spacedBy(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // title
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = stringResource(Res.string.add_a_bio_title),
                    color = onBackground,
                    style = titleLarge
                )
                Text(
                    text = stringResource(Res.string.add_a_bio_body),
                    color = onBackground,
                    style = bodyLarge
                )
            }
            Column(
                modifier = Modifier
                    .heightIn(min = 210.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                FormInputBar(
                    leadingIcon = Icons.Outlined.Person,
                    value = state.firstName,
                    placeholder = stringResource(Res.string.first_name_placeholder),
                    onValueChange = {
                        onAction(SignUpAction.FirstNameChanged(it))
                    }
                )
                FormInputBar(
                    leadingIcon = Icons.Outlined.Person,
                    value = state.lastName,
                    placeholder = stringResource(Res.string.last_name_placeholder),
                    onValueChange = {
                        onAction(SignUpAction.LastNameChanged(it))
                    }
                )
                FormInputBar(
                    leadingIcon = Icons.Outlined.Person,
                    value = state.currentLocation ?: "",
                    placeholder = stringResource(Res.string.current_location_placeholder),
                    onValueChange = {
                        onAction(SignUpAction.CurrentLocationChanged(it))
                    }
                )
                FormInputBar(
                    leadingIcon = Icons.Outlined.Create,
                    value = state.bio ?: "",
                    placeholder = stringResource(Res.string.bio_placeholder),
                    onValueChange = {
                        onAction(SignUpAction.BioChanged(it))
                    }
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(primary)
                        .height(6.dp)
                        .width(70.dp)
                )
                Box(
                    modifier = Modifier
                        .background(tertiary)
                        .height(6.dp)
                        .width(70.dp)
                )
                Box(
                    modifier = Modifier
                        .background(tertiary)
                        .height(6.dp)
                        .width(70.dp)
                )
            }
            // button section
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Text(
                    text = stringResource(Res.string.skip_for_now_button_label),
                    color = onBackground,
                    style = labelMedium
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(authButtonSpacing)
                ) {
                    AuthButtonPrimary(
                        text = stringResource(Res.string.continue_button_label),
                        onClick = {
                            onAction(SignUpAction.ContinueButtonPressed(CurrentScreen.AddBio))
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
}