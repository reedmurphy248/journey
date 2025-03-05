package org.example.journey.login.presentation.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import journey.composeapp.generated.resources.Res
import journey.composeapp.generated.resources.back_button_label
import journey.composeapp.generated.resources.continue_button_label
import journey.composeapp.generated.resources.journey
import journey.composeapp.generated.resources.select_a_profile_picture_body
import journey.composeapp.generated.resources.select_a_profile_picture_title
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
import org.jetbrains.compose.resources.stringResource

@Composable
fun SelectProfilePictureScreen() {
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
                    text = stringResource(Res.string.select_a_profile_picture_title),
                    color = onBackground,
                    style = titleLarge
                )
                Text(
                    text = stringResource(Res.string.select_a_profile_picture_body),
                    color = onBackground,
                    style = bodyLarge
                )
            }
            // photo selector
            Box(
                modifier = Modifier
                    .heightIn(min = 210.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Box(
                    contentAlignment = Alignment.BottomStart
                ) {
                    Box(
                        modifier = Modifier
                            .padding(6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(tertiary)
                                .border(1.dp, onTertiary, CircleShape)
                                .height(200.dp)
                                .width(200.dp)
                        ) {

                        }
                    }
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(tertiary)
                            .border(1.dp, onTertiary, CircleShape)
                            .height(80.dp)
                            .width(80.dp)
                    ) {

                    }
                }
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
}