package org.example.journey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.example.journey.app.App
import org.example.journey.core.presentation.background
import org.example.journey.login.presentation.screens.AddBioScreen
import org.example.journey.login.presentation.screens.SelectProfilePictureScreen
import org.example.journey.login.presentation.screens.SignUpEndNavScreen
import org.example.journey.login.presentation.screens.SignUpEndScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
    ) {
        // SelectProfilePictureScreen({}, {})
    }
}