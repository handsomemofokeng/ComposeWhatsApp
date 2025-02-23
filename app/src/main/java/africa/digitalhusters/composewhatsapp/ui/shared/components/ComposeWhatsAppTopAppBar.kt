package africa.digitalhusters.composewhatsapp.ui.shared.components

import africa.digitalhusters.composewhatsapp.ui.theme.Typography
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposeWhatsAppTopAppBar(
    title: String,
    isBold: Boolean = false,
    actionIcons: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = Typography.titleLarge,
                fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal
            )
        },
        actions = { actionIcons() },
        modifier = modifier
    )
}

@Preview(showSystemUi = true)
@Composable
private fun TopAppBarPreview() {
    ComposeWhatsAppTopAppBar(
        title = "Compose WhatsApp",
        isBold = true,
        actionIcons = {}
    )
}