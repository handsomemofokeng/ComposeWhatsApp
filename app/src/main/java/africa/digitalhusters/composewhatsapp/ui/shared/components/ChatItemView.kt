package africa.digitalhusters.composewhatsapp.ui.shared.components

import africa.digitalhusters.composewhatsapp.R
import africa.digitalhusters.composewhatsapp.data.ChatItem
import africa.digitalhusters.composewhatsapp.data.generateRandomChats
import africa.digitalhusters.composewhatsapp.ui.theme.ComposeWhatsAppTheme
import africa.digitalhusters.composewhatsapp.ui.theme.DarkGrey
import africa.digitalhusters.composewhatsapp.ui.theme.Dimensions
import africa.digitalhusters.composewhatsapp.ui.theme.Green
import africa.digitalhusters.composewhatsapp.ui.theme.LightGrey
import africa.digitalhusters.composewhatsapp.ui.theme.Typography
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChatItemView(
    chatItem: ChatItem,
    modifier: Modifier = Modifier,
) {

    Row(modifier = modifier.padding(vertical = Dimensions.Small)) {
        Box {
            CircularProgressIndicator(
                modifier = Modifier.size(Dimensions.ProfilePictureSize),
                progress = { if (chatItem.hasNewUpdates) 1f else 0f },
                color = Green,
                strokeWidth = 2.dp
            )
            AsyncImage(
                model = chatItem.profilePictureUrl,
                contentDescription = "Profile picture",
                placeholder = painterResource(R.drawable.icn_default_profile_picture),
                error = painterResource(R.drawable.icn_default_profile_picture),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(Dimensions.ProfilePictureSize)
                    .padding(Dimensions.XSmall)
                    .clip(shape = CircleShape)
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = Dimensions.Medium)
        ) {
            val hasUnreadMessages = chatItem.unreadMessageCount > 0
            Row {
                Text(
                    text = chatItem.contactName,
                    style = Typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = chatItem.timestamp,
                    style = Typography.bodySmall,
                    fontWeight = if (hasUnreadMessages) {
                        FontWeight.Bold
                    } else {
                        FontWeight.Normal
                    },
                    color = if (hasUnreadMessages) {
                        Green
                    } else {
                        LightGrey
                    }
                )
            }

            Spacer(Modifier.height(Dimensions.XSmall))

            Row {
                Text(
                    text = chatItem.lastMessage,
                    style = Typography.bodyMedium,
                    color = LightGrey,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .weight(1f)
                        .padding(2.dp)
                )
                if (hasUnreadMessages) {
                    Text(
                        text = chatItem.unreadMessageCount.toString(),
                        style = Typography.bodySmall,
                        color = DarkGrey,
                        modifier = Modifier
                            .background(
                                color = Green,
                                shape = CircleShape
                            )
                            .padding(vertical = Dimensions.XSmall,
                                horizontal = Dimensions.Small)
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
private fun ChatItemPreview() {
    ComposeWhatsAppTheme {
        Column(modifier = Modifier.padding(top = Dimensions.XXLarge)) {
            ChatItemView(
                chatItem = generateRandomChats(1)[0]
            )
            ChatItemView(
                chatItem = generateRandomChats(1)[0]
            )
        }
    }
}