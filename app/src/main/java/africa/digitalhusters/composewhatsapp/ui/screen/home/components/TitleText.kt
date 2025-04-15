package africa.digitalhusters.composewhatsapp.ui.screen.home.components

import africa.digitalhusters.composewhatsapp.ui.theme.Typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight


@Composable
fun TitleText(
    text: String,
) {
    Text(
        text = text,
        style = Typography.titleLarge,
        fontWeight = FontWeight.Medium
    )
}
