package org.example.journey.login.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.journey.core.presentation.labelLarge
import org.example.journey.core.presentation.onPrimary
import org.example.journey.core.presentation.onTertiary
import org.example.journey.core.presentation.primary
import org.example.journey.core.presentation.squareButtonCornersDp
import org.example.journey.core.presentation.tertiary

@Composable
fun PostRegisterNavButton(
    clicked: Boolean,
    text: String,
    onClick: () -> Unit
) {
    Surface(
        color = if (clicked) primary else tertiary,
        modifier = Modifier
            .height(58.dp)
            .widthIn(min = 300.dp),
        shape = RoundedCornerShape(squareButtonCornersDp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(onClick = onClick),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = if (clicked) onPrimary else onTertiary,
                style = labelLarge
            )
        }
    }
}