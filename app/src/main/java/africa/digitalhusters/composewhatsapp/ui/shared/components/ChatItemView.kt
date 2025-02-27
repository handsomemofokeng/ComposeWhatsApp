package africa.digitalhusters.composewhatsapp.ui.shared.components

import africa.digitalhusters.composewhatsapp.data.generateRandomChats
import africa.digitalhusters.composewhatsapp.ui.theme.ComposeWhatsAppTheme
import africa.digitalhusters.composewhatsapp.ui.theme.DarkGrey
import africa.digitalhusters.composewhatsapp.ui.theme.Dimensions
import africa.digitalhusters.composewhatsapp.ui.theme.Green
import africa.digitalhusters.composewhatsapp.ui.theme.LightGrey
import africa.digitalhusters.composewhatsapp.ui.theme.Typography
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.Locale.getDefault

@Composable
fun ChatItemView(
    title: String,
    subtitle: String,
    statusCount: Int,
    viewedStatusCount: Int,
    modifier: Modifier = Modifier,
    timestamp: String = "",
    profilePictureUrl: String = "",
    unreadMessageCount: Int = 0,
    isGroup: Boolean = false,
) {

    Row(modifier = modifier.padding(vertical = Dimensions.Small)) {
        SegmentedCircularProfilePicture(
            profilePictureUrl = profilePictureUrl,
            progress = viewedStatusCount * 0.1f,
            segments = statusCount,
            isGroup = isGroup,
            modifier = Modifier.size(Dimensions.ProfilePictureSize),
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = Dimensions.Small)
        ) {
            val hasUnreadMessages = unreadMessageCount > 0
            Row {
                Text(
                    text = title,
                    style = Typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 2.dp)
                )
                Text(
                    text = timestamp,
                    style = Typography.bodySmall,
                    fontWeight = if (hasUnreadMessages) FontWeight.Bold else FontWeight.Normal,
                    color = if (hasUnreadMessages) Green else LightGrey,
                    modifier = Modifier
                        .padding(
                            top = 2.dp,
                            start = Dimensions.Small
                        )
                        .align(alignment = Alignment.CenterVertically)
                )
            }

            Row {
                Text(
                    text = subtitle,
                    style = Typography.bodyMedium,
                    color = LightGrey,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .weight(1f)
                        .padding(
                            top = Dimensions.XSmall,
                            end = Dimensions.XSmall
                        )
                )
                if (hasUnreadMessages) {
                    Text(
                        text = unreadMessageCount.toString(),
                        style = Typography.bodySmall,
                        fontWeight = FontWeight.Medium,
                        color = DarkGrey,
                        modifier = Modifier
                            .background(
                                color = Green,
                                shape = CircleShape
                            )
                            .padding(
                                vertical = Dimensions.XSmall,
                                horizontal = Dimensions.Small
                            )
                    )
                }
            }
        }
    }
}

fun formatLocalDateTime(
    dateTime: LocalDateTime,
    locale: Locale = getDefault(),
): String {
    val now = LocalDateTime.now()
    val today = now.toLocalDate()
    val date = dateTime.toLocalDate()

    return when {
        date == today -> {
            val formatter =
                DateTimeFormatter.ofPattern("HH:mm", locale) // Time only (24-hour format)
            dateTime.format(formatter)
        }

        date.isEqual(today.minusDays(1)) -> "Yesterday"
        date.isBefore(today.minusDays(1)) -> {
            val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd", locale) // YYYY/MM/DD format
            dateTime.format(formatter)
        }

        else -> {
            // Handle future dates if needed.  For this requirement, we'll just use YYYY/MM/DD
            val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd", locale)
            dateTime.format(formatter)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ChatItemPreview() {
    ComposeWhatsAppTheme {
        Column(modifier = Modifier.padding(vertical = Dimensions.XXLarge, horizontal = Dimensions.Small)) {
            val randomContact1 = generateRandomChats(1)[0]
            val randomContact2 = generateRandomChats(1)[0]
            ChatItemView(
                title = randomContact1.contactName,
                subtitle = randomContact1.lastMessage,
                statusCount = randomContact1.statusCount,
                timestamp = formatLocalDateTime(randomContact1.timestamp),
                profilePictureUrl = randomContact1.profilePictureUrl.orEmpty(),
                unreadMessageCount = randomContact1.unreadMessageCount,
                viewedStatusCount = randomContact1.viewedStatusCount
            )
            ChatItemView(
                title = randomContact2.contactName,
                subtitle = formatLocalDateTime(randomContact2.timestamp),
                statusCount = randomContact2.statusCount,
                viewedStatusCount = randomContact2.viewedStatusCount,
            )
        }
    }
}