package org.example.journey.login.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.example.journey.core.presentation.onPrimary
import org.example.journey.core.presentation.primary

@Composable
fun AuthButtonPrimary(
    text: String,
    onClick: () -> Unit
) {
    AuthButton(
        color = primary,
        contentColor = onPrimary,
        text = text,
        onClick = onClick
    )
}