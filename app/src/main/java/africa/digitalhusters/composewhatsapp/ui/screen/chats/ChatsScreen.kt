package africa.digitalhusters.composewhatsapp.ui.screen.chats

import africa.digitalhusters.composewhatsapp.R
import africa.digitalhusters.composewhatsapp.ui.theme.ComposeWhatsAppTheme
import africa.digitalhusters.composewhatsapp.ui.theme.Dimensions
import africa.digitalhusters.composewhatsapp.ui.theme.LightGrey
import africa.digitalhusters.composewhatsapp.ui.theme.PaleGrey
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ChatsScreen(
    modifier: Modifier = Modifier,
    onSearchBarClick: () -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = Dimensions.Medium),
        content = {
            item {
                Column {
                    Row(
                        modifier = Modifier
                            .background(
                                color = LightGrey,
                                shape = RoundedCornerShape(percent = 100)
                            )
                            .clickable {
                                onSearchBarClick()
                            }
                            .padding(all = Dimensions.Medium)
                            .fillMaxWidth(),
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.icn_search),
                            contentDescription = null,
                            tint = PaleGrey
                        )
                        Spacer(Modifier.width(Dimensions.Medium))
                        Text(
                            text = stringResource(R.string.ask_meta_ai_or_search_label),
                            color = PaleGrey
                        )
                    }
                }
            }
        }
    )
}

@Preview(showSystemUi = true)
@Composable
private fun ChatScreenPreview() {
    ComposeWhatsAppTheme {
        ChatsScreen(
            modifier = Modifier.padding(top = Dimensions.Large),
            onSearchBarClick = {}
        )
    }
}