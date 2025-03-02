package africa.digitalhusters.composewhatsapp.ui.shared.components

import africa.digitalhusters.composewhatsapp.ui.theme.ComposeWhatsAppTheme
import africa.digitalhusters.composewhatsapp.ui.theme.Dimensions
import africa.digitalhusters.composewhatsapp.ui.theme.Green
import africa.digitalhusters.composewhatsapp.ui.theme.LightGrey
import africa.digitalhusters.composewhatsapp.ui.theme.TealGreenDark
import africa.digitalhusters.composewhatsapp.ui.theme.Typography
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TextFilter(
    text: String,
    isSelected: Boolean = false,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .clip(shape = RoundedCornerShape(percent = 100))
            .clickable {
                onClick()
            },
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) TealGreenDark
            else CardDefaults.cardColors().containerColor
        )
    ) {
        Text(
            text = text,
            color = if (isSelected) Green else LightGrey,
            style = Typography.labelSmall,
            modifier = Modifier.padding(
                vertical = Dimensions.Small,
                horizontal = Dimensions.Medium
            )
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TextFilterPreview() {
    ComposeWhatsAppTheme {
        Row(modifier = Modifier.padding(Dimensions.Large)) {
            TextFilter(text = "All", isSelected = true, onClick = {})
            Spacer(Modifier.width(Dimensions.Small))
            TextFilter(text = "Favourites", onClick = {})
        }
    }
}