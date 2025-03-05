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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import journey.composeapp.generated.resources.Res
import journey.composeapp.generated.resources.back_button_label
import journey.composeapp.generated.resources.create_account_label
import journey.composeapp.generated.resources.journey
import journey.composeapp.generated.resources.login_button_label
import journey.composeapp.generated.resources.password_placeholder
import journey.composeapp.generated.resources.sign_in_label
import journey.composeapp.generated.resources.username_or_email_placeholder
import org.example.journey.core.presentation.displayMedium
import org.example.journey.core.presentation.labelLarge
import org.example.journey.core.presentation.onBackground
import org.example.journey.login.presentation.authButtonSpacing
import org.example.journey.login.presentation.authScreensWidth
import org.example.journey.login.presentation.components.AuthButtonPrimary
import org.example.journey.login.presentation.components.AuthButtonSecondary
import org.example.journey.login.presentation.components.FormInputBar
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginScreen(

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
                        value = "",
                        placeholder = stringResource(Res.string.username_or_email_placeholder),
                        onValueChange = {}
                    )
                    FormInputBar(
                        leadingIcon = Icons.Outlined.Lock,
                        value = "",
                        placeholder = stringResource(Res.string.password_placeholder),
                        onValueChange = {}
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(authButtonSpacing)
            ) {
                AuthButtonPrimary(
                    text = stringResource(Res.string.login_button_label),
                    onClick = {}
                )
                AuthButtonSecondary(
                    text = stringResource(Res.string.back_button_label),
                    onClick = {}
                )
            }
        }
    }
}