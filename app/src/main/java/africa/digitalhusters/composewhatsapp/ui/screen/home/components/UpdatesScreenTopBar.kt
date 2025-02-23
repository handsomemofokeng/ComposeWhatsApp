package africa.digitalhusters.composewhatsapp.ui.screen.home.components

import africa.digitalhusters.composewhatsapp.R
import africa.digitalhusters.composewhatsapp.ui.shared.components.ComposeWhatsAppTopAppBar
import africa.digitalhusters.composewhatsapp.ui.theme.ComposeWhatsAppTheme
import africa.digitalhusters.composewhatsapp.ui.theme.Dimensions
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun UpdatesScreenTopBar(
    onSearchClick: () -> Unit,
    onMenuClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ComposeWhatsAppTopAppBar(
        modifier = modifier,
        title = stringResource(R.string.updates_label),
        actionIcons = {
            Icon(
                painter = painterResource(R.drawable.icn_search),
                contentDescription = stringResource(R.string.search_icon_content_description),
                modifier = Modifier
                    .size(Dimensions.XLarge)
                    .clickable {
                        onSearchClick()
                    }
            )

            Spacer(Modifier.width(Dimensions.Large))

            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = stringResource(R.string.more_menu_items_content_description),
                modifier = Modifier
                    .size(Dimensions.XLarge)
                    .clickable {
                        onMenuClick()
                    }
            )
        }
    )
}

@Preview(showSystemUi = true)
@Composable
private fun UpdatesTopBarPreview() {
    ComposeWhatsAppTheme {
        UpdatesScreenTopBar(
            onSearchClick = {},
            onMenuClick = {}
        )
    }
}