package org.example.journey.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import org.example.journey.core.presentation.background
import org.example.journey.login.presentation.LoginViewModel
import org.example.journey.login.presentation.SignUpViewModel
import org.example.journey.login.presentation.screens.AddBioScreenRoot
import org.example.journey.login.presentation.screens.AuthHomeScreen
import org.example.journey.login.presentation.screens.LoginScreenRoot
import org.example.journey.login.presentation.screens.SelectProfilePictureScreen
import org.example.journey.login.presentation.screens.SelectProfilePictureScreenRoot
import org.example.journey.login.presentation.screens.SignUpEndNavScreen
import org.example.journey.login.presentation.screens.SignUpEndScreen
import org.example.journey.login.presentation.screens.SignUpScreenRoot
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    val factory = rememberPermissionsControllerFactory()
    val controller = remember(factory) {
        factory.createPermissionsController()
    }

    BindEffect(controller)

    val permissionsViewModel = viewModel {
        PermissionsViewModel(controller)
    }

    val signUpViewModel = SignUpViewModel()

    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.AuthGraph,
            modifier = Modifier
                .fillMaxSize()
                .background(background)
        ) {
            navigation<Route.AuthGraph>(
                startDestination = Route.AuthHome
            ) {
                composable<Route.AuthHome>(

                ) {
                    AuthHomeScreen(
                        onLoginClick = {
                            navController.navigate(Route.Login)
                        },
                        onSignUpClick = {
                            navController.navigate(Route.SignUpGraph)
                        }
                    )
                }
                composable<Route.Login>(

                ) {
                    val viewModel = remember { LoginViewModel() }
                    LoginScreenRoot(
                        viewModel = viewModel,
                        onBackClick = {
                            navController.popBackStack()
                        },
                        onLoginSuccess = {
                            navController.navigate(Route.MainGraph)
                        },
                        onLoginFailure = {
                            // TODO: show error
                        }
                    )
                }
                navigation<Route.SignUpGraph>(
                    startDestination = Route.SignUp
                ) {
                    composable<Route.SignUp>(

                    ) {
                        SignUpScreenRoot(
                            signUpViewModel,
                            onContinueClick = {
                                navController.navigate(Route.SelectProfilePicture)
                            },
                            onBackClick = {
                                navController.popBackStack()
                            }
                        )
                    }
                    composable<Route.SelectProfilePicture> {
                        SelectProfilePictureScreenRoot(
                            signUpViewModel = signUpViewModel,
                            permissionsViewModel = permissionsViewModel,
                            onContinueClick = {
                                navController.navigate(Route.AddUserBio)
                            },
                            onBackClick = {
                                navController.popBackStack()
                            }
                        )
                    }
                    composable<Route.AddUserBio> {
                        AddBioScreenRoot(
                            viewModel = signUpViewModel,
                            onContinueClick = {
                                navController.navigate(Route.PostSignUp)
                            },
                            onBackClick = {
                                navController.popBackStack()
                            }
                        )
                    }
                    composable<Route.PostSignUp> {
                        SignUpEndScreen()
                    }
                    composable<Route.InitialNavOptions> {
                        SignUpEndNavScreen()
                    }
                }
            }
            navigation<Route.MainGraph>(
                startDestination = Route.Home
            ) {
                composable<Route.Home>(

                ) {

                }
            }
        }
    }
}

@Composable
private inline fun <reified T: ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}