package africa.digitalhusters.composewhatsapp.ui.screen.calls

import africa.digitalhusters.composewhatsapp.ui.screen.home.components.TitleText
import africa.digitalhusters.composewhatsapp.ui.shared.components.ChatItemView
import africa.digitalhusters.composewhatsapp.ui.theme.Dimensions
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CallsScreen(
    modifier: Modifier = Modifier
) {
    CallsScreenContent(
        modifier = modifier,
    )
}

@Composable
fun CallsScreenContent(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = Dimensions.Medium)
    ) {
        item {
            Column {
                TitleText(text = "Favourites")

                ChatItemView(
                    title = "Add Favourite",
                    subtitle = "",
                    statusCount = 0,
                    viewedStatusCount = 0,

                    )
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun CallsScreenPreview() {
    CallsScreenContent(modifier = Modifier.padding(top = Dimensions.XXXLarge))
}