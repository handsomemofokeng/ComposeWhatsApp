package africa.digitalhusters.composewhatsapp.ui.screen.updates_list

import africa.digitalhusters.composewhatsapp.R
import africa.digitalhusters.composewhatsapp.data.generateRandomChats
import africa.digitalhusters.composewhatsapp.ui.screen.home.components.TitleText
import africa.digitalhusters.composewhatsapp.ui.shared.components.ChatItemView
import africa.digitalhusters.composewhatsapp.ui.shared.components.CollapsingText
import africa.digitalhusters.composewhatsapp.ui.shared.components.PrimaryButton
import africa.digitalhusters.composewhatsapp.ui.shared.components.SecondaryButton
import africa.digitalhusters.composewhatsapp.ui.shared.components.formatLocalDateTime
import africa.digitalhusters.composewhatsapp.ui.theme.ComposeWhatsAppTheme
import africa.digitalhusters.composewhatsapp.ui.theme.Dimensions
import africa.digitalhusters.composewhatsapp.ui.theme.Green
import africa.digitalhusters.composewhatsapp.ui.theme.LightGrey
import africa.digitalhusters.composewhatsapp.ui.theme.TealGreen
import africa.digitalhusters.composewhatsapp.ui.theme.Typography
import africa.digitalhusters.composewhatsapp.ui.theme.White
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun UpdatesListScreen(modifier: Modifier = Modifier) {
    UpdatesListScreenContent(modifier = modifier)
}

val randomStatusList = generateRandomChats(5).sortedBy { it.timestamp }.reversed()
val randomChannelList = generateRandomChats(10)

@Composable
fun UpdatesListScreenContent(modifier: Modifier = Modifier) {
    val hasUnseenUpdates = remember { mutableStateOf(true) }
    val isViewedUpdatesCollapsed = remember { mutableStateOf(false) }
    val isMutedUpdatesCollapsed = remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = Dimensions.Medium),
    ) {
        item {
            Column {
                TitleText(text = stringResource(R.string.status_label))

                Spacer(Modifier.height(Dimensions.Medium))

                ChatItemView(
                    title = stringResource(R.string.my_status_label),
                    subtitle = if (randomStatusList[0].statusCount > 0) {
                        formatLocalDateTime(randomStatusList[0].timestamp)
                    } else {
                        stringResource(R.string.tap_to_add_status_update_label)
                    },
                    profilePictureUrl = randomStatusList[0].profilePictureUrl.orEmpty(),
                    isShowAddStatusIcon = randomStatusList[0].statusCount == 0,
                    statusCount = randomStatusList[0].statusCount,
                    viewedStatusCount = randomStatusList[0].unseenStatusCount,
                    modifier = Modifier.clickable {
                        hasUnseenUpdates.value = !hasUnseenUpdates.value
                    }
                )
                AnimatedVisibility(visible = hasUnseenUpdates.value) {
                    Text(
                        text = stringResource(R.string.recent_updates_label),
                        style = Typography.labelLarge,
                        color = LightGrey,
                        modifier = Modifier.padding(top = Dimensions.Small)
                    )
                }
            }
        }
        items(randomStatusList.filter { it.unseenStatusCount > 2 }) { unseenStatusItem ->
            AnimatedVisibility(hasUnseenUpdates.value) {
                ChatItemView(
                    title = unseenStatusItem.contactName,
                    profilePictureUrl = unseenStatusItem.profilePictureUrl.orEmpty(),
                    subtitle = formatLocalDateTime(unseenStatusItem.timestamp),
                    viewedStatusCount = unseenStatusItem.unseenStatusCount,
                    statusCount = unseenStatusItem.statusCount,
                    modifier = Modifier.padding(top = Dimensions.Small)
                )
            }
        }

        item {
            CollapsingText(
                title = stringResource(R.string.viewed_updates_label),
                isCollapsed = isViewedUpdatesCollapsed.value,
                onCollapseClick = {
                    isViewedUpdatesCollapsed.value = !isViewedUpdatesCollapsed.value
                },
            )
        }

        items(randomStatusList.filter { it.unseenStatusCount <= 2 }) { statusItem ->
            AnimatedVisibility(isViewedUpdatesCollapsed.value) {
                ChatItemView(
                    title = statusItem.contactName,
                    profilePictureUrl = statusItem.profilePictureUrl.orEmpty(),
                    subtitle = formatLocalDateTime(statusItem.timestamp),
                    viewedStatusCount = statusItem.unseenStatusCount,
                    statusCount = statusItem.statusCount,
                    modifier = Modifier.padding(top = Dimensions.Small)
                )
            }
        }

        item {
            CollapsingText(
                title = stringResource(R.string.muted_updates_label),
                isCollapsed = isMutedUpdatesCollapsed.value,
                onCollapseClick = {
                    isMutedUpdatesCollapsed.value = !isMutedUpdatesCollapsed.value
                },
            )
        }

        items(randomStatusList.shuffled().take(3)) { statusItem ->
            AnimatedVisibility(isMutedUpdatesCollapsed.value) {
                ChatItemView(
                    title = statusItem.contactName,
                    profilePictureUrl = statusItem.profilePictureUrl.orEmpty(),
                    subtitle = formatLocalDateTime(statusItem.timestamp),
                    viewedStatusCount = statusItem.unseenStatusCount,
                    statusCount = statusItem.statusCount,
                    modifier = Modifier.padding(top = Dimensions.Small)
                )
            }
        }

        item {
            Column {
                HorizontalDivider(modifier = Modifier.padding(top = Dimensions.Medium))

                Spacer(Modifier.height(Dimensions.Large))

                TitleText(text = stringResource(R.string.channels_label))

                Spacer(Modifier.height(Dimensions.Medium))

                Text(
                    text = stringResource(R.string.channels_subtitle),
                    style = Typography.bodyMedium,
                    color = LightGrey
                )
            }
        }

        items(randomChannelList.filter { it.isGroupChat }.take(4)) { channel ->
            Row(Modifier.fillMaxWidth()) {
                ChatItemView(
                    title = channel.contactName,
                    subtitle = "${channel.followersCount}  followers",
                    profilePictureUrl = channel.profilePictureUrl.orEmpty(),
                    statusCount = 0,
                    isGroup = true,
                    viewedStatusCount = 0,
                    modifier = Modifier.weight(1f)
                )

                PrimaryButton(
                    text = stringResource(R.string.follow_label),
                    textColor = White,
                    buttonColor = TealGreen,
                    onClick = {}
                )
            }
        }

        item {
            Column {
                SecondaryButton(
                    text = stringResource(R.string.explore_more_label),
                    textColor = Green,
                    modifier = Modifier.padding(vertical = Dimensions.Small),
                    onClick = {},
                )
                Spacer(Modifier.height(Dimensions.XXXLarge))
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun UpdatesListScreenPreview() {
    ComposeWhatsAppTheme {
        Scaffold { contentPadding ->
            UpdatesListScreenContent(modifier = Modifier.padding(contentPadding))
        }
    }
}