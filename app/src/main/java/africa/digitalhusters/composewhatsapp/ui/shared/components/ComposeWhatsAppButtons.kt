package africa.digitalhusters.composewhatsapp.ui.shared.components

import africa.digitalhusters.composewhatsapp.ui.theme.ComposeWhatsAppTheme
import africa.digitalhusters.composewhatsapp.ui.theme.DarkGrey
import africa.digitalhusters.composewhatsapp.ui.theme.Dimensions
import africa.digitalhusters.composewhatsapp.ui.theme.Green
import africa.digitalhusters.composewhatsapp.ui.theme.PaleGrey
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textColor: Color = DarkGrey,
    buttonColor: Color = Green,
) {
    Button(
        modifier = modifier, onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = textColor
        )
    ) {
        Text(text = text)
    }
}

@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textColor: Color = Green,
) {
    OutlinedButton(
        modifier = modifier, onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = textColor
        ),
        border = BorderStroke(width = 1.dp, color = PaleGrey)

    ) {
        Text(text = text)
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ButtonsPreview() {
    ComposeWhatsAppTheme {
        Scaffold(modifier = Modifier.padding(Dimensions.Medium)) {
            Column(Modifier.padding(it)) {
                PrimaryButton(
                    text = "Primary Button",
                    onClick = {}
                )

                Spacer(Modifier.height(Dimensions.Medium))

                SecondaryButton(text = "Secondary Button", onClick = {})
            }
        }
    }
}