package org.example.journey.login.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import journey.composeapp.generated.resources.Res
import journey.composeapp.generated.resources.create_account_label
import journey.composeapp.generated.resources.journey
import journey.composeapp.generated.resources.journey_catch_phrase
import journey.composeapp.generated.resources.sign_in_label
import org.example.journey.core.presentation.displayLarge
import org.example.journey.core.presentation.displaySmall
import org.example.journey.core.presentation.onBackground
import org.example.journey.login.presentation.authButtonSpacing
import org.example.journey.login.presentation.authScreensWidth
import org.example.journey.login.presentation.components.AuthButtonPrimary
import org.example.journey.login.presentation.components.AuthButtonSecondary
import org.jetbrains.compose.resources.stringResource

@Composable
fun AuthHomeScreen(
    onLoginClick: () -> Unit, onSignUpClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        // background animations
        // content
        Column(
            modifier = Modifier
                .heightIn(min = 470.dp)
                .fillMaxWidth(authScreensWidth),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(Res.string.journey), color = onBackground, style = displayLarge
                )
                Text(
                    text = stringResource(Res.string.journey_catch_phrase), color = onBackground, style = displaySmall
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(authButtonSpacing)
            ) {
                AuthButtonPrimary(stringResource(Res.string.create_account_label), onClick = onSignUpClick)
                AuthButtonSecondary(stringResource(Res.string.sign_in_label), onClick = onLoginClick)
            }
        }
    }
}