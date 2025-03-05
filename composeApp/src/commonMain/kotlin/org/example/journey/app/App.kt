package org.example.journey.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import org.example.journey.core.presentation.background
import org.example.journey.login.presentation.screens.AuthHomeScreen
import org.example.journey.login.presentation.screens.LoginScreen
import org.example.journey.login.presentation.screens.SignUpScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
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
                            navController.navigate(Route.SignUp)
                        }
                    )
                }
                composable<Route.Login>(

                ) {
                    LoginScreen()
                }
                composable<Route.SignUp>(

                ) {
                    SignUpScreen()
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