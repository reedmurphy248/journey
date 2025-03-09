package org.example.journey.login.presentation.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import journey.composeapp.generated.resources.Res
import journey.composeapp.generated.resources.email_not_unique
import journey.composeapp.generated.resources.invalid_email
import journey.composeapp.generated.resources.invalid_password
import journey.composeapp.generated.resources.invalid_phone_number
import journey.composeapp.generated.resources.invalid_username
import journey.composeapp.generated.resources.phone_number_not_unique
import journey.composeapp.generated.resources.username_not_unique
import org.example.journey.core.presentation.bodyLarge
import org.example.journey.core.presentation.bodySmall
import org.example.journey.core.presentation.errorRed
import org.example.journey.core.presentation.formFieldCornersDp
import org.example.journey.core.presentation.onTertiary
import org.example.journey.core.presentation.tertiary
import org.example.journey.login.presentation.FormValidationError
import org.jetbrains.compose.resources.stringResource

@Composable
fun FormValidatorMapper(formatError: FormValidationError): String {
    when (formatError) {
        FormValidationError.InvalidUsername -> return stringResource(
            Res.string.invalid_username
        )
        FormValidationError.UsernameNotUnique -> return stringResource(
            Res.string.username_not_unique
        )
        FormValidationError.InvalidPassword -> return stringResource(
            Res.string.invalid_password
        )
        FormValidationError.InvalidEmail -> return stringResource(
            Res.string.invalid_email
        )
        FormValidationError.EmailNotUnique -> return stringResource(
            Res.string.email_not_unique
        )
        FormValidationError.InvalidPhoneNumber -> return stringResource(
            Res.string.invalid_phone_number
        )
        FormValidationError.PhoneNumberNotUnique -> return stringResource(
            Res.string.phone_number_not_unique
        )
    }
}

@Composable
fun FormInputBar(
    leadingIcon: ImageVector,
    value: String,
    placeholder: String,
    isPassword: Boolean = false,
    onValueChange: (String) -> Unit,
    error: FormValidationError? = null
) {
    if (isPassword) {
        FormInputBarPassword(
            leadingIcon = leadingIcon,
            value = value,
            placeholder = placeholder,
            onValueChange = onValueChange,
            error = error != null
        )
    } else {
        FormInputBar(
            leadingIcon = leadingIcon,
            value = value,
            placeholder = placeholder,
            onValueChange = onValueChange,
            error = error != null
        )
    }
    if (error != null) {
        Text(text = FormValidatorMapper(error), color = errorRed, style = bodySmall)
    }
}

@Composable
fun FormInputBar(
    modifier: Modifier = Modifier
        .height(52.dp)
        .fillMaxWidth(),
    shape: RoundedCornerShape = RoundedCornerShape(formFieldCornersDp),
    textStyle: TextStyle = bodyLarge,
    singleLine: Boolean = true,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        textColor = onTertiary,
        backgroundColor = tertiary,
        errorIndicatorColor = errorRed,
        errorCursorColor = errorRed,
        errorLabelColor = errorRed,
        errorLeadingIconColor = errorRed,
        errorTrailingIconColor = errorRed
    ),
    leadingIcon: ImageVector,
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    error: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon: @Composable() (() -> Unit)? = null
) {
    TextField(
        modifier = modifier,
        shape = shape,
        value = value,
        textStyle = textStyle,
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
        singleLine = singleLine,
        colors = colors,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        trailingIcon = trailingIcon,
        isError = error
    )
}

@Composable
fun FormInputBarPassword(
    leadingIcon: ImageVector,
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    error: Boolean = false
) {
    var passwordVisible by remember { mutableStateOf(false) }

    FormInputBar(
        leadingIcon = leadingIcon,
        value = value,
        placeholder = placeholder,
        onValueChange = onValueChange,
        error = error,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (passwordVisible)
                Icons.Outlined.Visibility
            else Icons.Outlined.VisibilityOff

            // Localized description for accessibility services
            val description = if (passwordVisible) "Hide password" else "Show password"

            // Toggle button to hide or display password
            IconButton(onClick = {passwordVisible = !passwordVisible}){
                Icon(
                    imageVector = image,
                    contentDescription = description,
                    tint = onTertiary
                )
            }
        }
    )
}