package org.example.journey.login.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import journey.composeapp.generated.resources.Res
import journey.composeapp.generated.resources.add_a_bio_body
import journey.composeapp.generated.resources.add_a_bio_title
import journey.composeapp.generated.resources.back_button_label
import journey.composeapp.generated.resources.bio_placeholder
import journey.composeapp.generated.resources.connect_body
import journey.composeapp.generated.resources.connect_title
import journey.composeapp.generated.resources.continue_button_label
import journey.composeapp.generated.resources.create_account_label
import journey.composeapp.generated.resources.current_location_placeholder
import journey.composeapp.generated.resources.discover_body
import journey.composeapp.generated.resources.discover_title
import journey.composeapp.generated.resources.journey
import journey.composeapp.generated.resources.share_body
import journey.composeapp.generated.resources.share_title
import journey.composeapp.generated.resources.skip_for_now_button_label
import org.example.journey.core.presentation.bodyLarge
import org.example.journey.core.presentation.displayMedium
import org.example.journey.core.presentation.labelMedium
import org.example.journey.core.presentation.onBackground
import org.example.journey.core.presentation.onTertiary
import org.example.journey.core.presentation.primary
import org.example.journey.core.presentation.tertiary
import org.example.journey.core.presentation.titleLarge
import org.example.journey.login.presentation.authButtonSpacing
import org.example.journey.login.presentation.authScreensWidth
import org.example.journey.login.presentation.components.AuthButtonPrimary
import org.example.journey.login.presentation.components.AuthButtonSecondary
import org.example.journey.login.presentation.components.FormInputBar
import org.jetbrains.compose.resources.stringResource

@Composable
fun SignUpEndScreen() {
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
            AppSummaryItem(
                title = stringResource(Res.string.share_title),
                body = stringResource(Res.string.share_body)
            )
            AppSummaryItem(
                title = stringResource(Res.string.connect_title),
                body = stringResource(Res.string.connect_body)
            )
            AppSummaryItem(
                title = stringResource(Res.string.discover_title),
                body = stringResource(Res.string.discover_body)
            )
            // button section
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
                verticalArrangement = Arrangement.spacedBy(authButtonSpacing)
            ) {
                AuthButtonPrimary(
                    text = stringResource(Res.string.create_account_label),
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

@Composable
fun AppSummaryItem(
    title: String,
    body: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(onBackground)
                        .size(10.dp)
                )
            }
            Text(
                text = title,
                color = onBackground,
                style = titleLarge
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp),
            )
            Text(
                text = body,
                color = onBackground,
                style = bodyLarge
            )
        }
    }
}