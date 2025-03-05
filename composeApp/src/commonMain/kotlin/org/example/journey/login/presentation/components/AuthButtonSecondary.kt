package org.example.journey.login.presentation.components

import androidx.compose.runtime.Composable
import org.example.journey.core.presentation.onSecondary
import org.example.journey.core.presentation.secondary

@Composable
fun AuthButtonSecondary(
    text: String,
    onClick: () -> Unit
) {
    AuthButton(
        color = secondary,
        contentColor = onSecondary,
        text = text,
        onClick = onClick
    )
}