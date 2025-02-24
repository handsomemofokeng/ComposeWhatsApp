package africa.digitalhusters.composewhatsapp.ui.screen.chats

import africa.digitalhusters.composewhatsapp.R
import africa.digitalhusters.composewhatsapp.data.generateRandomChats
import africa.digitalhusters.composewhatsapp.ui.shared.components.ChatItemView
import africa.digitalhusters.composewhatsapp.ui.shared.components.TextFilter
import africa.digitalhusters.composewhatsapp.ui.theme.ComposeWhatsAppTheme
import africa.digitalhusters.composewhatsapp.ui.theme.Dimensions
import africa.digitalhusters.composewhatsapp.ui.theme.LightGrey
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChatsScreen(
    modifier: Modifier = Modifier,
    onSearchBarClick: () -> Unit,
) {
    val selectedFilterIndex = rememberSaveable { mutableIntStateOf(0) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = Dimensions.Small),
        content = {
            item {
                Column {
                    ClickableSearchBar(onSearchBarClick = onSearchBarClick)

                    Spacer(Modifier.height(Dimensions.Medium))

                    FiltersRow(
                        filterList = stringArrayResource(R.array.chat_screen_filters).toPersistentList(),
                        selectedFilterIndex = selectedFilterIndex.intValue,
                        onFilterClick = { selectedIndex ->
                            selectedFilterIndex.intValue = selectedIndex
                        }
                    )
                    Spacer(Modifier.height(Dimensions.Medium))
                }
            }

            items(generateRandomChats(20)) { chatItem ->
                ChatItemView(chatItem)
                Spacer(Modifier.height(Dimensions.Medium))
            }
        }
    )
}

@Composable
private fun FiltersRow(
    filterList: ImmutableList<String>,
    selectedFilterIndex: Int,
    onFilterClick: (selectedFilterIndex: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(Dimensions.Small)
    ) {
        itemsIndexed(filterList) { index, item ->
            TextFilter(
                text = item,
                isSelected = selectedFilterIndex == index,
                modifier = Modifier.clickable {
                    onFilterClick(index)
                }
            )
        }

        item {
            Card {
                Icon(
                    imageVector = Icons.Default.Add,
                    tint = LightGrey,
                    contentDescription = "Add new filter",
                    modifier = Modifier.padding(
                        horizontal = Dimensions.Small,
                        vertical = 2.dp
                    )
                )
            }
        }
    }
}

@Composable
private fun ClickableSearchBar(onSearchBarClick: () -> Unit) {
    Card(
        modifier = Modifier
            .clickable {
                onSearchBarClick()
            }
            .fillMaxWidth(),
        shape = RoundedCornerShape(percent = 100)
    ) {
        Row(modifier = Modifier.padding(all = Dimensions.Medium)) {
            Icon(
                painter = painterResource(R.drawable.icn_search),
                contentDescription = null,
                tint = LightGrey
            )
            Spacer(Modifier.width(Dimensions.Medium))
            Text(
                text = stringResource(R.string.ask_mpho_ai_or_search_label),
                color = LightGrey
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
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