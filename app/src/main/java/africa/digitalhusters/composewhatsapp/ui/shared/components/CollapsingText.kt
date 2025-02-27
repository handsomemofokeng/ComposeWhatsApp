package africa.digitalhusters.composewhatsapp.ui.shared.components

import africa.digitalhusters.composewhatsapp.R
import africa.digitalhusters.composewhatsapp.ui.theme.ComposeWhatsAppTheme
import africa.digitalhusters.composewhatsapp.ui.theme.Dimensions
import africa.digitalhusters.composewhatsapp.ui.theme.LightGrey
import africa.digitalhusters.composewhatsapp.ui.theme.Typography
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CollapsingText(
    title: String,
    isCollapsed: Boolean,
    onCollapseClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onCollapseClick()
            },
    ) {
        Text(
            text = title,
            style = Typography.labelLarge,
            color = LightGrey,
            modifier = Modifier
                .padding(vertical = Dimensions.Small)
                .weight(1f)
        )

        Icon(
            tint = LightGrey,
            contentDescription = stringResource(R.string.collapse_icon_content_description),
            imageVector = if (isCollapsed) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun CollapsingTextPreview() {
    val isCollapsed = remember { mutableStateOf(true) }
    ComposeWhatsAppTheme {
        Scaffold {
            CollapsingText(
                modifier = Modifier.padding(it),
                title = "Viewed updates",
                isCollapsed = isCollapsed.value,
                onCollapseClick = {
                    isCollapsed.value = !isCollapsed.value
                }
            )
        }
    }
}