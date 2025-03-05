package org.example.journey.login.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import org.example.journey.core.presentation.squareButtonCornersDp

@Composable
fun AuthButton(
    color: Color,
    contentColor: Color,
    text: String,
    onClick: () -> Unit
) {
    Surface(
        color = color,
        contentColor = contentColor,
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
                color = contentColor,
                style = labelLarge
            )
        }
    }
}