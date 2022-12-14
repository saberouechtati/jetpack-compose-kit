package com.streamamg.jetpack.compose.toolkit.composable.forms


import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.streamamg.jetpack.compose.toolkit.composable.buttons.SimpleButton
import com.streamamg.jetpack.compose.toolkit.composable.buttons.SimpleButtonConfig
import com.streamamg.jetpack.compose.toolkit.composable.fields.OutlinedEmailField
import com.streamamg.jetpack.compose.toolkit.composable.fields.OutlinedPasswordField
import com.streamamg.jetpack.compose.toolkit.composable.modifiers.MARGIN_MEDIUM
import com.streamamg.jetpack.compose.toolkit.composable.modifiers.Margins
import com.streamamg.jetpack.compose.toolkit.composable.modifiers.innerConstrainModifier
import com.streamamg.jetpack.compose.toolkit.composable.modifiers.underConstrainModifier

@Composable
fun SignInForm(
    signInButtonConfig: SimpleButtonConfig,
    onSignUpLinkClicked: () -> Unit,
    onSignInClicked: () -> Unit
) {
    ConstraintLayout {
        val (logo, emailField, passwordField, signInButton, signUpLink) = createRefs()

        val logoModifier = logo.innerConstrainModifier(
            scope = this,
            margins = Margins(top = 100, startEnd = MARGIN_MEDIUM)
        )

        val emailFieldModifier = emailField.underConstrainModifier(
            underReference = logo,
            scope = this,
            margins = Margins(start = MARGIN_MEDIUM, top = MARGIN_MEDIUM, end = MARGIN_MEDIUM)
        )

        val passwordFieldModifier = passwordField.underConstrainModifier(
            underReference = emailField,
            scope = this,
            margins = Margins(start = MARGIN_MEDIUM, top = MARGIN_MEDIUM, end = MARGIN_MEDIUM)
        )

        val signInButtonModifier = signInButton.underConstrainModifier(
            underReference = passwordField,
            scope = this,
            margins = Margins(start = MARGIN_MEDIUM, top = MARGIN_MEDIUM, end = MARGIN_MEDIUM)
        )

        val signUpLinkModifier = signUpLink.underConstrainModifier(
            underReference = signInButton,
            scope = this,
            margins = Margins(start = MARGIN_MEDIUM, top = MARGIN_MEDIUM, end = MARGIN_MEDIUM)
        )

        val focusManager = LocalFocusManager.current
        Text(text = "Sign In", modifier = logoModifier, fontSize = 22.sp, color = Color(0xFFBB86FC))
        OutlinedEmailField(label = "Email", modifier = emailFieldModifier, focusManager)
        OutlinedPasswordField(label = "Password", modifier = passwordFieldModifier, focusManager)

        ClickableText(
            text = AnnotatedString("No account? Sign Up"),
            onClick = {
                onSignUpLinkClicked()
            },
            modifier = signUpLinkModifier,
            style = TextStyle.Default.copy(color = Color(0xFFBB86FC), fontSize = 14.sp)
        )

        SimpleButton(
            signInButtonConfig,
            modifier = signInButtonModifier.height(50.dp)
        ) {
            onSignInClicked()
        }
    }
}