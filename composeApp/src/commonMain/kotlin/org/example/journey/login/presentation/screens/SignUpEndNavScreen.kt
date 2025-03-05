package org.example.journey.login.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import journey.composeapp.generated.resources.Res
import journey.composeapp.generated.resources.create_journey_label
import journey.composeapp.generated.resources.explore_label
import journey.composeapp.generated.resources.go_label
import journey.composeapp.generated.resources.journey
import journey.composeapp.generated.resources.shop_travel_and_momentos_label
import journey.composeapp.generated.resources.start_your_label
import journey.composeapp.generated.resources.visit_profile_label
import org.example.journey.core.presentation.displayMedium
import org.example.journey.core.presentation.labelMedium
import org.example.journey.core.presentation.onBackground
import org.example.journey.login.presentation.authScreensWidth
import org.example.journey.login.presentation.components.AuthButtonPrimary
import org.example.journey.login.presentation.components.PostRegisterNavButton
import org.jetbrains.compose.resources.stringResource

@Composable
fun SignUpEndNavScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = stringResource(Res.string.start_your_label), color = onBackground, style = labelMedium)
        Text(text = stringResource(Res.string.journey), color = onBackground, style = displayMedium)
        Spacer(modifier = Modifier.height(60.dp))
        Column(
            modifier = Modifier.fillMaxWidth(authScreensWidth),
            verticalArrangement = Arrangement.spacedBy(80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                PostRegisterNavButton(
                    clicked = false,
                    text = stringResource(Res.string.create_journey_label),
                    onClick = {}
                )
                PostRegisterNavButton(
                    clicked = false,
                    text = stringResource(Res.string.explore_label),
                    onClick = {}
                )
                PostRegisterNavButton(
                    clicked = false,
                    text = stringResource(Res.string.visit_profile_label),
                    onClick = {}
                )
                PostRegisterNavButton(
                    clicked = false,
                    text = stringResource(Res.string.shop_travel_and_momentos_label),
                    onClick = {}
                )
            }
            AuthButtonPrimary(
                text = stringResource(Res.string.go_label),
                onClick = {}
            )
        }
    }
}