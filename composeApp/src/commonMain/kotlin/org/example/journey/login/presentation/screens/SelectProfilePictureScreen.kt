package org.example.journey.login.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.rememberAsyncImagePainter
import dev.icerock.moko.permissions.PermissionState
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import journey.composeapp.generated.resources.Res
import journey.composeapp.generated.resources.back_button_label
import journey.composeapp.generated.resources.continue_button_label
import journey.composeapp.generated.resources.journey
import journey.composeapp.generated.resources.select_a_profile_picture_body
import journey.composeapp.generated.resources.select_a_profile_picture_title
import journey.composeapp.generated.resources.skip_for_now_button_label
import org.example.journey.app.PermissionsViewModel
import org.example.journey.app.rememberGalleryManager
import org.example.journey.core.presentation.bodyLarge
import org.example.journey.core.presentation.displayMedium
import org.example.journey.core.presentation.labelMedium
import org.example.journey.core.presentation.onBackground
import org.example.journey.core.presentation.onTertiary
import org.example.journey.core.presentation.onTertiaryContainer
import org.example.journey.core.presentation.primary
import org.example.journey.core.presentation.tertiary
import org.example.journey.core.presentation.tertiaryContainer
import org.example.journey.core.presentation.titleLarge
import org.example.journey.login.presentation.CurrentScreen
import org.example.journey.login.presentation.SignUpAction
import org.example.journey.login.presentation.SignUpState
import org.example.journey.login.presentation.SignUpViewModel
import org.example.journey.login.presentation.authButtonSpacing
import org.example.journey.login.presentation.authScreensWidth
import org.example.journey.login.presentation.components.AuthButtonPrimary
import org.example.journey.login.presentation.components.AuthButtonSecondary
import org.jetbrains.compose.resources.stringResource

@Composable
fun SelectProfilePictureScreenRoot(
    signUpViewModel: SignUpViewModel,
    permissionsViewModel: PermissionsViewModel,
    onContinueClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val signUpState by signUpViewModel.state.collectAsStateWithLifecycle()
    val permissionsState by permissionsViewModel.state.collectAsStateWithLifecycle()

    val gallery = rememberGalleryManager(
        onResult = {
            signUpViewModel.onAction(SignUpAction.ProfilePictureChanged(it?.uri.toString()))
        }
    )

//    LaunchedEffect(permissionsState) {
//        if (permissionsState == PermissionState.Granted) {
//            gallery.launch()
//        }
//    }

    SelectProfilePictureScreen(
        state = signUpState,
        onAction = { action ->
            when (action) {
                SignUpAction.BackButtonPressed -> {
                    // signUpViewModel.onAction(action)
                    onBackClick()
                }
                SignUpAction.ChangeProfilePictureButtonPressed -> {
                    // How does the flow work if the permission pop up happens?
                    if (permissionsState != PermissionState.Granted) {
                        permissionsViewModel.provideOrRequestGalleryPermission()
                    }

                    if (permissionsState == PermissionState.Granted) {
                        gallery.launch()
                    }
                }
                is SignUpAction.ContinueButtonPressed -> {
                    // signUpViewModel.onAction(action)
                    onContinueClick()
                }
                else -> signUpViewModel.onAction(action)
            }
        }
    )
}

@Composable
fun SelectProfilePictureScreen(
    state: SignUpState,
    onAction: (SignUpAction) -> Unit,
) {

    var imageLoadResult by remember {
        mutableStateOf<Result<Painter>?>(null)
    }

    val painter = rememberAsyncImagePainter(
        model = state.profilePicture,
        onSuccess = {
            val size = it.painter.intrinsicSize
            imageLoadResult = if(size.width > 1 && size.height > 1) {
                Result.success(it.painter)
            } else {
                Result.failure(Exception("Invalid image dimensions"))
            }
        },
        onError = {
            it.result.throwable.printStackTrace()
        }
    )

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
                                .width(200.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            if (imageLoadResult != null) {
                                Image(
                                    painter = painter,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color.Transparent),
                                    contentScale = if (imageLoadResult != null) {
                                        ContentScale.Crop
                                    } else {
                                        ContentScale.Fit
                                    }
                                )
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(tertiaryContainer)
                            .border(1.dp, onTertiaryContainer, CircleShape)
                            .height(70.dp)
                            .width(70.dp)
                            .clickable(
                                onClick = {
                                    onAction(SignUpAction.ChangeProfilePictureButtonPressed)
                                }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Add,
                            contentDescription = null,
                            tint = onTertiaryContainer,
                            modifier = Modifier.size(30.dp)
                        )
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
                        onClick = {
                            onAction(SignUpAction.ContinueButtonPressed(CurrentScreen.SelectProfilePicture))
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