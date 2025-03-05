package org.example.journey.app

import kotlinx.serialization.Serializable


interface Route {

    @Serializable
    data object AuthGraph: Route

    @Serializable
    data object AuthHome: Route

    @Serializable
    data object Login: Route

    @Serializable
    data object SignUp: Route

    @Serializable
    data object SelectProfilePicture: Route

    @Serializable
    data object AddUserBio: Route

    @Serializable
    data object PostSignUp: Route

    @Serializable
    data object InitialNavOptions: Route

    @Serializable
    data object MainGraph: Route

    @Serializable
    data object Home: Route

}