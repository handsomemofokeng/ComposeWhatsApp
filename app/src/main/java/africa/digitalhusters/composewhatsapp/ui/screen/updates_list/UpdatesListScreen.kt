package africa.digitalhusters.composewhatsapp.ui.screen.updates_list

import africa.digitalhusters.composewhatsapp.R
import africa.digitalhusters.composewhatsapp.data.generateRandomChats
import africa.digitalhusters.composewhatsapp.data.generateRandomImageUrl
import africa.digitalhusters.composewhatsapp.ui.shared.components.ChatItemView
import africa.digitalhusters.composewhatsapp.ui.shared.components.formatLocalDateTime
import africa.digitalhusters.composewhatsapp.ui.theme.ComposeWhatsAppTheme
import africa.digitalhusters.composewhatsapp.ui.theme.Dimensions
import africa.digitalhusters.composewhatsapp.ui.theme.LightGrey
import africa.digitalhusters.composewhatsapp.ui.theme.Typography
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun UpdatesListScreen(modifier: Modifier = Modifier) {
    UpdatesListScreenContent(modifier = modifier)
}

@Composable
fun UpdatesListScreenContent(modifier: Modifier = Modifier) {
    val randomStatusList = generateRandomChats(10).sortedBy { it.timestamp }.reversed()
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = Dimensions.Medium),
        verticalArrangement = Arrangement.spacedBy(Dimensions.Small)
    ) {
        item {
            Column {
                Text(
                    text = stringResource(R.string.status_label),
                    style = Typography.titleLarge,
                )

                Spacer(Modifier.height(Dimensions.Medium))

                ChatItemView(
                    title = stringResource(R.string.my_status_label),
                    subtitle = formatLocalDateTime(randomStatusList[0].timestamp),
                    profilePictureUrl = generateRandomImageUrl(),
                    statusCount = randomStatusList[0].statusCount,
                    viewedStatusCount = randomStatusList[0].viewedStatusCount
                )

                Text(
                    text = stringResource(R.string.recent_updates_label),
                    style = Typography.labelLarge,
                    color = LightGrey,
                    modifier = Modifier.padding(vertical = Dimensions.Medium)
                )
            }
        }

        items(randomStatusList) { statusItem ->
            ChatItemView(
                title = statusItem.contactName,
                profilePictureUrl = statusItem.profilePictureUrl.orEmpty(),
                subtitle = formatLocalDateTime(statusItem.timestamp),
                viewedStatusCount = statusItem.viewedStatusCount,
                statusCount = statusItem.statusCount
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun UpdatesListScreenPreview() {
    ComposeWhatsAppTheme {
        UpdatesListScreenContent(modifier = Modifier.padding(top = Dimensions.XXXLarge))
    }
}