package org.example.journey.login.presentation.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import org.example.journey.core.presentation.bodyLarge
import org.example.journey.core.presentation.formFieldCornersDp
import org.example.journey.core.presentation.onTertiary
import org.example.journey.core.presentation.tertiary

@Composable
fun FormInputBar(
    leadingIcon: ImageVector,
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        modifier = Modifier
            .height(52.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(formFieldCornersDp),
        value = value,
        textStyle = bodyLarge,
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                tint = onTertiary
            )
        },
        placeholder = {
            Text(text = placeholder, color = onTertiary, style = bodyLarge)
        },
        onValueChange = onValueChange,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            textColor = onTertiary,
            backgroundColor = tertiary
        )
    )
}